import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        Game game = new Game();

        window.setTitle("Snake");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Thread thread = new Thread(game);
        thread.start();
    }
}
