import sys
import os
import re
import chardet

def detect_encoding(file_path):
    with open(file_path, 'rb') as f:
        result = chardet.detect(f.read())
    return result.get('encoding')

def is_windows1252_encoded(file_path):
    try:
        with open(file_path, 'r', encoding='windows-1252') as f:
            f.read()
        return True
    except UnicodeDecodeError:
        return False

def validate_naming_and_folder(file_path):
    issues = []

    filename = os.path.basename(file_path)
    v_pattern = r'^v\d{17}__.+\.sql$'
    r_pattern = r'^r\d{17}__.+\.sql$'

    if '/vscript/' in file_path.lower():
        if not re.match(v_pattern, filename):
            issues.append('❌ Invalid naming convention for v script')
    elif '/rscript/' in file_path.lower():
        if not re.match(r_pattern, filename):
            issues.append('❌ Invalid naming convention for r script')
    else:
        issues.append('❌ Script not in correct folder (vscript/ or rscript/)')

    return issues

def is_modification_blocked_for_vscript(change_type, file_path):
    return change_type == 'M' and '/vscript/' in file_path.lower()

def main():
    encoding_issues = []
    naming_issues = []
    blocked_modifications = []

    with open('changed_files.txt', 'r') as f:
        for line in f:
            change_type, file_path = line.strip().split('\t')
            if not os.path.isfile(file_path):
                continue

            # Encoding check
            if not is_windows1252_encoded(file_path):
                encoding_issues.append(file_path)

            # Naming and folder check
            issues = validate_naming_and_folder(file_path)
            if issues:
                naming_issues.append((file_path, issues))

            # vScript modification block
            if is_modification_blocked_for_vscript(change_type, file_path):
                blocked_modifications.append(file_path)

    if encoding_issues or naming_issues or blocked_modifications:
        print("\n❌ Validation failed with the following issues:")
        if encoding_issues:
            print("\nEncoding Issues:")
            for file in encoding_issues:
                print(f"  - {file} is not Windows-1252 encoded")

        if naming_issues:
            print("\nNaming/Folder Issues:")
            for file, issues in naming_issues:
                print(f"  - {file}")
                for issue in issues:
                    print(f"     {issue}")

        if blocked_modifications:
            print("\nBlocked vScript Modifications:")
            for file in blocked_modifications:
                print(f"  - Modification not allowed: {file}")

        sys.exit(1)
    else:
        print("\n✅ All checks passed: encoding, naming, folder, and vScript rules.")

if __name__ == "__main__":
    main()
