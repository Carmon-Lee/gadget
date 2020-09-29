package tree.traverse;

import tree.TreeNode;
import tree.traverse.iteration.InOrderIterTraverse;
import tree.traverse.iteration.PreIterTreeTraverse;
import tree.traverse.recursive.PreOrderTraverse;

/**
 * @author guang.li
 * @version TreeTest.java v 1.0 2020/9/29 15:52
 */
public class TreeTest {
//      10
//   6    12
//  4 7  11 13
// 1 5
    static TreeNode buildTree() {
        TreeNode n10 = new TreeNode(10);
        TreeNode n6 = new TreeNode(6);
        TreeNode n12 = new TreeNode(12);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n11 = new TreeNode(11);
        TreeNode n13 = new TreeNode(13);
        TreeNode n1 = new TreeNode(1);
        TreeNode n5 = new TreeNode(5);

        n10.left = n6;
        n10.right = n12;
        n6.left = n4;
        n6.right = n7;
        n12.left = n11;
        n12.right = n13;
        n4.left = n1;
        n4.right = n5;
        return n10;
    }


    public static void main(String[] args) {
        TreeTraverse treeTraverse = new PreIterTreeTraverse();
        System.out.println(treeTraverse.traverse(buildTree()));

        treeTraverse = new PreOrderTraverse();
        System.out.println(treeTraverse.traverse(buildTree()));

    }
}
