package com.algo.lc.trees;

public class LowestCommonAncestorofaBinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p.val < root.val && q.val > root.val) || (p.val > root.val && q.val < root.val) || p.val==root.val || q.val == root.val) {
            return root;
        }
        if (p.val<root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return lowestCommonAncestor(root.right, p, q);
    }
}
