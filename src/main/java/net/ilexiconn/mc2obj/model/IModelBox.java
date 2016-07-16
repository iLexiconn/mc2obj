package net.ilexiconn.mc2obj.model;

import org.lwjgl.util.vector.Vector3f;

public interface IModelBox<MODEL extends IModel> {

    MODEL getParentModel();

    String getName();

    Vector3f getOffset();

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
