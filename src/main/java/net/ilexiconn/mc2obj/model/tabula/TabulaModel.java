package net.ilexiconn.mc2obj.model.tabula;

import com.google.gson.Gson;
import net.ilexiconn.mc2obj.model.IModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TabulaModel implements IModel {

    public int textureWidth;
    public int textureHeight;

    public final String name;

    public float[] scale = new float[]{1.0F, 1.0F, 1.0F};

    public List<TabulaBox> cubes;

    public TabulaModel(File file) {
        this.name = file.getName().split("\\.")[0];
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
    public String getName() {
        return this.name;
    }

    @Override
    public List<TabulaBox> getBoxes() {
        return this.cubes;
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
