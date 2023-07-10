import java.awt.*;

public class Snake {
    private final int SQUARE_SIZE = 20;
    public int size;
    private int[] x, y;
    private Square square = new Square(0, 0, SQUARE_SIZE, Color.WHITE);;
    public Snake(int[] x, int[] y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < size; i++) {
            square.x = x[i];
            square.y = y[i];
            square.draw(g2);
        }
    }
}
