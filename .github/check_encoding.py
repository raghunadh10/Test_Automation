import sys
import os
import re
import argparse

# Regex patterns
v_script_pattern = re.compile(r'^v\d{17}__.+\.sql$', re.IGNORECASE)
r_script_pattern = re.compile(r'^r__\w+\.\w+\.(pkb|pks)\.sql$', re.IGNORECASE)

# Expected folder paths
V_SCRIPT_FOLDER = os.path.normpath("clpss-db/DB/sql/Data")
R_SCRIPT_FOLDER = os.path.normpath("clpss-db/DB/sql/Packages")

def is_windows1252_encoded(file_path):
    try:
        with open(file_path, 'r', encoding='windows-1252') as f:
            f.read()
        return True
    except UnicodeDecodeError:
        return False

def is_valid_filename(filename):
    if filename.startswith('v'):
        return bool(v_script_pattern.match(filename))
    elif filename.startswith('r'):
        return bool(r_script_pattern.match(filename))
    return False

def is_valid_folder(file_path):
    norm_path = os.path.normpath(file_path)
    if os.path.basename(file_path).startswith('v'):
        return V_SCRIPT_FOLDER in norm_path
    elif os.path.basename(file_path).startswith('r'):
        return R_SCRIPT_FOLDER in norm_path
    return False

def parse_status_files(status_files_string):
    status_dict = {}
    if status_files_string:
        lines = status_files_string.strip().split('\n')
        for line in lines:
            status, filename = line.strip().split(maxsplit=1)
            status_dict[filename] = status
    return status_dict

def main():
    parser = argparse.ArgumentParser(description="Validate files for encoding, naming, folder, and status.")
    parser.add_argument('files', nargs='+', help='List of changed files')
    parser.add_argument('--status-files', type=str, help='String containing file status and name')

    args = parser.parse_args()
    status_dict = parse_status_files(args.status_files) if args.status_files else {}

    results = {}
    failed = False

    for file_path in args.files:
        file_name = os.path.basename(file_path)
        status = status_dict.get(file_path, "Unknown")

        # If file is Modified ('M'), fail immediately
        if status == "M":
            print(f"\n⛔ File '{file_path}' is Modified (M). Modifications are not allowed.")
            failed = True
            continue  # skip further validation for this file

        if not os.path.isfile(file_path):
            print(f"Skipping non-existent file: {file_path}")
            continue

        results[file_path] = {
            "encoding": is_windows1252_encoded(file_path),
            "naming": is_valid_filename(file_name),
            "folder": is_valid_folder(file_path)
        }

    # Now check encoding, naming, folder validations
    for file, checks in results.items():
        errors = []
        if not checks["encoding"]:
            errors.append("❌ Invalid encoding (not Windows-1252)")
        
        elif not checks["naming"]:
            errors.append("❌ Invalid naming convention")
        
        elif not checks["folder"]:
            errors.append("❌ Incorrect folder location")
        
        if errors:
            failed = True
            print(f"\n❌ Issues found in file: {file}")
            for error in errors:
                print(f"   - {error}")

    if failed:
        print("\n⛔ One or more files failed validation checks.")
        sys.exit(1)
    else:
        print("\n✅ All files passed encoding, naming, folder location, and status checks.")

if __name__ == "__main__":
    main()
