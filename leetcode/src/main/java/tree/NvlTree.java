package tree;

/**
 * @author liguang
 * Created on 2021-10-03
 */
public class NvlTree {

    Node root;

    // 4种模式下需要旋转
    //    .    .        .      .
    //   /      \        \    /
    //  .        .        .  .
    // /          \      /    \
    //.            .    .      .
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        if (root.left == null && root.right == null) {
            if (value <= root.value) {
                root.left = new Node(value);
            } else {
                root.right = new Node(value);
            }
            return;
        }
        Node prePrev = null;
        Node prev = null;
        Node cur = root;
        while (cur!=null) {
            prePrev = prev;
            prev = cur;
            if (value<=cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (value <= prev.value) {
            prev.left = new Node(value);
            if (prePrev == null) {
                return;
            }
            if (prePrev.left == prev) {
                // left rotate
                leftRotate(prePrev);
            } else {
                //
            }
        } else {
            prev.right = new Node(value);
            if (prePrev == null) {
                return;
            }
            if (prePrev.right == prev) {
                // right rotate
            } else {

            }
        }

    }

    private void leftRotate(Node node) {

    }

    private void rightRotate(Node node) {

    }

    boolean exists(int value) {
        return false;
    }

    public static void main(String[] args) {

    }
}
