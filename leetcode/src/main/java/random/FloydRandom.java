package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloydRandom {

    List<Integer> random(int m, int n) {
        Random rand = new Random();
        List<Integer> output = new ArrayList<>();
        for (int i = n - m + 1; i <= n; i++) {
            int value = rand.nextInt(i);
            if (!output.contains(value)) {
                output.add(value);
            } else {
                output.add(i);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(new FloydRandom().random(10, 10));
    }
}
