package exam_2019.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Task1 {

    public static void main(String[] args) {
        String s = "F";
        StringTransformer stringTransformer = s1 -> s1.replace(s1, s1 + "+" + s1 + "--" + s1 + "+" + s1);
        s = stringTransformer.transform(s, 10);
        try {
            Files.writeString(Paths.get("task1.txt"), s, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }

    }
}
