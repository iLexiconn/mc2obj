package net.ilexiconn.mc2obj.model;

public interface IModelBox<MODEL extends IModel> {
	MODEL getParentModel();

	String getName();

	float getOffsetX();

	float getOffsetY();

	float getOffsetZ();

	float getSizeX();

	float getSizeY();

	float getSizeZ();

	boolean isMirror();

	float getTextureOffsetX();

	float getTextureOffsetY();

	float getTextureWidth();

	float getTextureHeight();

	float getRotationAngleX();

	float getRotationAngleY();

	float getRotationAngleZ();

	float getRotationPointX();

	float getRotationPointY();

	float getRotationPointZ();
}
