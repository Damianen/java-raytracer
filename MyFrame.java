import javax.swing.*;

public class MyFrame extends JFrame {
    
    MyPanel panel;
    MyFrame() {
        panel = new MyPanel(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setTitle("RayTracer");

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel.startThread();
    }
}
