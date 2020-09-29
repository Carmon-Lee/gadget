package tree.traverse.recursive;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guang.li
 * @version PreOrderTreeTraverser.java v 1.0 2020/9/29 14:03
 */
public class PreOrderTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrderRecursive(res, root);
        return res;
    }

    private void preOrderRecursive(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrderRecursive(res, root.left);
        preOrderRecursive(res, root.right);
    }
}
