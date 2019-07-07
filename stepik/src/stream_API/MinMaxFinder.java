package stream_API;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinMaxFinder {
    public static void main(String[] args) {

    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.collect(Collectors.toList());
        Optional<T> min = list.stream().min(order);
        Optional<T> max = list.stream().max(order);
        if (min.isPresent())
            minMaxConsumer.accept(min.get(), max.get());
        else
            minMaxConsumer.accept(null,null);

    }
}
