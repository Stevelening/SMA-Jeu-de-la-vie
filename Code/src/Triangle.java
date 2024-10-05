import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class Triangle extends Path2D.Double {
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        moveTo(x1, y1);
        lineTo(x2, y2);
        lineTo(x3, y3);
        closePath();
    }

    public void draw(Graphics2D g2d, Color fillColor, Color borderColor) {
        g2d.setColor(fillColor);
        g2d.fill(this);

        g2d.setColor(borderColor);
        g2d.draw(this);
    }

    // Optional: Method to check if a point is contained within the triangle
    public boolean containsPoint(Point2D point) {
        return contains(point);
    }
}
