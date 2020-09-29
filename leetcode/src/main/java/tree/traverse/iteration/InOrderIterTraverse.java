package tree.traverse.iteration;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author guang.li
 * @version InOrderIterTraverse.java v 1.0 2020/9/29 14:09
 */
public class InOrderIterTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            while (cur.left != null) {
                cur = cur.left;
                stack.push(cur);
            }

            if (cur.right != null && prev != cur.right) {
                stack.push(cur.right);
            } else {
                prev = cur;
                res.add(cur.val);
            }
        }

        return res;
    }
}
