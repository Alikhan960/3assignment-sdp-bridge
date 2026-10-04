package kz.aitu.bridge.implementor;

/**
 * Implementor: low-level drawing primitives.
 * Knows nothing about shapes such as Circle or Square.
 */
public interface Renderer {

    void drawCircle(int centerX, int centerY, int radius);

    void drawRectangle(int x, int y, int width, int height);

    /** Produces the finished picture in this renderer's own format. */
    String result();
}
