# Stabilize Java Project (VS Code + Plain Java)

## What this kit does
- Sets VS Code to treat `src/` as the source root and `bin/` as the output folder
- Cleans stray `.class` files from `src/`
- Compiles all `.java` files into `bin/`
- Shows how to run `Main`

## Usage
1. Copy this folder's contents into your project root (where `src/` lives).
2. Run:
   ```bash
   ./setup.sh
   ```
3. In VS Code, run **Java: Clean Java Language Server Workspace** once (Cmd+Shift+P), then reopen the project root.

### Daily workflow
- Compile: `./compile.sh`
- Run: `./run.sh`

If `import model.Movie;` disappears in VS Code, repeat: Clean Java Language Server Workspace (once), make sure `.vscode/settings.json` still exists, and that no `.class` files are inside `src/`.
