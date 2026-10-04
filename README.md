# Bridge Pattern — Shape × Renderer (Java 17)

Assignment #3

The **Bridge** pattern splits one class hierarchy into two that can change independently, connected by composition:

| Role in the pattern    | Class                              |
|------------------------|------------------------------------|
| Abstraction            | `Shape` (abstract, holds a `Renderer`) |
| Refined Abstraction    | `Circle`, `Square`                 |
| Implementor            | `Renderer` (interface)             |
| Concrete Implementor   | `VectorRenderer` (SVG), `RasterRenderer` (pixel grid) |
| Client                 | `Main`                             |

Adding a new shape (e.g. `Triangle`) or a new renderer (e.g. `AsciiRenderer`) never requires editing the other hierarchy.

## Structure

```
src/main/java/kz/aitu/bridge/
├── Main.java                  # client / demo
├── abstraction/               # Shape, Circle, Square
└── implementor/               # Renderer, VectorRenderer, RasterRenderer
src/test/java/kz/aitu/bridge/
└── BridgeTest.java            # dependency-free tests
docs/                          # UML diagram (uml.dot -> uml-class-diagram.png)
```

## Build & run

Requires JDK 17+.

```bash
mkdir out
javac -d out $(find src -name '*.java')   # Windows PowerShell: javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out kz.aitu.bridge.Main          # demo: same shapes, two renderers
java -cp out kz.aitu.bridge.BridgeTest    # tests
```

In IntelliJ IDEA: open the folder, mark `src/main/java` and `src/test/java` as source roots, run `Main`.

## Demo output

`VectorRenderer` prints SVG, `RasterRenderer` prints a character bitmap — produced by the *same* `Circle` and `Square` code.
