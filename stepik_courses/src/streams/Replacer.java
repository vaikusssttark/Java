package streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Replacer {
    public static void main(String[] args) {
        InputStream is = System.in;
        OutputStream os = System.out;

        try {
            int prev = is.read();
            if (prev == -1) return;
            int next = is.read();
            if (next == -1) {
                os.write(prev);
            }
            while (-1 != next) {
                if (prev == 13 && next == 10) {
                    os.write(next);
                    next = is.read();
                } else {
                    os.write(prev);
                }
                prev = next;
                next = is.read();
                if( -1 == next) {
                    os.write(prev);
                }
            }
            os.flush();
        } catch (IOException ignored) {
        }
    }
}
