package Rendering.RayTracing;

import java.lang.Math;

import org.joml.*;

public class Sphere {
    
    float radius;
    Vector3f origin;
    Vector3f lastHitPoint;
    Vector3f sphereColor;

    public Sphere(float radius, Vector3f origin) {
        this.radius = radius;
        this.origin = origin;
        this.sphereColor = new Vector3f(1.0f, 0.0f, 1.0f);
    }

    public boolean hitSphere(Ray ray) {
        float a = ray.direction.dot(ray.direction);
        float b = 2.0f * ray.origin.dot(ray.direction);
        float c = ray.origin.dot(ray.origin) - radius * radius;

        float d = b * b - 4.0f * a * c;

        if (d < 0.0f) {
            return false;
        }

        float closetT = (-b - (float)Math.sqrt(d)) / (2.0f * a);
        lastHitPoint = ray.origin.add(ray.direction.mul(closetT));
        
        return true;
    }

    public Vector3f getLastHitpoint() {
        return this.lastHitPoint;
    }

    public Vector3f getSphereColor() {
        return this.sphereColor;
    }
}
