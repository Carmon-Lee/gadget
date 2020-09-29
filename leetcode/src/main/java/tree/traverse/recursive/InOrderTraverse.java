package tree.traverse.recursive;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guang.li
 * @version InOrderTreeTraverser.java v 1.0 2020/9/29 14:04
 */
public class InOrderTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrderRecursive(res, root);
        return res;
    }

    private void inOrderRecursive(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(res, root.left);
        res.add(root.val);
        inOrderRecursive(res, root.right);
    }
}
