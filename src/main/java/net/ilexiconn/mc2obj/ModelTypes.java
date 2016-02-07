package net.ilexiconn.mc2obj;

import net.ilexiconn.mc2obj.model.IModel;
import net.ilexiconn.mc2obj.model.tabula.TabulaModel;
import net.ilexiconn.mc2obj.model.techne.TechneModel;

import java.io.File;

public enum ModelTypes {
	TABULA("tbl", TabulaModel.class),
	TECHNE("tcn", TechneModel.class);

	private String extension;
	private Class<? extends IModel> type;

	ModelTypes(String extension, Class<? extends IModel> type) {
		this.extension = extension;
		this.type = type;
	}

	public String getExtension() {
		return extension;
	}

	public IModel get(File file) {
		try {
			return type.getConstructor(File.class).newInstance(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ModelTypes getTypeFromExtension(String extension) {
		for (ModelTypes type : VALUES) {
			if (type.getExtension().equals(extension)) {
				return type;
			}
		}
		return null;
	}

	public static ModelTypes[] VALUES = values();
}
