package com.algo.lc.trees;

public class ValidateBinarySearchTree {
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root==null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }
}
