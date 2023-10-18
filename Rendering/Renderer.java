package Rendering;
import java.awt.*;

import org.joml.*;
import Rendering.RayTracing.Scene;

public class Renderer {

    Scene scene;

    Renderer() {
        scene = Scene.getInstance();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        for (int x = 0; x < MyPanel.SCR_WIDTH; x++) {
            for (int y = 0; y < MyPanel.SCR_HEIGHT; y++) {
                Color c = getPixelColor(x, y);
                g2d.setPaint(c);
                g2d.drawLine(x, y, x, y);
            }
        }
    }

    public Color getPixelColor(int x, int y) {
        Vector2f coords = new Vector2f((float) x / (float) MyPanel.SCR_WIDTH, (float) y / (float) MyPanel.SCR_HEIGHT);
        coords = coords.mul(2.0f).sub(new Vector2f(1.0f));

        Vector3f color = scene.calculatePixelColor(coords);

        return new Color(ConvertColor(new Vector4f(color, 1.0f)), true);
    }

    public static int ConvertColor(Vector4f color) {
        int r = (int)(color.x * 255.0f);
        int g = (int)(color.y * 255.0f);
        int b = (int)(color.z * 255.0f);
        int a = (int)(color.w * 255.0f);

        return (a << 24) | (r << 16) | (g << 8) | (b);
    }
}
