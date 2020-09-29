package tree.traverse.iteration;

import tree.TreeNode;
import tree.traverse.TreeTraverse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author guang.li
 * @version PreIterTreeTraverse.java v 1.0 2020/9/29 14:08
 */
public class PreIterTreeTraverse implements TreeTraverse {

    @Override
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                res.add(cur.val);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            } else {
                cur = stack.pop();
            }
        }

//        TreeNode cur = root;
//        while (!stack.isEmpty() || cur != null) {
//            if (cur != null) {
//                res.add(cur.val);
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop().right;
//            }
//        }

        return res;
    }


}
