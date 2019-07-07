package stream_API;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class WordCounter {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt", StandardCharsets.UTF_8))) {
            Map<String, Integer> map = new TreeMap<>();
            StringBuilder sb = new StringBuilder();
            Stream<String> bufferStream = bufferedReader.lines();

            bufferStream.map(String::toLowerCase)
                    .peek(System.out::println)
                    .forEach(sb::append);

            String[] words = sb.toString().toLowerCase().split("[^a-zа-я0-9]+");
            Stream<String> textStream = Arrays.stream(words);

            textStream.forEach(n ->{
                if (map.containsKey(n))
                    map.replace(n, map.get(n), map.get(n) + 1);
                else
                    map.put(n, 1);
            });

            map.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .forEach(n -> System.out.println(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
