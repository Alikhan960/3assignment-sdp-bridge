package kz.aitu.bridge.abstraction;

import kz.aitu.bridge.implementor.Renderer;

/** Refined Abstraction. A square is a rectangle with equal sides. */
public class Square extends Shape {

    private final int x;
    private final int y;
    private final int side;

    public Square(Renderer renderer, int x, int y, int side) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.side = side;
    }

    @Override
    public void draw() {
        renderer.drawRectangle(x, y, side, side);
    }

    @Override
    public String name() {
        return "Square";
    }
}
