package tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Pair<Integer, String[]> integerPair = internalSerialize(root, 0);
        return String.join(",", Arrays.asList(integerPair.getValue()));
    }

    private Pair<Integer, String[]> internalSerialize(TreeNode root, int height) {
        if (root == null) {
            return Pair.of(height, new String[]{"n"});
        }
        Pair<Integer, String[]> leftPair = internalSerialize(root.left, height + 1);
        Pair<Integer, String[]> rightPair = internalSerialize(root.right, height + 1);
        List<String> mergeArr = new ArrayList<>();

        int maxHeight = Math.max(leftPair.getKey(), rightPair.getKey());
        int heightDiff = maxHeight - height;
        int leftFullSize = 2 << heightDiff - 1;
        String[] leftSerStr = leftPair.getValue();
        if (leftSerStr.length < leftFullSize) {
            String[] expand = new String[leftFullSize];
            System.arraycopy(leftSerStr, 0, leftSerStr, 0, leftSerStr.length);
            for (int i = leftSerStr.length; i < leftFullSize; i++) {
                expand[i] = "n";
                leftSerStr = expand;
            }
        }
        mergeArr.addAll(Arrays.asList(leftSerStr));
        mergeArr.addAll(Arrays.asList(rightPair.getValue()));

        return Pair.of(maxHeight, mergeArr.toArray(new String[0]));
    }

    // Decodes your encoded data to tree.
    // 1,2,n,n,3,4,n,n,5,n,n
    public TreeNode deserialize(String data) {

        return null;
    }

    public int[] countBits(int num) {
        if (num==0) {
            return new int[]{0};
        }
        num++;
        int[] output = new int[num];
        output[1] = 1;
        int cur = 1;
        int len = 1;
        for (;cur+len<num;) {
            System.arraycopy(output, cur, output, cur+len, Math.min(len, num-cur-len));
            cur += len;
            if (cur+len>=num) {
                break;
            }
            System.arraycopy(output, cur, output, cur+len, Math.min(len, num-cur-len));
            for (int j=0; j<Math.min(len, num-cur-len); j++) {
                output[cur+len+j] ++;
            }
            cur += len;
            len *= 2;
        }
        return output;
    }


    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Codec().countBits(2)));
//        System.out.println(Arrays.toString(new Codec().countBits(3)));
//        System.out.println(Arrays.toString(new Codec().countBits(4)));
//        System.out.println(Arrays.toString(new Codec().countBits(5)));
//        System.out.println(Arrays.toString(new Codec().countBits(6)));
//        System.out.println(Arrays.toString(new Codec().countBits(7)));
        System.out.println(Arrays.toString(new Codec().countBits(8)));
    }
}
