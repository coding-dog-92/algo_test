package com.algo.trees;

public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        if (preLeft==preRight) {
            return new TreeNode(preorder[preLeft]);
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        // find rootVal in inorder
//        int rootIdx = 0;
//        for (int i = inLeft; i <= inRight; i++) {
//            if (inorder[i] == rootVal) {
//                rootIdx = i;
//                break;
//            }
//        }
        int rootIdx = inLeft;
        while (rootIdx <= inRight) {
            if (inorder[rootIdx] == rootVal) {
                break;
            }
            rootIdx ++;
        }
        // preorder: left=preLeft+1,preLeft+rootIdx-inLeft; right=preLeft+rootIdx-inLeft+1,preRight
        // inorder: left=inLeft,rootIdx-1;  right=rootIdx+1,inRight
        root.left = buildTree(preorder, preLeft+1, preLeft+rootIdx-inLeft, inorder, inLeft, rootIdx-1);
        root.right = buildTree(preorder, preLeft+rootIdx-inLeft+1, preRight, inorder, rootIdx+1, inRight);
        return root;
    }
}
