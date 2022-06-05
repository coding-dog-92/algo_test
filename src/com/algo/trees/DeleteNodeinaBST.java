package com.algo.trees;

public class DeleteNodeinaBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) root.left = deleteNode(root.left, key);
        else if (root.val < key) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                TreeNode node = root.right;
                while (node.left != null) {
                    node = node.left;
                }
                root.val = node.val;
                root.right = deleteNode(root.right, node.val);
            }
        }
        return root;
    }
}
