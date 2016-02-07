package net.ilexiconn.mc2obj.obj;

import java.util.ArrayList;
import java.util.List;

public class Face {
	public Shape parentShape;
	private List<Vertex> vertices = new ArrayList<>();
	private List<TextureCoords> uvCoords = new ArrayList<>();

	public Face(Shape shape) {
		this.parentShape = shape;
	}

	public Face append(Vertex vertex, TextureCoords uvCoords) {
		this.vertices.add(this.parentShape.addVertex(vertex));
		this.uvCoords.add(this.parentShape.addTexCoords(uvCoords));
		return this;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("f");
		int i = 0;
		while (i < this.vertices.size()) {
			sb.append(" ").append(this.vertices.get(i).index).append("/").append(this.uvCoords.get(i).index);
			++i;
		}
		return sb.toString();
	}
}
