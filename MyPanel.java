import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel implements Runnable{

    Renderer renderer;
    static final int SCR_WIDTH = 1000;
    static final int SCR_HEIGHT = 1000;
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
        Graphics2D g2d = (Graphics2D) g;
        
        for (int x = 0; x < SCR_WIDTH; x++) {
            for (int y = 0; y < SCR_HEIGHT; y++) {
                Color c = renderer.getPixelColor(x, y);
                g2d.setPaint(c);
                g2d.drawLine(x, y, x, y);
            }
        }

    }

    @Override
    public void run() {
        while (thread != null) {
            repaint();
        }
    }
}