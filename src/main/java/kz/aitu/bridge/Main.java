package kz.aitu.bridge;

import kz.aitu.bridge.abstraction.Circle;
import kz.aitu.bridge.abstraction.Shape;
import kz.aitu.bridge.abstraction.Square;
import kz.aitu.bridge.implementor.RasterRenderer;
import kz.aitu.bridge.implementor.Renderer;
import kz.aitu.bridge.implementor.VectorRenderer;

import java.util.function.Supplier;

/** Client: composes shapes with renderers at runtime and swaps renderers freely. */
public class Main {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 14;

    public static void main(String[] args) {
        demo("VectorRenderer", () -> new VectorRenderer(WIDTH, HEIGHT));
        demo("RasterRenderer", () -> new RasterRenderer(WIDTH, HEIGHT));
    }

    /** The same shape code runs with whichever renderer is supplied. */
    private static void demo(String title, Supplier<Renderer> rendererFactory) {
        System.out.println("=== " + title + " ===");
        Renderer renderer = rendererFactory.get();

        Shape[] shapes = {
                new Circle(renderer, 10, 6, 5),
                new Square(renderer, 22, 2, 10)
        };
        for (Shape shape : shapes) {
            shape.draw();
        }
        System.out.println(renderer.result());
        System.out.println();
    }
}
