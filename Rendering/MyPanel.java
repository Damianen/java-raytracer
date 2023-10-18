package Rendering;
import javax.swing.*;

import java.awt.*;

public class MyPanel extends JPanel implements Runnable{

    static final int SCR_WIDTH = 1000;
    static final int SCR_HEIGHT = 1000;
    static final float aspect_ratio = SCR_WIDTH / SCR_HEIGHT;

    Renderer renderer; 
    Thread thread;
    MyFrame frame;

    MyPanel(MyFrame frame) {
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setDoubleBuffered(true);
        renderer = new Renderer();
        this.frame = frame;
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g);
    }

    @Override
    public void run() {
        while (thread != null) {
            repaint();
        }
    }
}