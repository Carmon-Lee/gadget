/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */

import java.util.*;

/**
 * @author liguang
 * @version Solution.java, v 0.1 2020年09月02日 9:55 下午
 */
class Solution {


    private List<String> res = new ArrayList<>();
    private String s;
    private Set<String> dict = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        res.clear();
        dict.clear();
        dict.addAll(wordDict);
        backtrace(0, new StringBuilder());
        return res;
    }

    private void backtrace(int start, StringBuilder sb) {
        if (start >= s.length()) {
            if (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String substring = s.substring(start, i + 1);
            if (!dict.contains(substring)) {
                continue;
            }
            sb.append(substring).append(' ');
            backtrace(i + 1, sb);
            if (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.delete(sb.length() - substring.length(), sb.length());
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("a", Arrays.asList("a")));
        System.out.println(new Solution().wordBreak("aaa", Arrays.asList("a")));
        System.out.println(new Solution().wordBreak("ab", Arrays.asList("a", "ab", "b")));
        System.out.println(new Solution().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
//        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
        System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")).size());
        System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")).size());

    }
}


class ListNode {
    ListNode next;
    int value;
}