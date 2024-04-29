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

        mat.matrix[0][0] = aspectRatio * fov;
        mat.matrix[1][1] = fov;
        mat.matrix[2][2] = fFar / (fFar - fNear);
        mat.matrix[2][3] = 1.0;
        mat.matrix[3][2] = -fFar * fNear / (fFar - fNear);

        return mat;
    }

    public static Matrix rotationMatrixX(double angle) {
        Matrix mat = new Matrix(new double[][]{{0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0}});

        mat.matrix[0][0] = 1.0;
        mat.matrix[1][1] = Math.cos(angle);
        mat.matrix[1][2] = Math.sin(angle);
        mat.matrix[2][1] = -Math.sin(angle);
        mat.matrix[2][2] = Math.cos(angle);
        mat.matrix[3][3] = 1.0;

        return mat;
    }

    public static Matrix rotationMatrixY(double angle) {
        Matrix mat = new Matrix(new double[][]{{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});

        mat.matrix[0][0] = Math.cos(angle);
        mat.matrix[0][2] = Math.sin(angle);
        mat.matrix[1][1] = 1.0;
        mat.matrix[2][0] = -Math.sin(angle);
        mat.matrix[2][2] = Math.cos(angle);
        mat.matrix[3][3] = 1.0;

        return mat;
    }

    public static Matrix rotationMatrixZ(double angle) {
        Matrix mat = new Matrix(new double[][]{{0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0}});

        mat.matrix[0][0] = Math.cos(angle);
        mat.matrix[0][1] = Math.sin(angle);
        mat.matrix[1][0] = -Math.sin(angle);
        mat.matrix[1][1] = Math.cos(angle);
        mat.matrix[2][2] = 1.0;
        mat.matrix[3][3] = 1.0;

        return mat;
    }

    public static Matrix translationMatrix(double x, double y, double z) {
        return new Matrix(new double[][]{{1, 1, 0, 0},
                                         {0, 1, 1, 0},
                                         {x, y, z, 0},
                                         {0, 0, 0, 0}});

    }

    public static Matrix identityMatrix() {
        return new Matrix(new double[][]{{1, 0, 0, 0},
                                         {0, 1, 0, 0},
                                         {0, 0, 1, 0},
                                         {0, 0, 0, 1}});
    }

    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        Matrix mat = new Matrix(new double[][]{{0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0},
                                               {0, 0, 0, 0}});

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                mat.matrix[i][j] = matrix1.matrix[i][0] * matrix2.matrix[0][j] +
                                   matrix1.matrix[i][1] * matrix2.matrix[1][j] +
                                   matrix1.matrix[i][2] * matrix2.matrix[2][j] +
                                   matrix1.matrix[i][3] * matrix2.matrix[3][j];
            }
        }

        return mat;
    }
}
