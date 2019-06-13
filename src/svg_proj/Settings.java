package svg_proj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {
    private static Settings instance = new Settings();
    private String background;
    private int width;
    private int height;
    private Map<String, Integer> shapesWithCount = new HashMap<>();
    private String randSeed;
    private Properties prop = new Properties();

    private Settings() {
        try {
            prop.load(new InputStreamReader(new FileInputStream("svg.properties"), StandardCharsets.UTF_8));
            this.width = Integer.parseInt(prop.getProperty("width"));
            this.height = Integer.parseInt(prop.getProperty("height"));
            this.randSeed = prop.getProperty("rand_seed");
            this.background = prop.getProperty("background");
        } catch (IOException e) {
            System.out.println("Файл svg.properties не найден");
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return instance;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getBackground() {
        return this.background;
    }

    public long getRandSeed() {
        if (this.randSeed.equals("auto"))
            return 0;
        else
            return Long.parseLong(this.randSeed);
    }

    public String getShapeDiscription(String shapeName) {
        return this.prop.getProperty("shape." + shapeName);
    }

    public Map<String, Integer> getShapesWithCount() {
        StringBuilder draw = new StringBuilder(prop.getProperty("draw"));
        while (draw.indexOf(":") != -1) {
            if (draw.indexOf(" ") != -1) {
                int startID = draw.indexOf(":");
                int endID = draw.indexOf(" ");
                shapesWithCount.put(draw.substring(0, startID), Integer.parseInt(draw.substring(startID + 1, endID)));
                draw.delete(0, endID + 1);
            } else {
                int startID = draw.indexOf(":");
                shapesWithCount.put(draw.substring(0, startID), Integer.parseInt(draw.substring(startID + 1, draw.length())));
                draw.delete(0, draw.length());
            }
        }
        return shapesWithCount;
    }


}
