package net.ilexiconn.mc2obj;

import net.ilexiconn.mc2obj.options.RunOptions;
import com.beust.jcommander.JCommander;
import java.io.BufferedWriter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import net.ilexiconn.mc2obj.model.IModel;
import net.ilexiconn.mc2obj.obj.ObjModel;

public class Main {

    public static void main(String[] args) throws Exception {
        RunOptions options = new RunOptions();
        new JCommander(options, args);
        options.convert();
        List<File> files = Arrays.asList(new File(".").listFiles());
        files.stream()
                .filter(File::isFile)
                .map(Main::loadModel)
                .filter(Main::notNull)
                .map(Converter::convert)
                .peek(m -> m.translate(options.translate))
                .peek(m -> m.scaleTexture(options.stretch))
                .peek(m -> m.translateTexture(options.offset))
                .forEach(Main::saveObjModel);
    }

    public static IModel loadModel(File file) {
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        ModelTypes type = ModelTypes.getTypeFromExtension(extension);
        return type == null ? null : type.get(file);
    }

    public static void saveObjModel(ObjModel model) {
        File objFile = new File(model.name + ".obj");
        try (BufferedWriter writer = Files.newBufferedWriter(objFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
            System.out.printf("Saving OBJ file: \"%1$s\"!%n", objFile.getName());
            for (String str : model.toStringList()) {
                writer.write(str);
                writer.write("\n");
            }
            System.out.printf("Saved OBJ file: \"%1$s\"!%n", objFile.getName());
        } catch (IOException e) {
            System.out.printf("Unable to save OBJ file: \"%1$s\"!%n", objFile.getName());
        }
    }

    public static boolean notNull(Object obj) {
        return obj != null;
    }

}
