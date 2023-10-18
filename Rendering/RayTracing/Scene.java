package Rendering.RayTracing;
import java.util.*;
import java.lang.Math;

import org.joml.*;
public class Scene {
    public static synchronized Scene getInstance() {
        if (instance == null) {
            instance = new Scene();
        }
        
        return instance;
    }

    public Vector3f calculatePixelColor(Vector2f coords) {
        Ray ray = new Ray(new Vector3f(0.0f, 0.0f, 2.0f), new Vector3f(coords.x, coords.y, -1.0f));

        for (int i = 0; i < spheres.size(); i++) {
            if (!spheres.get(i).hitSphere(ray)) {
                continue;
            }

            Vector3f hitpoint = spheres.get(i).getLastHitpoint();
            Vector3f normal = hitpoint.normalize();

            Vector3f lightDir = new Vector3f(1.0f, 1.0f, -1.0f).normalize();

            float lightLevel = Math.max(normal.dot(lightDir.mul(-1.0f)), 0.0f);

            Vector3f color = new Vector3f(spheres.get(i).getSphereColor());
            
            color.mul(lightLevel);

            return color;
        }

        return new Vector3f(0.0f);
    }

    private static Scene instance = null;
    private ArrayList<Sphere> spheres = new ArrayList<>();
    
    private Scene() {
        spheres.add(new Sphere(0.5f, new Vector3f(0.0f, 0.0f, 0.0f)));
    }
}
