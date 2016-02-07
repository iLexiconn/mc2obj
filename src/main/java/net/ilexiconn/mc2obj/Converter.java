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
	public ObjModel convert(IModel model, float scale) {
		ObjModel obj = new ObjModel();
		for (IModelBox box : model.getBoxes()) {
			obj.shapes.add(this.convertBoxToShape(obj, box, scale));
		}
		return obj;
	}

	private Shape convertBoxToShape(ObjModel model, IModelBox box, float scale) {
		Shape shape = new Shape(model, box.getName());
		Vertex frontTopLeft = new Vertex(box.getOffsetX(), box.getOffsetY(), box.getOffsetZ());
		Vertex frontTopRight = new Vertex(box.getOffsetX() + box.getSizeX(), box.getOffsetY(), box.getOffsetZ());
		Vertex frontBottomRight = new Vertex(box.getOffsetX() + box.getSizeX(), box.getOffsetY() + box.getSizeY(), box.getOffsetZ());
		Vertex frontBottomLeft = new Vertex(box.getOffsetX(), box.getOffsetY() + box.getSizeY(), box.getOffsetZ());
		Vertex backTopLeft = new Vertex(box.getOffsetX(), box.getOffsetY(), box.getOffsetZ() + box.getSizeZ());
		Vertex backTopRight = new Vertex(box.getOffsetX() + box.getSizeX(), box.getOffsetY(), box.getOffsetZ() + box.getSizeZ());
		Vertex backBottomRight = new Vertex(box.getOffsetX() + box.getSizeX(), box.getOffsetY() + box.getSizeY(), box.getOffsetZ() + box.getSizeZ());
		Vertex backBottomLeft = new Vertex(box.getOffsetX(), box.getOffsetY() + box.getSizeY(), box.getOffsetZ() + box.getSizeZ());
		if (box.getSizeX() > 0.0F && box.getSizeY() > 0.0F) {
			shape.faces.add(new Face(shape).append(frontBottomLeft, this.createUV(box, box.getSizeZ(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(frontBottomRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(frontTopRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())).append(frontTopLeft, this.createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())));
			shape.faces.add(new Face(shape).append(backBottomRight, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(backBottomLeft, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX() * 2.0F, box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(backTopLeft, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX() * 2.0F, box.getSizeZ(), -box.getSizeX())).append(backTopRight, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ(), box.getSizeX())));
		}
		if (box.getSizeX() > 0.0F && box.getSizeZ() > 0.0F) {
			shape.faces.add(new Face(shape).append(frontTopLeft, this.createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())).append(frontTopRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())).append(backTopRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), 0.0F, -box.getSizeX())).append(backTopLeft, this.createUV(box, box.getSizeZ(), 0.0F, box.getSizeX())));
			shape.faces.add(new Face(shape).append(backBottomLeft, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), box.getSizeX())).append(backBottomRight, this.createUV(box, box.getSizeZ() + box.getSizeX() * 2.0F, box.getSizeZ(), -box.getSizeX())).append(frontBottomRight, this.createUV(box, box.getSizeZ() + box.getSizeX() * 2.0F, 0.0F, -box.getSizeX())).append(frontBottomLeft, this.createUV(box, box.getSizeZ() + box.getSizeX(), 0.0F, box.getSizeX())));
		}
		if (box.getSizeY() > 0.0F && box.getSizeZ() > 0.0F) {
			shape.faces.add(new Face(shape).append(backBottomLeft, this.createUV(box, 0.0F, box.getSizeZ() + box.getSizeY(), box.getSizeX() + box.getSizeZ() * 2.0F)).append(frontBottomLeft, this.createUV(box, box.getSizeZ(), box.getSizeZ() + box.getSizeY(), box.getSizeX())).append(frontTopLeft, this.createUV(box, box.getSizeZ(), box.getSizeZ(), box.getSizeX())).append(backTopLeft, this.createUV(box, 0.0F, box.getSizeZ(), box.getSizeX() + box.getSizeZ() * 2.0F)));
			shape.faces.add(new Face(shape).append(frontBottomRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX())).append(backBottomRight, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ() + box.getSizeY(), -box.getSizeX() - box.getSizeZ() * 2.0F)).append(backTopRight, this.createUV(box, box.getSizeZ() * 2.0F + box.getSizeX(), box.getSizeZ(), -box.getSizeX() - box.getSizeZ() * 2.0F)).append(frontTopRight, this.createUV(box, box.getSizeZ() + box.getSizeX(), box.getSizeZ(), -box.getSizeX())));
		}
		shape.rotate(-box.getRotationAngleX(), 1.0F, 0.0F, 0.0F);
		shape.rotate(-box.getRotationAngleY(), 0.0F, 1.0F, 0.0F);
		shape.rotate(-box.getRotationAngleZ(), 0.0F, 0.0F, 1.0F);
		shape.translate(new Vector3f(box.getRotationPointX(), box.getRotationPointY(), box.getRotationPointZ()));
		shape.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		shape.scale(new Vector3f(box.getParentModel().getScaleX() * scale, box.getParentModel().getScaleY() * scale, box.getParentModel().getScaleZ() * scale));
		return shape;
	}

	private TextureCoords createUV(IModelBox box, float baseU, float baseV, float mirrorOffset) {
		if (!box.isMirror()) {
			return new TextureCoords((box.getTextureOffsetX() + baseU) / box.getTextureWidth(), 1.0F - (box.getTextureOffsetY() + baseV) / box.getTextureHeight());
		} else {
			return new TextureCoords((box.getTextureOffsetX() + baseU + mirrorOffset) / box.getTextureWidth(), 1.0F - (box.getTextureOffsetY() + baseV) / box.getTextureHeight());
		}
	}
}
