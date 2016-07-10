package net.ilexiconn.mc2obj.model;

import java.util.List;

public interface IModel {

	String getName();

	List<? extends IModelBox> getBoxes();

	float getScaleX();

	float getScaleY();

	float getScaleZ();

}
