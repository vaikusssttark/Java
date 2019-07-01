package exam_2019.task1;

public interface StringTransformer {
    String transform(String s);

    default String transform(String s, int times) {
        for (int i = 0; i < times; i++) {
            s = this.transform(s);
        }
        return s;
    }
}
