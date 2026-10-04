package kz.aitu.bridge.implementor;

import java.util.ArrayList;
import java.util.List;

/** Concrete Implementor: describes the picture as SVG vector elements. */
public class VectorRenderer implements Renderer {

    private final int width;
    private final int height;
    private final List<String> elements = new ArrayList<>();

    public VectorRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawCircle(int centerX, int centerY, int radius) {
        elements.add(String.format(
                "  <circle cx=\"%d\" cy=\"%d\" r=\"%d\" fill=\"none\" stroke=\"black\"/>",
                centerX, centerY, radius));
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        elements.add(String.format(
                "  <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"none\" stroke=\"black\"/>",
                x, y, width, height));
    }

    @Override
    public String result() {
        StringBuilder svg = new StringBuilder();
        svg.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">%n",
                width, height));
        elements.forEach(e -> svg.append(e).append(System.lineSeparator()));
        return svg.append("</svg>").toString();
    }
}
