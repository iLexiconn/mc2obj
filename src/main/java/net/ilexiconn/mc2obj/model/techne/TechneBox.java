package net.ilexiconn.mc2obj.model.techne;

import net.ilexiconn.mc2obj.model.IModelBox;
import org.lwjgl.util.vector.Vector3f;

public class TechneBox implements IModelBox<TechneModel> {
	public TechneModel parentModel;
	public float textureWidth = 64.0F;
	public float textureHeight = 32.0F;
	public int textureOffsetX;
	public int textureOffsetY;
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	public boolean mirror;
	public final String name;
	public Vector3f offset;
	public float sizeX;
	public float sizeY;
	public float sizeZ;

	public TechneBox(TechneModel parentModel, String name) {
		this.parentModel = parentModel;
		this.name = name;
	}

	public void setTextureOffset(int x, int y) {
		this.textureOffsetX = x;
		this.textureOffsetY = y;
	}

	public void setRotationPoint(float x, float y, float z) {
		this.rotationPointX = x;
		this.rotationPointY = y;
		this.rotationPointZ = z;
	}

	public void setTextureSize(int x, int y) {
		if (x == 0 || y == 0) {
			return;
		}
		this.textureWidth = x;
		this.textureHeight = y;
	}

	public void setOffset(float x, float y, float z) {
		this.offset = new Vector3f(x, y, z);
	}

	public void setDimensions(float x, float y, float z) {
		this.sizeX = x;
		this.sizeY = y;
		this.sizeZ = z;
	}

	public void setRotateAngles(float x, float y, float z) {
		this.rotateAngleX = x;
		this.rotateAngleY = y;
		this.rotateAngleZ = z;
	}

	@Override
	public TechneModel getParentModel() {
		return parentModel;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Vector3f getOffset() {
		return this.offset;
	}

	@Override
	public float getSizeX() {
		return sizeX;
	}

	@Override
	public float getSizeY() {
		return sizeY;
	}

	@Override
	public float getSizeZ() {
		return sizeZ;
	}

	@Override
	public boolean isMirror() {
		return mirror;
	}

	@Override
	public float getTextureOffsetX() {
		return textureOffsetX;
	}

	@Override
	public float getTextureOffsetY() {
		return textureOffsetY;
	}

	@Override
	public float getTextureWidth() {
		return textureWidth;
	}

	@Override
	public float getTextureHeight() {
		return textureHeight;
	}

	@Override
	public float getRotationAngleX() {
		return rotateAngleX;
	}

	@Override
	public float getRotationAngleY() {
		return rotateAngleY;
	}

	@Override
	public float getRotationAngleZ() {
		return rotateAngleZ;
	}

	@Override
	public float getRotationPointX() {
		return rotationPointX;
	}

	@Override
	public float getRotationPointY() {
		return rotationPointY;
	}

	@Override
	public float getRotationPointZ() {
		return rotationPointZ;
	}
}
