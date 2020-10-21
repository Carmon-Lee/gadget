/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liguang
 * @version L.java, v 0.1 2020年10月21日 9:30
 */
public class L_1297_maxFrequency {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();
        int freq = 0;

        //"aababcaab"
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            //System.out.println(map);
            if (i - start + 1 < minSize) {
                continue;
            }
            while (i - start + 1 > maxSize || map.keySet().size() > maxLetters) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                if (map.get(s.charAt(start)) == 0) {
                    map.remove(s.charAt(start));
                }
                start++;
            }
            if (map.keySet().size() <= maxLetters && i - start + 1 >= minSize) {
                System.out.println(s.substring(start, i + 1));
                String t = s.substring(start, i + 1);
                res.put(t, res.getOrDefault(t, 0) + 1);
            }
        }
        for (Integer value : res.values()) {
            freq = value > freq ? value : freq;
        }
        return freq;
    }


    public static void main(String[] args) {
        new L_1297_maxFrequency().maxFreq("aabcabcab", 2, 2, 3);
    }
}