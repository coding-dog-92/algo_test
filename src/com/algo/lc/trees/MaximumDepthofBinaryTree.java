package com.algo.lc.trees;

public class MaximumDepthofBinaryTree {

    int max = 0;

    public int maxDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        dfs(root, 1);
        return max;
    }

    public void dfs(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            max = Math.max(max, depth);
        }
        if (node.left!=null) {
            dfs(node.left, depth+1);
        }
        if (node.right!=null) {
            dfs(node.right, depth+1);
        }
    }
}
