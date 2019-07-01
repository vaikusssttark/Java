package streams;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConvertBytesToString {

    public static void main(String[] args) throws IOException {
        System.setIn(new ByteArrayInputStream(new byte[] {48, 49, 50, 51}));
        System.out.println(readAsString(System.in, StandardCharsets.US_ASCII));
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        Reader is = new InputStreamReader(inputStream, charset);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = is.read();
            if (i == -1) {
                break;
            }
            sb.append((char) i);
        }

        return sb.toString();
    }
}
