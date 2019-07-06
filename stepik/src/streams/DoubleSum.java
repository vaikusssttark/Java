package streams;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DoubleSum {
    public static void main(String[] args) {
        System.out.format("%.6f",sum(System.in));
    }

    public static double sum(InputStream si) {
        Scanner sc = new Scanner(si);
        double x = 0;
        String s = sc.nextLine();
        if (sc.hasNextLine())
            s += sc.nextLine();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            try {
                x += Double.parseDouble(st.nextToken());
            } catch (NumberFormatException ignore) {
            }
        }
        sc.close();

        return x;
    }
}
