#!/usr/bin/env bash
set -e

if [ ! -d "src" ]; then
  echo "❌ Please run this script from your project root (where the 'src' folder is)."
  exit 1
fi

echo "🔧 Creating .vscode/settings.json…"
mkdir -p .vscode
cat > .vscode/settings.json <<'JSON'
{
  "java.project.sourcePaths": ["src"],
  "java.project.outputPath": "bin",
  "java.configuration.updateBuildConfiguration": "automatic"
}
JSON

echo "🧹 Cleaning old class files…"
find src -name "*.class" -delete || true
rm -rf bin
mkdir -p bin

echo "🧪 Compiling all Java sources to bin/…"
javac -d bin $(find src -name "*.java")

echo "✅ Compile OK."
echo "▶️  You can run the app with:  java -cp bin Main
