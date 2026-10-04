package kz.aitu.bridge.abstraction;

import kz.aitu.bridge.implementor.Renderer;

import java.util.Objects;

/**
 * Abstraction: what a shape IS. Holds the "bridge" - a reference to a Renderer -
 * and delegates all low-level drawing to it.
 */
public abstract class Shape {

    protected final Renderer renderer;

    protected Shape(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer must not be null");
    }

    public abstract void draw();

    public abstract String name();
}
