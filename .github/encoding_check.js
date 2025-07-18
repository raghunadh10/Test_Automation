const fs = require('fs');
const iconv = require('iconv-lite');

const filePath = process.argv[2];

try {
  const content = fs.readFileSync(filePath);
  const decoded = iconv.decode(content, 'windows-1252');

  if (decoded.includes('�')) {
    console.error(`Encoding issue: ${filePath} contains invalid characters.`);
    process.exit(1);
  }

  console.log(`Encoding valid for: ${filePath}`);
} catch (err) {
  console.error(`Error checking encoding for ${filePath}: ${err.message}`);
  process.exit(1);
}
