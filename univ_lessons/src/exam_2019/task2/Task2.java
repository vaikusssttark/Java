package exam_2019.task2;



import exam_2019.task1.StringTransformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Task2 {
    public static void main(String[] args) {
        String firstSquare = "<rect x=\"0\" y=\"0\" width=\"100\" height=\"100\"/>";
        StringTransformer st = s -> {
            StringBuilder s1 = new StringBuilder();
            for (int x = 0; x < 3; x++)
                for (int y = 0; y < 3; y++) {
                    if (x != 1 || y != 1) {
                        s1.append(String.format("<g transform=\"translate(%d, %d)\">\n\t%s\n</g>\n", x * 100, y * 100, s));
                    }
                }
            return String.format("<g transform=\"scale(0.333333333333 0.3333333333333)\">\n%s\n</g>", s1.toString());
        };

        String s = st.transform(firstSquare, 5);
        String s1 = String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"400\" height=\"400\" style=\"stroke:#000000; fill: #FFD700\">\n%s\n</svg>", s);
        try {
            Files.writeString(Paths.get("task2.svg"), s1, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }
    }
}
