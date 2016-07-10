package net.ilexiconn.mc2obj.model.techne;

import net.ilexiconn.mc2obj.model.IModel;
import net.ilexiconn.mc2obj.model.IModelBox;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TechneModel implements IModel {
	public List<TechneBox> boxes = new ArrayList<>();
	public float scaleX = 1.0F;
	public float scaleY = 1.0F;
	public float scaleZ = 1.0F;
	public int textureSizeX = 64;
	public int textureSizeY = 32;
	private final String filename;
	private final String name;

	public TechneModel(File file) {
		this.filename = file.getName();
		this.name = this.filename.split("\\.")[0];
		this.loadTechneModel(file);
	}

	@Override
	public String getName() {
		return this.name;
	}

	private void loadTechneModel(File file) {
		try {
			ZipFile zipFile = new ZipFile(file);
			InputStream stream = null;
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (!entry.getName().equals("model.xml")) {
					continue;
				}
				stream = zipFile.getInputStream(entry);
				break;
			}
			byte[] modelXml = IOUtils.toByteArray(stream);
			zipFile.close();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new ByteArrayInputStream(modelXml));
			NodeList nodeListModel = document.getElementsByTagName("Model");
			NodeList nodes = nodeListModel.item(0).getChildNodes();
			int i = 0;
			while (i < nodes.getLength()) {
				Node node = nodes.item(i);
				if (node.getNodeName().equals("GlScale")) {
					String[] scale = node.getTextContent().split(",");
					this.scaleX = Float.parseFloat(scale[0]);
					this.scaleY = Float.parseFloat(scale[1]);
					this.scaleZ = Float.parseFloat(scale[2]);
				}
				if (node.getNodeName().equals("TextureSize")) {
					String[] textureSize = node.getTextContent().split(",");
					this.textureSizeX = Integer.parseInt(textureSize[0]);
					this.textureSizeY = Integer.parseInt(textureSize[1]);
				}
				++i;
			}
			NodeList shapes = document.getElementsByTagName("Shape");
			int i2 = 0;
			while (i2 < shapes.getLength()) {
				Node shape = shapes.item(i2);
				NamedNodeMap shapeAttributes = shape.getAttributes();
				if (shapeAttributes == null) {
					throw new RuntimeException("Shape #" + (i2 + 1) + " in " + this.filename + " has no attributes");
				}
				Node name = shapeAttributes.getNamedItem("name");
				String shapeName = null;
				if (name != null) {
					shapeName = name.getNodeValue();
				}
				if (shapeName == null) {
					shapeName = "Shape #" + (i2 + 1);
				}
				try {
					boolean mirrored = false;
					String[] offset = new String[3];
					String[] position = new String[3];
					String[] rotation = new String[3];
					String[] size = new String[3];
					String[] textureOffset = new String[2];
					NodeList shapeChildren = shape.getChildNodes();
					int j = 0;
					while (j < shapeChildren.getLength()) {
						Node shapeChild = shapeChildren.item(j);
						String shapeChildName = shapeChild.getNodeName();
						String shapeChildValue = shapeChild.getTextContent();
						if (shapeChildValue != null) {
							shapeChildValue = shapeChildValue.trim();
							switch (shapeChildName) {
								case "IsMirrored":
									mirrored = !shapeChildValue.equals("False");
									break;
								case "Offset":
									offset = shapeChildValue.split(",");
									break;
								case "Position":
									position = shapeChildValue.split(",");
									break;
								case "Rotation":
									rotation = shapeChildValue.split(",");
									break;
								case "Size":
									size = shapeChildValue.split(",");
									break;
								case "TextureOffset":
									textureOffset = shapeChildValue.split(",");
									break;
							}
						}
						++j;
					}
					TechneBox box = new TechneBox(this, shapeName);
					box.setTextureOffset(Integer.parseInt(textureOffset[0]), Integer.parseInt(textureOffset[1]));
					box.mirror = mirrored;
					box.setOffset(Float.parseFloat(offset[0]), Float.parseFloat(offset[1]), Float.parseFloat(offset[2]));
					box.setDimensions(Integer.parseInt(size[0]), Integer.parseInt(size[1]), Integer.parseInt(size[2]));
					box.setRotationPoint(Float.parseFloat(position[0]), Float.parseFloat(position[1]) - 23.4f, Float.parseFloat(position[2]));
					box.setRotateAngles(Float.parseFloat(rotation[0]), Float.parseFloat(rotation[1]), Float.parseFloat(rotation[2]));
					box.setTextureSize(this.textureSizeX, this.textureSizeY);
					this.boxes.add(box);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				++i2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TechneBox> getBoxes() {
		return boxes;
	}

	@Override
	public float getScaleX() {
		return scaleX;
	}

	@Override
	public float getScaleY() {
		return scaleY;
	}

	@Override
	public float getScaleZ() {
		return scaleZ;
	}
}
