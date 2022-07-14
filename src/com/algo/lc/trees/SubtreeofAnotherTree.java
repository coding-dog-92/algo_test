package com.algo.lc.trees;

public class SubtreeofAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return  isSame(root, subRoot)|| isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSame(TreeNode node, TreeNode target) {
        if (node == null && target == null) {
            return true;
        }
        if (node == null || target == null) {
            return false;
        }
        return node.val==target.val && isSame(node.left, target.left) && isSame(node.right, target.right);
    }
}
