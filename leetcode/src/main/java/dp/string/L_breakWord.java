/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.string;

import java.util.*;

/**
 * @author liguang
 * @version L_breakWord.java, v 0.1 2020年10月26日 12:49
 */
public class L_breakWord {

    private boolean res;
    private int minLen;
    private int maxLen;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        minLen = Integer.MAX_VALUE;
        maxLen = Integer.MIN_VALUE;
        for (String w:wordDict) {
            int len = w.length();
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
            set.add(w);
        }
        //System.out.println(set);
        res = false;
        backtrace(s, 0, set);
        return res;
    }

    private void backtrace(String s, int idx, Set<String> set) {
        if (idx>=s.length()) {
            res = true;
            return;
        }

        BitSet bitSet = new BitSet(10);
        bitSet.set(0);
        bitSet.get(0);
        for (int i=Math.min(idx+maxLen, s.length()); i>=idx+minLen;i--) {
            String t = s.substring(idx, i);
            //System.out.println(t);
            if (set.contains(t)) {
                backtrace(s, i, set);
                if (res) {
                    return;
                }
            }
        }
    }

//    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
//["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    public static void main(String[] args) {
        new L_breakWord().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
    }
}