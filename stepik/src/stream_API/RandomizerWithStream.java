package stream_API;

import java.util.stream.IntStream;

public class RandomizerWithStream {
    public static void main(String[] args) {
        pseudoRandomStream(13).forEach(System.out::println);
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, n -> ((n * n) % 10000) / 10);
    }
}
