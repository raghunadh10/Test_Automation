import sys
import os
import re

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

def main():
    results = {}

    for file_path in sys.argv[1:]:
        if not os.path.isfile(file_path):
            continue

        file_name = os.path.basename(file_path)
        results[file_path] = {
            "encoding": is_windows1252_encoded(file_path),
            "naming": is_valid_filename(file_name),
            "folder": is_valid_folder(file_path)
        }

    failed = False
    for file, checks in results.items():
        errors = []
        if not checks["encoding"]:
            errors.append("❌ Invalid encoding (not Windows-1252)")
        if not checks["naming"]:
            errors.append("❌ Invalid naming convention")
        if not checks["folder"]:
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
        print("✅ All files passed encoding, naming, and folder location checks.")

if __name__ == "__main__":
    main()
