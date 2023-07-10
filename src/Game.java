import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements Runnable {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private final int SQUARE_SIZE_MOVE = 20;
    private final int[] x = new int[40];
    private final int[] y = new int[30];
    private final KeyHandler keyHandler = new KeyHandler();
    private Snake snake;
    private Square apple;
    private int appleX, appleY;
    private String snakeDirection;
    private int snakeSize;
    private int score;
    private boolean inGame = true;
    private final Random random = new Random();

    public Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        //this.setDoubleBuffered(true);
        startGame();
    }

    public void startGame() {
        snakeSize = 3;
        snakeDirection = "left";
        for (int i = 0; i < snakeSize; i++) {
            x[i] = 400 + i * SQUARE_SIZE_MOVE;
            y[i] = 300;
        }
        appleX = 20 * random.nextInt(40);
        appleY = 20 * random.nextInt(30);
        snake = new Snake(x, y, snakeSize);
        apple = new Square(appleX, appleY, 20.0, Color.GREEN);
        score = 0;
    }

    public void update() {
        if (keyHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (!snakeDirection.equals("right")) {
                snakeDirection = "left";
            }
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (!snakeDirection.equals("left")) {
                snakeDirection = "right";
            }
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_UP)) {
            if (!snakeDirection.equals("down")) {
                snakeDirection = "up";
            }
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_DOWN)) {
            if (!snakeDirection.equals("up")) {
                snakeDirection = "down";
            }
        }

        if (x[0] == apple.x && y[0] == apple.y) {
            snakeSize++;
            snake.size++;
            score++;
            appleX = 20 * random.nextInt(40);
            appleY = 20 * random.nextInt(30);
            apple = new Square(appleX, appleY, 20.0, Color.GREEN);
        }

        if (x[0] > SCREEN_WIDTH) {
            x[0] = 0;
        } else if (x[0] < 0) {
            x[0] = SCREEN_WIDTH;
        } else if (y[0] > SCREEN_HEIGHT) {
            y[0] = 0;
        } else if (y[0] < 0) {
            y[0] = SCREEN_HEIGHT;
        }

        updateSnakeCoordinates();

        switch (snakeDirection) {
            case "left" -> x[0] -= SQUARE_SIZE_MOVE;
            case "right" -> x[0] += SQUARE_SIZE_MOVE;
            case "up" -> y[0] -= SQUARE_SIZE_MOVE;
            case "down" -> y[0] += SQUARE_SIZE_MOVE;
            default -> {
            }
        }

        for (int i = 1; i < y.length; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        }
    }

    private void updateSnakeCoordinates() {
        for (int i = snakeSize - 1; i > 0; i--) {
            x[i] = x[i -1];
            y[i] = y[i - 1];
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        snake.draw(g2);
        apple.draw(g2);
        if (!inGame) {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Consolas", Font.BOLD, 50));
            g2.drawString("GAME OVER", 250, 250);
            g2.drawString("score: " + score, 250, 320);
        }
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / 10;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (inGame) {
            currentTime = System.nanoTime();
            delta +=  (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
        System.out.println("game over");
        repaint();
    }
}
