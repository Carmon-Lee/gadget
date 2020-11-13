package tree.traverse.iteration;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author guang.li
 * @version PostIterTraverse.java v 1.0 2020/9/29 14:09
 */
public class PostIterTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right != null && cur.right != prev) {
                cur = cur.right;
            } else {
                res.add(cur.val);
                prev = cur;
                stack.pop();
                cur = null;
            }
        }

        return res;
    }
}
