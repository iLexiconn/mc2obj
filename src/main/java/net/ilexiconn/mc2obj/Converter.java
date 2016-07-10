package net.ilexiconn.mc2obj;

import net.ilexiconn.mc2obj.model.IModel;
import net.ilexiconn.mc2obj.model.IModelBox;
import org.lwjgl.util.vector.Vector3f;
import net.ilexiconn.mc2obj.obj.Face;
import net.ilexiconn.mc2obj.obj.ObjModel;
import net.ilexiconn.mc2obj.obj.Shape;
import net.ilexiconn.mc2obj.obj.TextureCoords;
import net.ilexiconn.mc2obj.obj.Vertex;

public class Converter {
	
	public static final float SCALE = 0.0625F;

	public static ObjModel convert(IModel model) {
		ObjModel obj = new ObjModel(model.getName());
		model.getBoxes().stream()
				.map(box -> convertBoxToShape(obj, box, SCALE))
				.forEach(obj.shapes::add);
		return obj;
	}

	private static Shape convertBoxToShape(ObjModel model, IModelBox box, float scale) {
		Shape shape = new Shape(model, box.getName());
		Vertex frontTopLeft = new Vertex(box.getOffset());
		Vertex frontTopRight = new Vertex(box.getOffset().x + box.getSizeX(), box.getOffset().y, box.getOffset().z);
		Vertex frontBottomRight = new Vertex(box.getOffset().x + box.getSizeX(), box.getOffset().y + box.getSizeY(), box.getOffset().z);
		Vertex frontBottomLeft = new Vertex(box.getOffset().x, box.getOffset().y + box.getSizeY(), box.getOffset().z);
		Vertex backTopLeft = new Vertex(box.getOffset().x, box.getOffset().y, box.getOffset().z + box.getSizeZ());
		Vertex backTopRight = new Vertex(box.getOffset().x + box.getSizeX(), box.getOffset().y, box.getOffset().z + box.getSizeZ());
		Vertex backBottomRight = new Vertex(box.getOffset().x + box.getSizeX(), box.getOffset().y + box.getSizeY(), box.getOffset().z + box.getSizeZ());
		Vertex backBottomLeft = new Vertex(box.getOffset().x, box.getOffset().y + box.getSizeY(), box.getOffset().z + box.getSizeZ());
		if (box.getSizeX() > 0.0F && box.getSizeY() > 0.0F) {
			shape.faces.add(new Face(shape).append(frontBottomLeft, createUV(box, box.getSizeZ(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(frontBottomRight, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(frontTopRight, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())).append(frontTopLeft, createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())));
			shape.faces.add(new Face(shape).append(backBottomRight, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(backBottomLeft, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX() * 2.0F, box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(backTopLeft, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX() * 2.0F, box.getSizeZ(), -box.getSizeX())).append(backTopRight, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ(), box.getSizeX())));
		}
		if (box.getSizeX() > 0.0F && box.getSizeZ() > 0.0F) {
			shape.faces.add(new Face(shape).append(frontTopLeft, createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())).append(frontTopRight, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())).append(backTopRight, createUV(box, box.getSizeZ() + box.getSizeX(), 0.0F, -box.getSizeX())).append(backTopLeft, createUV(box, box.getSizeZ(), 0.0F, box.getSizeX())));
			shape.faces.add(new Face(shape).append(backBottomLeft, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), box.getSizeX())).append(backBottomRight, createUV(box, box.getSizeZ() + box.getSizeX() * 2.0F, box.getSizeZ(), -box.getSizeX())).append(frontBottomRight, createUV(box, box.getSizeZ() + box.getSizeX() * 2.0F, 0.0F, -box.getSizeX())).append(frontBottomLeft, createUV(box, box.getSizeZ() + box.getSizeX(), 0.0F, box.getSizeX())));
		}
		if (box.getSizeY() > 0.0F && box.getSizeZ() > 0.0F) {
			shape.faces.add(new Face(shape).append(backBottomLeft, createUV(box, 0.0F, box.getSizeZ() + box.getSizeY(), box.getSizeX() + box.getSizeZ() * 2.0F)).append(frontBottomLeft, createUV(box, box.getSizeZ(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(frontTopLeft, createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())).append(backTopLeft, createUV(box, 0.0F, box.getSizeZ(), box.getSizeX() + box.getSizeZ() * 2.0F)));
			shape.faces.add(new Face(shape).append(frontBottomRight, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(backBottomRight, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX() - box.getSizeZ() * 2.0F)).append(backTopRight, createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ(), -box.getSizeX() - box.getSizeZ() * 2.0F)).append(frontTopRight, createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())));
		}
		shape.rotate(-box.getRotationAngleX(), 1.0F, 0.0F, 0.0F);
		shape.rotate(-box.getRotationAngleY(), 0.0F, 1.0F, 0.0F);
		shape.rotate(-box.getRotationAngleZ(), 0.0F, 0.0F, 1.0F);
		shape.translate(new Vector3f(box.getRotationPointX(), box.getRotationPointY(), box.getRotationPointZ()));
		shape.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		shape.scale(new Vector3f(box.getParentModel().getScaleX() * scale, box.getParentModel().getScaleY() * scale, box.getParentModel().getScaleZ() * scale));
		return shape;
	}

	private static TextureCoords createUV(IModelBox box, float baseU, float baseV, float mirrorOffset) {
		if (!box.isMirror()) {
			return new TextureCoords((box.getTextureOffsetX() + baseU) / box.getTextureWidth(), 1.0F - (box.getTextureOffsetY() + baseV) / box.getTextureHeight());
		} else {
			return new TextureCoords((box.getTextureOffsetX() + baseU + mirrorOffset) / box.getTextureWidth(), 1.0F - (box.getTextureOffsetY() + baseV) / box.getTextureHeight());
		}
	}
}
