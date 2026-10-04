package kz.aitu.bridge.abstraction;

import kz.aitu.bridge.implementor.Renderer;

/** Refined Abstraction. */
public class Circle extends Shape {

    private final int centerX;
    private final int centerY;
    private final int radius;

    public Circle(Renderer renderer, int centerX, int centerY, int radius) {
        super(renderer);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.drawCircle(centerX, centerY, radius);
    }

    @Override
    public String name() {
        return "Circle";
    }
}
