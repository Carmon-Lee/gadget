/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package string;

import java.util.ArrayDeque;
import java.util.Deque;
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


    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k>=len) {
            return "0";
        }
        int retain = len-k;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0;i<len;i++) {
            while (!stack.isEmpty() && stack.size()+len-i>retain && stack.peek()>num.charAt(i)) {
                stack.pop();
            }
            stack.push(num.charAt(i));
        }
        // 0200
        while (stack.size()>0 && stack.getLast()=='0') {
            stack.removeLast();
        }
        char[] chars = new char[Math.min(retain, stack.size())];
        for (int i=0;i<chars.length;i++) {
            chars[i] = stack.removeLast();
        }
        //System.out.println(stack);
        return chars.length==0?"0": new String(chars);
    }


    public static void main(String[] args) {
//        "1234567890"
//9
        new L_1297_maxFrequency().removeKdigits("1234567890", 9);
    }
}