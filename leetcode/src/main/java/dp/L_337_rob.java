package dp;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 *      3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @author guang.li
 * @version L_337_rob.java v 1.0 2020/10/7 17:39
 */
public class L_337_rob {

    private Map<TreeNode, Integer> includeMap;
    private Map<TreeNode, Integer> excludeMap;

    public int rob(TreeNode root) {
        includeMap = new HashMap<>();
        excludeMap = new HashMap<>();
        return Math.max(rob(root, true), rob(root, false));
    }

    private int rob(TreeNode node, boolean includeRoot) {
        if (node == null) {
            return 0;
        }
        if (includeRoot) {
            int left = 0, right = 0;
            if (node.left != null) {
                left = excludeMap.getOrDefault(node.left, rob(node.left, false));
                excludeMap.put(node.left, left);
            }
            if (node.right != null) {
                right = excludeMap.getOrDefault(node.right, rob(node.right, false));
                excludeMap.put(node.right, right);
            }

            return left + right + node.val;
        } else {
            int left0 = 0, left1 = 0, right0 = 0, right1 = 0;
            if (node.left != null) {
                left0 = excludeMap.getOrDefault(node.left, rob(node.left, false));
                left1 = includeMap.getOrDefault(node.left, rob(node.left, true));
                excludeMap.put(node.left, left0);
                includeMap.put(node.left, left1);
            }
            if (node.right != null) {
                right0 = excludeMap.getOrDefault(node.right, rob(node.right, false));
                right1 = includeMap.getOrDefault(node.right, rob(node.right, true));
                excludeMap.put(node.right, right0);
                includeMap.put(node.right, right1);
            }

            return Math.max(left0, left1) + Math.max(right0, right1);
        }
    }
}
