package math;

public class Matrix {
    double[][] matrix = new double[4][4];

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Vector3D multiplyMatrixVector(Vector3D vector1, Vector3D vector2, Matrix m) {
        Vector3D out = new Vector3D(0,0,0);

        out.x = vector1.x * m.matrix[0][0] + vector1.y * m.matrix[1][0] + vector1.z * m.matrix[2][0] + m.matrix[3][0];
        out.y = vector1.x * m.matrix[0][1] + vector1.y * m.matrix[1][1] + vector1.z * m.matrix[2][1] + m.matrix[3][1];
        out.z = vector1.x * m.matrix[0][2] + vector1.y * m.matrix[1][2] + vector1.z * m.matrix[2][2] + m.matrix[3][2];

        double w = vector1.x * m.matrix[0][2] + vector1.y * m.matrix[1][2] + vector1.z * m.matrix[2][2] + m.matrix[3][2];

        if(w != 0) {
            out.x /= w;
            out.y /= w;
            out.z /= w;
        }

        return out;
    }

    // Create a 1st person perspective and projects the matrix to it
    // fNear and fFar, how near or far we want objects to be rendered
    public static Matrix projectMatrix(double fNear, double fFar, double aspectRatio, double fov) {
        Matrix mat = new Matrix(new double[][]{{0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0}});

        double fFov = 1.0 / (fov * 2 / Math.PI * 180);

        return mat;
    }
}
