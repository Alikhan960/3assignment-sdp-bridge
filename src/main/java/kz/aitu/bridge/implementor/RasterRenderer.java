package kz.aitu.bridge.implementor;

import java.util.Arrays;

/** Concrete Implementor: paints pixels (shown as characters) on a fixed-size grid. */
public class RasterRenderer implements Renderer {

    private static final char PIXEL = '#';
    private static final char EMPTY = '.';

    private final char[][] pixels;

    public RasterRenderer(int width, int height) {
        pixels = new char[height][width];
        for (char[] row : pixels) {
            Arrays.fill(row, EMPTY);
        }
    }

    @Override
    public void drawCircle(int centerX, int centerY, int radius) {
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                // Terminal cells are ~2x taller than wide, so stretch x to look round.
                double dx = (x - centerX) / 2.0;
                double dy = y - centerY;
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (Math.abs(distance - radius) < 0.5) {
                    setPixel(x, y);
                }
            }
        }
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            setPixel(i, y);
            setPixel(i, y + height - 1);
        }
        for (int j = y; j < y + height; j++) {
            setPixel(x, j);
            setPixel(x + width - 1, j);
        }
    }

    @Override
    public String result() {
        StringBuilder out = new StringBuilder();
        for (char[] row : pixels) {
            out.append(new String(row)).append(System.lineSeparator());
        }
        return out.toString().stripTrailing();
    }

    private void setPixel(int x, int y) {
        if (y >= 0 && y < pixels.length && x >= 0 && x < pixels[y].length) {
            pixels[y][x] = PIXEL;
        }
    }
}
