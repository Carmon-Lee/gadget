package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author guang.li
 * @version FunctionTRime.java v 1.0 2020/11/3 13:53
 */
public class FunctionTRime {

    //n = 2
    //logs =
    //["0:start:0",
    // "1:start:2",
    // "1:end:5",
    // "0:end:10"]
//    2  4  1  1
//["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]

    //输出:[4, 4, 3]
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] output = new int[n];
        Deque<Integer> idStack = new ArrayDeque<>();
        Deque<Integer> timestampStack = new ArrayDeque<>();

        for (int i = 1; i < logs.size(); i++) {
            String[] prevLog = logs.get(i - 1).split(":");
            String[] curLog = logs.get(i).split(":");

            int prevId = Integer.parseInt(prevLog[0]);
            int curId = Integer.parseInt(curLog[0]);
            if (prevId == curId && "start".equals(prevLog[1]) && "end".equals(curLog[1])) {
                output[prevId] += Integer.parseInt(curLog[2]) - Integer.parseInt(prevLog[2]) + 1;
            } else if ("start".equals(prevLog[1])) {
                output[prevId] += Integer.parseInt(curLog[2]) - Integer.parseInt(prevLog[2]);
            } else if ("end".equals(curLog[1])) {
                output[curId] += Integer.parseInt(curLog[2]) - Integer.parseInt(prevLog[2]);
            }
        }

        return output;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FunctionTRime().exclusiveTime(2, Arrays.asList(
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:10"
        ))));

        System.out.println(Arrays.toString(new FunctionTRime().exclusiveTime(1, Arrays.asList(
                "0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"
        ))));

//        2
//["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
        //
    }
}
