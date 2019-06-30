import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

//Реализация вычисления контрольной суммы массива байт, считываемого с потока

public class CheckSum {
    public static void main(String[] args) {
        InputStream stream;
        int result;
        stream = getStream(new byte[]{0x33, 0x45, 0x01});

        try {
            result = checkSumOfStream(stream);
            System.out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static InputStream getStream(byte[] data) {
        return new ByteArrayInputStream(data);
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int res = 0;
        int data;
        while((data = inputStream.read()) != -1) {
            res = Integer.rotateLeft(res, 1) ^ data;
        }
        return res;
    }

}


