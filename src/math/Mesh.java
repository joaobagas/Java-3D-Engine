package math;

import java.util.ArrayList;
import java.util.List;

// This mesh class will be used to generate 3D objects.
public class Mesh {
    public List<Triangle> triangles = new ArrayList<>();

    public Mesh(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public void loadObjectFromFile() {

    }
}
