package Rendering.RayTracing;
import org.joml.*;

public class Ray {
    
    Vector3f origin;
    Vector3f direction;

    public Ray(Vector3f origin, Vector3f direction) {
        this.origin = origin;
        this.direction = direction;
    }
}
