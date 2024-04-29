import gfx.BitMap;
import math.Matrix;
import math.Mesh;
import math.Triangle;
import math.Vector3D;
import gfx.DrawRoutines;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static gfx.DrawRoutines.drawTriangle;

public class EnginePanel extends JPanel implements Runnable {
    final int fps = 60;
    Thread engineThread;
    int width;
    int height;

    // Key handler for the engine
    KeyHandler keyHandler = new KeyHandler();

    // Projection Matrix data
    double fNear = 0.1; // Nearest clip plane
    double fFar = 1000;
    double aspectRatio = 600 / 800; // Height/Width
    double fov = 50;

    // Object mesh
    Mesh object3D;

    // Matrix mat = new Matrix();
    Matrix matrixProj = Matrix.projectMatrix(fNear, fFar, aspectRatio, fov);

    public EnginePanel(int width, int height) {
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        this.width = width;
        this.height = height;

        initializeMesh();
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

    public void initializeMesh() {
        object3D = new Mesh(Arrays.asList(
                new Triangle[]{
                        // South
                        new Triangle(new Vector3D(0,0,0), new Vector3D(0,1,0), new Vector3D(1,1,0)),
                        new Triangle(new Vector3D(0,0,0), new Vector3D(1,1,0), new Vector3D(1,0,0)),
                        // East
                        new Triangle(new Vector3D(1,0,0), new Vector3D(1,1,0), new Vector3D(1,1,1)),
                        new Triangle(new Vector3D(1,0,0), new Vector3D(1,1,1), new Vector3D(1,0,1)),
                        // North
                        new Triangle(new Vector3D(1,0,0), new Vector3D(1,1,1), new Vector3D(0,1,1)),
                        new Triangle(new Vector3D(1,0,0), new Vector3D(0,1,1), new Vector3D(0,0,1)),
                        // West
                        new Triangle(new Vector3D(0,0,1), new Vector3D(0,1,1), new Vector3D(0,1,0)),
                        new Triangle(new Vector3D(0,0,1), new Vector3D(0,1,0), new Vector3D(0,0,0)),
                        // Top
                        new Triangle(new Vector3D(0,1,0), new Vector3D(0,1,1), new Vector3D(1,1,1)),
                        new Triangle(new Vector3D(0,1,0), new Vector3D(1,1,1), new Vector3D(1,1,0)),
                        // Bottom
                        new Triangle(new Vector3D(1,0,1), new Vector3D(0,0,1), new Vector3D(0,0,0)),
                        new Triangle(new Vector3D(1,0,1), new Vector3D(0,0,0), new Vector3D(1,0,0))
                }
            )
        );
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0, width, height);

        Graphics2D g2 = (Graphics2D) g;

        for(Triangle tri: object3D.triangles) {
            Triangle triProjected = new Triangle(new Vector3D(0,0,0), new Vector3D(0,0,0), new Vector3D(0,0,0));

            Matrix.multiplyMatrixVector(tri.firstVertex, tri.firstVertex, matrixProj);
            Matrix.multiplyMatrixVector(tri.secondVertex, tri.secondVertex, matrixProj);
            Matrix.multiplyMatrixVector(tri.thirdVertex, tri.thirdVertex, matrixProj);

            g.setColor(Color.WHITE);

            drawTriangle(g2,
                    triProjected.firstVertex.x, triProjected.firstVertex.y,
                    triProjected.secondVertex.x, triProjected.secondVertex.y,
                    triProjected.thirdVertex.x, triProjected.thirdVertex.y);
        }

        g.dispose();
    }
}
