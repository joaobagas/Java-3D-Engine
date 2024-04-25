import javax.swing.*;
import java.awt.*;

public class EnginePanel extends JPanel implements Runnable {
    final int fps = 60;
    Thread engineThread;
    int width;
    int height;

    public EnginePanel(int width, int height) {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        this.width = width;
        this.height = height;
    }

    public void startThread() {
        engineThread = new Thread(this);
        engineThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        int timer = 0;
        int drawCount = 0;

        while(engineThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (int) (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0, width, height);

        g.dispose();
    }
}
