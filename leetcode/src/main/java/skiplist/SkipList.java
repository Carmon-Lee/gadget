package skiplist;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liguang
 * Created on 2021-02-18
 */
public class SkipList {

    public static void main(String[] args) {
        int nodeCount = 16;
        double p = 0.5;
        Random random = new Random();
        int limit = 64;

        Map<Integer, Integer> nodeLevels = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            int level = 1;
            while (random.nextDouble() < p && level < limit) {
                level++;
            }
            nodeLevels.put(level, nodeLevels.getOrDefault(level, 0) + 1);
        }
        System.out.println(nodeLevels);
    }
}
