import java.awt.*;
import java.util.*;
import java.lang.Math;

import org.joml.*;

public class Renderer {

    Renderer() {
        
    }

    public Color getPixelColor(int x, int y) {
        Vector2f coords = new Vector2f(x / (float) MyPanel.SCR_WIDTH, y / (float) MyPanel.SCR_HEIGHT);
        coords = coords.mul(2.0f).sub(new Vector2f(1.0f));

        Vector3f rayOrg = new Vector3f(0.0f, 0.0f, 1.0f);
        Vector3f rayDir = new Vector3f(coords.x, coords.y, -1.0f);
        float radius = 0.5f;

        float a = rayDir.dot(rayDir);
        float b = 2.0f * rayOrg.dot(rayDir);
        float c = rayOrg.dot(rayOrg) - radius * radius;

        float d = b * b - 4.0f * a * c;

        Vector3f sphereColor = new Vector3f(1.0f, 0.0f, 1.0f);

        if (d < 0.0f) {
            return new Color(ConvertColor(new Vector4f(0, 0, 0, 1)), true);
        }

        float closetT = (-b - (float)Math.sqrt(d)) / (2.0f * a);

        Vector3f hitpoint = rayOrg.add(rayDir.mul(closetT));
        Vector3f normal = hitpoint.normalize();

        Vector3f lightDir = new Vector3f(1.0f, -1.0f, 1.0f).normalize();

        float lightLevel = Math.max(normal.dot(lightDir.mul(1.0f)), 0.0f); 

        sphereColor = sphereColor.mul(lightLevel);

        return new Color(ConvertColor(new Vector4f(sphereColor, 1.0f)), true);

    }

    public static int ConvertColor(Vector4f color) {
        int r = (int)(color.x * 255.0f);
        int g = (int)(color.y * 255.0f);
        int b = (int)(color.z * 255.0f);
        int a = (int)(color.w * 255.0f);

        return (a << 24) | (r << 16) | (g << 8) | (b);
    }
}
