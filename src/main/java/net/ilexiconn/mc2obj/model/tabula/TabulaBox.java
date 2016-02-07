package net.ilexiconn.mc2obj.model.tabula;

import net.ilexiconn.mc2obj.model.IModelBox;

public class TabulaBox implements IModelBox<TabulaModel> {
	public TabulaModel parent;
	public String name;
	public int[] dimensions = new int[3];

	public float[] position = new float[3];
	public float[] offset = new float[3];
	public float[] rotation = new float[3];

	public int[] txOffset = new int[2];
	public boolean txMirror = false;

	@Override
	public TabulaModel getParentModel() {
		return parent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getOffsetX() {
		return offset[0];
	}

	@Override
	public float getOffsetY() {
		return offset[1];
	}

	@Override
	public float getOffsetZ() {
		return offset[2];
	}

	@Override
	public float getSizeX() {
		return dimensions[0];
	}

	@Override
	public float getSizeY() {
		return dimensions[1];
	}

	@Override
	public float getSizeZ() {
		return dimensions[2];
	}

	@Override
	public boolean isMirror() {
		return txMirror;
	}

	@Override
	public float getTextureOffsetX() {
		return txOffset[0];
	}

	@Override
	public float getTextureOffsetY() {
		return txOffset[1];
	}

	@Override
	public float getTextureWidth() {
		return getParentModel().textureWidth;
	}

	@Override
	public float getTextureHeight() {
		return getParentModel().textureHeight;
	}

	@Override
	public float getRotationAngleX() {
		return rotation[0];
	}

	@Override
	public float getRotationAngleY() {
		return rotation[1];
	}

	@Override
	public float getRotationAngleZ() {
		return rotation[2];
	}

	@Override
	public float getRotationPointX() {
		return position[0];
	}

	@Override
	public float getRotationPointY() {
		return position[1];
	}

	@Override
	public float getRotationPointZ() {
		return position[2];
	}
}
