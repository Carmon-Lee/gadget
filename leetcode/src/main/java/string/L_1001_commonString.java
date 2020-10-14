package string;

import java.util.ArrayList;
import java.util.List;

public class L_1001_commonString {

    public List<String> commonChars(String[] strs) {
        List<String> res = new ArrayList<>();
        if (strs.length==0) {
            return res;
        }
        int[] letterCount = new int[26];
        int mask = 0xff;

        String first = strs[0];
        for (char c : first.toCharArray()) {
            letterCount[c - 'a']++;
        }

        for (int j = 1; j < strs.length; j++) {
            for (char c : strs[j].toCharArray()) {
                letterCount[c - 'a'] = (letterCount[c - 'a'] & mask) | (((letterCount[c - 'a'] >> 8) + 1) << 8);
            }
            for (int i = 0; i < 26; i++) {
                letterCount[i] = Math.min(letterCount[i] & mask, letterCount[i] >> 8);
            }
        }


        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < letterCount[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new L_1001_commonString().commonChars(new String[]{"cool", "cook"}));
    }
}
