package graph;

import java.util.*;

/**
 * @author guang.li
 * @version L_127.java v 1.0 2020/11/5 9:41
 */
public class L_127 {

    private String endWord;
    private Set<String> visted;
    private Set<String> patVisted;
    private Set<String> dict;
    private Map<String, Set<String>> wordPattern;
    private Map<String, Set<String>> patternMap;
    private int length = 0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        if (beginWord.length()==0 && endWord.length()==0) {
            return 1;
        }
        dict = new HashSet<>(wordList);
        visted = new HashSet<>();
        patVisted = new HashSet<>();
        if (!dict.contains(endWord)) {
            return 0;
        }
        this.endWord = endWord;
        wordPattern = new HashMap<>();
        patternMap = new HashMap<>();

        wordList.add(beginWord);
        for (String word:wordList) {
            List<String> patterns = pattern(word);
            patternMap.putIfAbsent(word, new HashSet<>());
            patternMap.get(word).addAll(patterns);
            for (String p:patterns) {
                wordPattern.putIfAbsent(p, new HashSet<>());
                wordPattern.get(p).add(word);
            }
        }
        visted.add(beginWord);
        backtrace(beginWord);
        return length;
    }

    private void backtrace(String curWord) {
        if (curWord.equals(endWord)) {
            //System.out.println(visted);
            length = length==0 ? visted.size() : Math.min(length, visted.size());
        }
        Set<String> patterns = patternMap.get(curWord);
        for (String pattern:patterns) {
            if (patVisted.contains(pattern)) {
                continue;
            }
            Set<String> words = wordPattern.get(pattern);
            for (String w:words) {
                if (!dict.contains(w) || visted.contains(w) || curWord.equals(w) || patVisted.contains(pattern)) {
                    continue;
                }
                visted.add(w);
                patVisted.add(pattern);
                backtrace(w);
                visted.remove(w);
            }
            patVisted.remove(pattern);
        }
    }

    private List<String> pattern(String word) {
        char[] chars = word.toCharArray();
        List<String> list = new ArrayList<>();
        for (int i=0;i<chars.length;i++) {
            char c = chars[i];
            chars[i] = '*';
            list.add(String.valueOf(chars));
            chars[i] = c;
        }
        return list;
    }

    public static void main(String[] args) {
//
//    "qa"
//"sq"
//["si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"]

        System.out.println(new L_127().ladderLength("qa","sq",new ArrayList<>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"))));
    }
}
