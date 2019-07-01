package fractal;

public class Mandelbrot implements Fractal {
    private final int iter;

    Mandelbrot(int iter) {
        this.iter = iter;
    }

    @Override
    public double getColor(double x, double y) {
        double re = 0;
        double im = 0;
        double R = 1000000000;
        for (int i = 0; i < iter; i++) {
            double reNext = re * re - im * im + x;
            double imNext = 2 * re * im + y;
            re = reNext;
            im = imNext;
            double abs = re * re + im * im;
            if (abs > R * R) {
                double fix = Math.log(Math.log(abs) / Math.log(R) / 2) / Math.log(2);
                return (i - fix) / iter;
            }
        }
        return 1;
    }
}
