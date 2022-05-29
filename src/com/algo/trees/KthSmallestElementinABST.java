package com.algo.trees;

public class KthSmallestElementinABST {
    int count = 0;
    int res = 0;

    /**
     * inorder sequence of BST is ascending
     * just return kth val of inorder
     */
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        dfs(root);
        return res;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        count --;
        if (count == 0) {
            res = root.val;
            return;
        }
        dfs(root.right);
    }
}
