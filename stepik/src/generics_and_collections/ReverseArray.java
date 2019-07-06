package generics_and_collections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ReverseArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int flag = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (flag % 2 != 0) {
                    list.add(Integer.valueOf(stringTokenizer.nextToken()));
                } else
                    stringTokenizer.nextToken();

                flag++;
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.print(list.get(i) + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
