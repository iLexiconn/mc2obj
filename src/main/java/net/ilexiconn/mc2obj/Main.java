package net.ilexiconn.mc2obj;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		File baseDir = new File(".");
		File[] files = baseDir.listFiles();
		if (files != null) {
			Converter converter = new Converter();
			for (File file : files) {
				if (!file.isDirectory()) {
					File objFile = new File(file.getParentFile(), String.valueOf(FilenameUtils.getBaseName(file.getAbsolutePath())) + ".obj");
					ModelTypes type = ModelTypes.getTypeFromExtension(FilenameUtils.getExtension(file.getAbsolutePath()));
					if (type != null) {
						FileWriter writer = new FileWriter(objFile);
						for (String str : converter.convert(type.get(file), 0.0625F).toStringList()) {
							writer.write(str);
							writer.write("\n");
						}
						writer.close();
					}
				}
			}
		}
	}
}
