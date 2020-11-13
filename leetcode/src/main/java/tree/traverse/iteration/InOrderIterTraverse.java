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

        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }

        return res;
    }
}
