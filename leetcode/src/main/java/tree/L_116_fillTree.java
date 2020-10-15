/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package tree;

/**
 * @author liguang
 * 给定一棵二叉树，将每个节点与其右侧的节点进行连接
 * @version L_116_fillTree.java, v 0.1 2020年10月15日 9:17
 */
public class L_116_fillTree {

    //    1
    //  2   3
    // 1 2 3 4
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node level = null;
        Node cur = root;
        Node tmp = null;
        while (cur != null) {
            level = new Node(0);
            tmp = level;
            while (cur != null) {
                if (cur.left != null) {
                    tmp.next = cur.left;
                    tmp = tmp.next;
                }
                if (cur.right != null) {
                    tmp.next = cur.right;
                    tmp = tmp.next;
                }
                cur = cur.next;
            }
            cur = level.next;
        }
        return root;
    }
}


