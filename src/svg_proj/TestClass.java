package svg_proj;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;

public class TestClass {
    public static void main(String[] args) {
        int svgWidth = Settings.getInstance().getWidth();
        int svgHeight = Settings.getInstance().getHeight();
        Map<String, Integer> shapesWithCount = Settings.getInstance().getShapesWithCount();
        Random random = getRandom();

        try (SVG svg = new SVG("a.svg", svgWidth, svgHeight)) {

            for (Map.Entry<String, Integer> entry : shapesWithCount.entrySet()) {
                String shapeDescription = Settings.getInstance().getShapeDiscription(entry.getKey());
                for (int i = 0; i < entry.getValue(); i++) {
                    new PositionedShape(new ShapeFactory().create(shapeDescription),
                            random.nextInt(svgWidth), random.nextInt(svgHeight)).draw(svg);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static Random getRandom() {
        Random random;
        if (Settings.getInstance().getRandSeed() == 0) {
            random = new Random();
        } else {
            random = new Random(Settings.getInstance().getRandSeed());
        }
        return random;
    }
}
