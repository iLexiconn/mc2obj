package net.ilexiconn.mc2obj.obj;

import org.lwjgl.util.vector.Vector2f;

import java.util.Locale;

public class TextureCoords {
	public Vector2f uvCoords;
	public int index;

	public TextureCoords(float u, float v) {
		this(new Vector2f(u, v));
	}

	public TextureCoords(Vector2f uvCoords) {
		this.uvCoords = uvCoords;
	}

	void register(ObjModel model) {
		this.index = model.nextTexIndex++;
	}

	public String toString() {
		return "vt " + String.format(Locale.US, "%.6f", this.uvCoords.x) + " " + String.format(Locale.US, "%.6f", this.uvCoords.y);
	}

	public boolean equals(Object obj) {
		if (obj instanceof TextureCoords) {
			TextureCoords uv = (TextureCoords) obj;
			return uv.uvCoords.x == this.uvCoords.x && uv.uvCoords.y == this.uvCoords.y;
		}
		return false;
	}
}
