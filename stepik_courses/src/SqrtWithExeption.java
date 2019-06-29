//Реализован вызов исключения в собств. методе

public class SqrtWithExeption {
    public static void main(String[] args) {
        System.out.println(sqrt(-2));
    }

    public static double sqrt(double x) {
        if (x < 0)
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        else
            return Math.sqrt(x);
    }
}
