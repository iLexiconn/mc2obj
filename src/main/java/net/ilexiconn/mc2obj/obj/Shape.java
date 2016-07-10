package net.ilexiconn.mc2obj.obj;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Shape {
	public String name;
	public ObjModel model;
	private List<Vertex> vertices = new ArrayList<>();
	private List<TextureCoords> texCoords = new ArrayList<>();
	public List<Face> faces = new ArrayList<>();

	public Shape(ObjModel parent, String name) {
		this.model = parent;
		this.name = name;
	}

	public Vertex addVertex(Vertex newVertex) {
		for (Vertex vertex : this.vertices) {
			if (!vertex.equals(newVertex)) {
				continue;
			}
			return vertex;
		}
		this.vertices.add(newVertex);
		newVertex.register(this.model);
		return newVertex;
	}

	public TextureCoords addTexCoords(TextureCoords newUV) {
		for (TextureCoords uv : this.texCoords) {
			if (!uv.equals(newUV)) {
				continue;
			}
			return uv;
		}
		this.texCoords.add(newUV);
		newUV.register(this.model);
		return newUV;
	}

	public List<String> toStringList() {
		ArrayList<String> list = new ArrayList<>();
		list.add("o " + this.name);
		for (Vertex vertex : this.vertices) {
			list.add(vertex.toString());
		}
		for (TextureCoords uv : this.texCoords) {
			list.add(uv.toString());
		}
		for (Face face : this.faces) {
			list.add(face.toString());
		}
		return list;
	}

	public void translate(Vector3f translationVector) {
		for (Vertex vertex : this.vertices) {
			Vector3f.add(vertex, translationVector, vertex);
		}
	}

	public void scale(Vector3f scaleVector) {
		for (Vertex vertex : this.vertices) {
			vertex.x *= scaleVector.x;
			vertex.y *= scaleVector.y;
			vertex.z *= scaleVector.z;
		}
	}

	public void rotate(float angle, float x, float y, float z) {
		Matrix3f rotationMatrix = Shape.rotationMatrix(angle, x, y, z);
		for (Vertex vertex : this.vertices) {
			Matrix3f.transform(rotationMatrix, vertex, vertex);
		}
	}

	public static Matrix3f rotationMatrix(float angle, float x, float y, float z) {
		Vector3f axis = new Vector3f(x, y, z);
		axis.normalise();
		float s = (float) Math.sin(angle *= 0.017453292f);
		float c = (float) Math.cos(angle);
		float oc = 1.0F - c;
		Matrix3f mat = new Matrix3f();
		FloatBuffer buff = BufferUtils.createFloatBuffer(9);
		buff.put(new float[]{oc * axis.x * axis.x + c, oc * axis.x * axis.y - axis.z * s, oc * axis.z * axis.x + axis.y * s, oc * axis.x * axis.y + axis.z * s, oc * axis.y * axis.y + c, oc * axis.y * axis.z - axis.x * s, oc * axis.z * axis.x - axis.y * s, oc * axis.y * axis.z + axis.x * s, oc * axis.z * axis.z + c});
		buff.flip();
		mat.load(buff);
		return mat;
	}
}
