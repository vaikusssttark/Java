package fractal;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawFractal extends Application {
    private double x0 = -2;
    private double y0 = 2;
    private double interval = 0.01;
    private Pane pane = new Pane();
    private WritableImage fractalImage;
    private ImageView imageView = new ImageView();
    private Palette palette = new Palette1();
    private int iter = 1000;
    private Fractal fractal = new Mandelbrot(iter);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fractals");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> keyPressed(event.getCode()));
        primaryStage.show();
    }

    private Parent initInterface() {
        fractalImage = drawFractalImage(400, 400);
        imageView.setImage(fractalImage);
        pane.getChildren().addAll(imageView);
        return pane;
    }

    private WritableImage drawFractalImage(int width, int height) {

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                double x = x0 + i * interval;
                double y = y0 - j * interval;
                double colorInd = fractal.getColor(x, y);
                Color color = palette.getColor(colorInd);
                pixelWriter.setColor(i, j, color);
            }
        }
        return writableImage;
    }

    private void keyPressed(KeyCode pressedKey) {
        double zoom = 1.3;
        int step = 100;
        switch (pressedKey) {
            case UP:
                y0 += step * interval;
                break;
            case DOWN:
                y0 -= step * interval;
                break;
            case LEFT:
                x0 -= step * interval;
                break;
            case RIGHT:
                x0 += step * interval;
                break;
            case ADD:
                x0 += 0.5 * pane.getWidth() * (interval - interval / zoom);
                y0 -= 0.5 * pane.getHeight() * (interval - interval / zoom);
                interval /= zoom;
                break;
            case SUBTRACT:
                x0 += 0.5 * pane.getWidth() * (interval - interval * zoom);
                y0 -= 0.5 * pane.getHeight() * (interval - interval * zoom);
                interval *= zoom;
                break;
        }
        updateImage();
    }

    private void updateImage() {
        fractalImage = drawFractalImage((int) (pane.getWidth()), (int) pane.getHeight());
        imageView.setImage(fractalImage);
    }
}
