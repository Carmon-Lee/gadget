package string.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieTree {

    static TreeNode buildTree() {
        return new TreeNode(false);
    }

    static void add(TreeNode tree, String str) {
        if (tree.sibling == null) {
            tree.sibling = new HashMap<>();
        }
        char[] chars = str.toCharArray();
        recursiveAppend(tree, chars, 0);
    }

    static void recursiveAppend(TreeNode tree, char[] chs, int idx) {
        if (idx < 0 || idx >= chs.length) {
            return;
        }
        char ch = chs[idx];
        if (tree.sibling == null) {
            tree.sibling = new HashMap<>();
        }

        TreeNode child = tree.sibling.get(ch);
        if (child == null) {
            tree.sibling.put(ch, new TreeNode(false));
            child = tree.sibling.get(ch);
        }
        child.leaf = child.leaf || idx == chs.length - 1;

        recursiveAppend(child, chs, idx + 1);
    }

    public static boolean exists(TreeNode node, String str) {
        char[] chars = str.toCharArray();
        return recurFind(node, chars, 0);
    }

    public static boolean recurFind(TreeNode node, char[] chars, int idx) {
        if (idx >= chars.length) {
            return false;
        }
        char ch = chars[idx];
        TreeNode child = node.sibling.get(ch);
        if (child == null) {
            return false;
        }
        if (idx==chars.length-1) {
            return child.leaf;
        }
        return recurFind(child, chars, idx+1);
    }


    public static void main(String[] args) {
        TreeNode tree = buildTree();
        add(tree, "tree");
        add(tree, "triangle");
        add(tree, "triple");
        add(tree, "trip");

        System.out.println(exists(tree, "tree"));
        System.out.println(exists(tree, "tre"));
        System.out.println(exists(tree, "trip"));
        System.out.println(exists(tree, "tri"));
        System.out.println(exists(tree, "tripp"));
        System.out.println(exists(tree, "triple"));
        System.out.println();
    }
}

class TreeNode {
    boolean leaf;
    Map<Character, TreeNode> sibling;

    public TreeNode(boolean leaf) {
        this.leaf = leaf;
    }

}



