package fractal;

import javafx.scene.paint.Color;

public class Palette1 implements Palette {

    @Override
    public Color getColor(double id) {
        for (int i = 0; i < 5; i++) {
            if (id >= i * 0.2 && id <= i * 0.2 + 0.1)
                return Color.gray((1 - (i * 0.2 + 0.1 - id) * 10));
            else if (id >= i * 0.2 + 0.1 && id <= i * 0.2 + 0.2)
                return Color.gray((i * 0.2 + 0.2 - id) * 10);
        }
        return Color.gray(1 - Math.min(10 * id, 1));
    }
}
