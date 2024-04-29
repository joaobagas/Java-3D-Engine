package gfx;

import java.awt.*;
import java.awt.geom.Line2D;

public class DrawRoutines {
    public static void drawTriangle(Graphics2D g2D, double x1, double y1, double x2, double y2, double x3, double y3) {
        // Define the stroke
        g2D.setStroke(new BasicStroke(3));

        // Draw three lines
        g2D.draw(new Line2D.Double(x1, y1, x2, y2));
        g2D.draw(new Line2D.Double(x2, y2, x3, y3));
        g2D.draw(new Line2D.Double(x3, y3, x1, y1));
    }

    public void textureTriangle() {

    }
}
