package objects_and_classes;

import java.util.function.DoubleUnaryOperator;

//Реализация числ. интегрирования заданной функции с помощью метода левых прямоугольников

public class NumIntegration {

    public static void main(String[] args) {
        System.out.println(integrate(x -> 1, 0, 10));
    }

    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double i = a;
        double step = 0.000001;
        double sum = 0;
        while (Double.compare(i, b) <= 0) {
            sum += f.applyAsDouble(i) * (step);
            i += step;
        }
        return sum;
    }
}
