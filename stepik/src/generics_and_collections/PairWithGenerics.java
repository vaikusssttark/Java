package generics_and_collections;

import java.util.Objects;

class PairWithGenerics<T, X> {

    private T first;
    private X second;

    private PairWithGenerics(){
        this.first = null;
        this.second = null;
    }

    private PairWithGenerics(T first, X second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public X getSecond() {
        return second;
    }

    public static <T, X> PairWithGenerics<T, X> of(T first, X second) {
        return new PairWithGenerics<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairWithGenerics)) return false;
        PairWithGenerics<?, ?> pair = (PairWithGenerics<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
