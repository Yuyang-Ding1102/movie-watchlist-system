#!/usr/bin/env bash
set -e
mkdir -p bin
find src -name "*.class" -delete || true
javac -d bin $(find src -name "*.java")
echo "✅ Compiled to bin/"
