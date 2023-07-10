import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square {
    public double x, y, width;
    private Color color;

    public Square(double x, double y, double width, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.color = color;
    }

    public void draw (Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x, y, width, width));
    }
}
