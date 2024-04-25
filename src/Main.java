import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int WIDTH = 1000;
        int HEIGHT = 700;

        JFrame frame = new JFrame();
        EnginePanel ep = new EnginePanel(WIDTH, HEIGHT);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("3D Engine");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(ep);
        frame.setVisible(true);

        ep.startThread();
    }
}