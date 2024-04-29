package math;

public class Vector3D {
    public double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D addVectors(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(vector1.x + vector2.x,
                            vector1.y + vector2.y,
                            vector1.z + vector2.z);
    }

    public static Vector3D subtractVectors(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(vector1.x - vector2.x,
                            vector1.y - vector2.y,
                            vector1.z - vector2.z);
    }

    public static Vector3D multiplyVectors(Vector3D vector, double f) {
        return new Vector3D(vector.x * f,
                            vector.y * f,
                            vector.z * f);
    }

    public static Vector3D divideVectors(Vector3D vector, double f) {
        return new Vector3D(vector.x / f,
                            vector.y / f,
                            vector.z / f);
    }

    // This is used to project two vectors and see how similar they both are
    public static double calculateDotProduct(Vector3D vector1, Vector3D vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z;
    }

    public static double calculateLength(Vector3D vector) {
        return Math.sqrt(calculateDotProduct(vector, vector));
    }

    public static Vector3D normalize(Vector3D vector) {
        double length = calculateLength(vector);
        return new Vector3D(vector.x / length,
                            vector.y / length,
                            vector.z / length);
    }

    // This is used to calculate the surface normal
    public static Vector3D calculateCrossProduct(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(vector1.y * vector2.z + vector1.z * vector2.y,
                            vector1.z * vector2.x + vector1.x * vector2.z,
                            vector1.x * vector2.y + vector1.y * vector2.x);
    }
}
