package tree.traverse.recursive;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guang.li
 * @version PostTreeTraverser.java v 1.0 2020/9/29 14:05
 */
public class PostOrderTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrderRecursive(res, root);
        return res;
    }

    private void postOrderRecursive(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(res, root.left);
        postOrderRecursive(res, root.right);
        res.add(root.val);
    }
}
