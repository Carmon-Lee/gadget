/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author liguang
 * @version NextNumber.java, v 0.1 2020年10月20日 18:17
 */
public class NextNumber {

    public int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();

        Deque<Integer> deque = new ArrayDeque<>();
        // 12265  12526
        // 562
        int left = -1, right = -1;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[chars.length - 1 - i];
            while (!deque.isEmpty() && chars[deque.peek()] > ch) {
                int p = deque.pop();
                left = chars.length - 1 - i;
                right = p;
            }
            if (left != -1) {
                break;
            }
            deque.push(chars.length - 1 - i);
        }

        if (left == -1) {
            return -1;
        }

        char t = chars[left];
        chars[left] = chars[right];
        chars[right] = t;

        if (left < chars.length - 1) {
            char[] sort = new char[chars.length- 1- left];
            System.arraycopy(chars, left + 1, sort, 0, chars.length- 1- left);
            Arrays.sort(sort);
            System.arraycopy(sort, 0, chars, left + 1, chars.length- 1- left);
        }
        s  = new String(chars);
        if (Long.parseLong(s)>Integer.MAX_VALUE) {
            return -1;
        }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        System.out.println(new NextNumber().nextGreaterElement(12304));
        System.out.println(new NextNumber().nextGreaterElement(12403));
        System.out.println(new NextNumber().nextGreaterElement(12043));
        System.out.println(new NextNumber().nextGreaterElement(11));
    }
}