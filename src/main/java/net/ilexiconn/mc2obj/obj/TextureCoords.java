package net.ilexiconn.mc2obj.obj;

import org.lwjgl.util.vector.Vector2f;

public class TextureCoords extends Vector2f {

    public int index;

    public TextureCoords(float u, float v) {
        super(u, v);
    }

    public TextureCoords(Vector2f uvCoords) {
        super(uvCoords);
    }

    public void multiply(float u, float v) {
        this.x *= u;
        this.y *= v;
    }

    public void multiply(Vector2f vector) {
        this.x *= vector.x;
        this.y *= vector.y;
    }

    void register(ObjModel model) {
        this.index = model.nextTexIndex++;
    }

    public String toString() {
        return String.format("vt %.6f %.6f", this.x, this.y);
    }

}
