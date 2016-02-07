package net.ilexiconn.mc2obj.obj;

import java.util.ArrayList;
import java.util.List;

public class ObjModel {
	public List<Shape> shapes = new ArrayList<>();
	public int nextVertexIndex = 1;
	public int nextTexIndex = 1;

	public List<String> toStringList() {
		ArrayList<String> lines = new ArrayList<>();
		for (Shape shape : this.shapes) {
			lines.addAll(shape.toStringList());
		}
		return lines;
	}
}
