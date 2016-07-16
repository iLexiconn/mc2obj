package net.ilexiconn.mc2obj.obj;

import org.lwjgl.util.vector.Vector3f;

public class Vertex extends Vector3f {

    private int index;

    public Vertex(float x, float y, float z) {
        super(x, y, z);
    }

    public Vertex(Vector3f src) {
        super(src);
    }

    public void multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }

    public void multiply(Vector3f vector) {
        this.x *= vector.x;
        this.y *= vector.y;
        this.z *= vector.z;
    }

    void register(ObjModel model) {
        this.index = model.nextVertexIndex++;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("v %1$.6f %2$.6f %3$.6f", this.x, this.y, this.z);
    }

}
