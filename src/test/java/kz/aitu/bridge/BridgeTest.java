package kz.aitu.bridge;

import kz.aitu.bridge.abstraction.Circle;
import kz.aitu.bridge.abstraction.Shape;
import kz.aitu.bridge.abstraction.Square;
import kz.aitu.bridge.implementor.RasterRenderer;
import kz.aitu.bridge.implementor.Renderer;
import kz.aitu.bridge.implementor.VectorRenderer;

/** Dependency-free tests (run with: java -cp out kz.aitu.bridge.BridgeTest). */
public class BridgeTest {

    private static int failures = 0;

    public static void main(String[] args) {
        vectorCircleProducesSvgCircle();
        vectorSquareProducesSvgRect();
        rasterShapesMarkPixels();
        sameShapeWorksWithAnyRenderer();
        nullRendererIsRejected();
        newRendererNeedsNoShapeChanges();
        if (failures > 0) {
            System.out.println(failures + " test(s) FAILED");
            System.exit(1);
        }
        System.out.println("All tests passed");
    }

    static void vectorCircleProducesSvgCircle() {
        Renderer r = new VectorRenderer(20, 20);
        new Circle(r, 5, 5, 3).draw();
        check("vector circle", r.result().contains("<circle cx=\"5\" cy=\"5\" r=\"3\""));
    }

    static void vectorSquareProducesSvgRect() {
        Renderer r = new VectorRenderer(20, 20);
        new Square(r, 1, 2, 4).draw();
        check("vector square", r.result().contains("<rect x=\"1\" y=\"2\" width=\"4\" height=\"4\""));
    }

    static void rasterShapesMarkPixels() {
        Renderer r = new RasterRenderer(20, 10);
        new Square(r, 0, 0, 3).draw();
        check("raster square", r.result().startsWith("###"));
    }

    static void sameShapeWorksWithAnyRenderer() {
        for (Renderer r : new Renderer[]{new VectorRenderer(10, 10), new RasterRenderer(10, 10)}) {
            Shape s = new Circle(r, 4, 4, 2);
            s.draw();
            check("draw with " + r.getClass().getSimpleName(), !r.result().isEmpty());
        }
    }

    static void nullRendererIsRejected() {
        try {
            new Circle(null, 0, 0, 1);
            check("null renderer rejected", false);
        } catch (NullPointerException expected) {
            check("null renderer rejected", true);
        }
    }

    /** A brand-new Renderer implementation works with the unchanged Square. */
    static void newRendererNeedsNoShapeChanges() {
        StringBuilder log = new StringBuilder();
        Renderer textual = new Renderer() {
            public void drawCircle(int x, int y, int r) { log.append("circle;"); }
            public void drawRectangle(int x, int y, int w, int h) { log.append("rect;"); }
            public String result() { return log.toString(); }
        };
        new Square(textual, 0, 0, 1).draw();
        check("new renderer", textual.result().equals("rect;"));
    }

    private static void check(String name, boolean ok) {
        System.out.println((ok ? "PASS " : "FAIL ") + name);
        if (!ok) failures++;
    }
}
