package streams.serialize;

import java.io.*;
import java.util.Objects;

public class DeserializeObject implements Serializable {
    public static void main(String[] args) {

    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        ByteArrayInputStream bytes = new ByteArrayInputStream(data);
        try {
            ObjectInputStream oos = new ObjectInputStream(bytes);
            Animal[] animals = new Animal[oos.readInt()];
            for (int i = 0; i < animals.length; i++) {
                animals[i] = (Animal) oos.readObject();
            }
            bytes.close();
            oos.close();
            return animals;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
