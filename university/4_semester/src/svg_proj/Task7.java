package svg_proj;

import java.io.FileNotFoundException;
import java.util.Random;

public class Task7 {
    public static void main(String[] args) {

        try (SVG svg = new SVG("100c150q.svg", 300, 300)) {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                new PositionedShape(new RedCircle(), random.nextInt(300), random.nextInt(300)).draw(svg);
            }
            for (int i = 0; i < 150; i++) {
                new PositionedShape(new SmallSquare(), random.nextInt(300), random.nextInt(300)).draw(svg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
