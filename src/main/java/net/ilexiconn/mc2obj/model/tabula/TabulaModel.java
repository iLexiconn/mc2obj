package net.ilexiconn.mc2obj.model.tabula;

import com.google.gson.Gson;
import net.ilexiconn.mc2obj.model.IModel;
import net.ilexiconn.mc2obj.model.IModelBox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TabulaModel implements IModel {
	public int textureWidth;
	public int textureHeight;

	public float[] scale = new float[]{1.0F, 1.0F, 1.0F};

	public ArrayList<TabulaBox> cubes;

	public TabulaModel(File file) {
		try {
			ZipFile zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			InputStream stream = null;
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (!entry.getName().equals("model.json")) {
					continue;
				}
				stream = zipFile.getInputStream(entry);
				break;
			}
			TabulaModel model = new Gson().fromJson(new InputStreamReader(stream), TabulaModel.class);
			this.textureHeight = model.textureHeight;
			this.textureWidth = model.textureWidth;
			this.scale = model.scale;
			this.cubes = model.cubes;
			for (TabulaBox box : cubes) {
				box.parent = this;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IModelBox[] getBoxes() {
		return cubes.toArray(new TabulaBox[cubes.size()]);
	}

	@Override
	public float getScaleX() {
		return scale[0];
	}

	@Override
	public float getScaleY() {
		return scale[1];
	}

	@Override
	public float getScaleZ() {
		return scale[2];
	}
}
