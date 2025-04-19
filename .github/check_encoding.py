import sys
import os

def is_windows1252_encoded(file_path):
    try:
        with open(file_path, 'r', encoding='Windows-1252') as f:
            f.read()
        return True
    except UnicodeDecodeError:
        return False

def main():
    failed_files = []
    for file_path in sys.argv[1:]:
        if not os.path.isfile(file_path):
            continue
        if not is_windows1252_encoded(file_path):
            failed_files.append(file_path)

    if failed_files:
        print("The following files are not in Windows-1252 encoding:")
        for file in failed_files:
            print(f"  - {file}")
        sys.exit(1)
    else:
        print("All files passed encoding check (Windows-1252).")

if __name__ == "__main__":
    main()
