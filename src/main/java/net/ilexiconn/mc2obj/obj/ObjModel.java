package net.ilexiconn.mc2obj.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjModel {

    public final String name;
    public final List<Shape> shapes;
    public int nextVertexIndex = 1;
    public int nextTexIndex = 1;

    public ObjModel(String name) {
        this.name = name;
        this.shapes = new ArrayList<>();
    }

    public void translate(Vector3f translationVector) {
        this.shapes.forEach(s -> s.translate(translationVector));
    }

    public void scale(Vector3f scaleVector) {
        this.shapes.forEach(s -> s.scale(scaleVector));
    }

    public void translateTexture(Vector2f translationVector) {
        this.shapes.forEach(s -> s.translateTexture(translationVector));
    }

    public void scaleTexture(Vector2f scaleVector) {
        this.shapes.forEach(s -> s.scaleTexture(scaleVector));
    }

    public List<String> toStringList() {
        return shapes.stream().flatMap(s -> s.toStringList().stream()).collect(Collectors.toList());
    }

}
