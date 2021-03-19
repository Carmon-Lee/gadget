package array.binaryIndextree;

/**
 * Created on 2020-11-28
 * 给定一个树组，求一段区间的和
 * 这个树组的值会经常发生变化，要求用一种高效求和的方式
 */
public class BinaryIndexTree {

    int[] a;
    int[] c;

    public BinaryIndexTree(int[] arr) {
        this.a = new int[arr.length+1];
        this.c = new int[arr.length+1];
        for (int i=0;i<arr.length;i++) {
            update(i+1, arr[i]);
        }
    }

    int lowBit(int n) {
        return n & (-n);
    }

    int getSum(int pos) {
        int v = 0;
        while (pos > 0) {
            v += c[pos];
            pos -= lowBit(pos);
        }
        return v;
    }

    private void update(int pos, int v) {
        int diff = v - a[pos];
        if (diff == 0) {
            return;
        }
        a[pos] = v;
        while (pos < c.length) {
            c[pos] += diff;
            pos += lowBit(pos);
        }
    }

    public static void main(String[] args) {
        BinaryIndexTree tree = new BinaryIndexTree(new int[] {1, 2, 3, 4, 5});
        for (int i = 0; i < 5; i++) {
            System.out.println(tree.getSum(i));
        }

        // 1 3 2 3 1
        //
    }
}
