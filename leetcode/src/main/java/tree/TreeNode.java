package tree;

/**
 * @author guang.li
 * @version TreeNode.java v 1.0 2020/9/29 13:52
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }





    public int paintingPlan(int n, int k) {
        if (k>n*n || k<n) {
            return 0;
        }
        if (k==n*n) {
            return 1;
        }
        int count = 0;
        int pown = (int)(Math.pow(2,n))-1;
        for (int i=0;i<=pown;i++) {
            for (int j=0;j<=pown;j++) {
                int rows = countOne(i);
                int cols = countOne(j);
                int brackets = rows*n + cols*n - rows*cols;
                if (brackets==k) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countOne(int i) {
        int c = 0;
        while (i>0) {
            if (i%2==1) {
                c++;
            }
            i/=2;
        }
        return c;
    }


    public static void main(String[] args) {
        new TreeNode(1).paintingPlan(2,4);

    }
}
