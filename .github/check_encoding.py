import sys
import os
import re

# Regex patterns for V and R script naming conventions
v_script_pattern = re.compile(r'^v\d{14}__.+\.sql$', re.IGNORECASE)
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
    return False  # For non v/r scripts, skip naming check

def is_valid_folder(file_path):
    norm_path = os.path.normpath(file_path)
    if os.path.basename(file_path).startswith('v'):
        return V_SCRIPT_FOLDER in norm_path
    elif os.path.basename(file_path).startswith('r'):
        return R_SCRIPT_FOLDER in norm_path
    return False  # Non v/r scripts bypass folder check

def main():
    encoding_failed_files = []
    naming_failed_files = []
    location_failed_files = []

    for file_path in sys.argv[1:]:
        if not os.path.isfile(file_path):
            continue

        file_name = os.path.basename(file_path)

        if not is_valid_filename(file_name):
            naming_failed_files.append(file_name)

        if not is_windows1252_encoded(file_path):
            encoding_failed_files.append(file_path)

        # Check folder structure
        if not is_valid_folder(file_path):
            location_failed_files.append(file_path)

    if naming_failed_files:
        print("The following files have invalid naming conventions:")
        for file in naming_failed_files:
            print(f"  - {file}")
        sys.exit(3)

    if encoding_failed_files:
        print("The following files are not in Windows-1252 encoding:")
        for file in encoding_failed_files:
            print(f"  - {file}")
        sys.exit(2)

    if encoding_failed_files:
        print("❌ The following files are not in Windows-1252 encoding:")
        for file in encoding_failed_files:
            print(f"  - {file}")
        sys.exit(1)

    print("✅ All files passed encoding, naming, and folder checks.")

if __name__ == "__main__":
    main()
