package com.algo.meta;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {

    private List<BinaryTreeNodeCommon> pList = new ArrayList<>();
    private List<BinaryTreeNodeCommon> qList = new ArrayList<>();

    public BinaryTreeNodeCommon lowestCommonAncestor(BinaryTreeNodeCommon root, BinaryTreeNodeCommon p, BinaryTreeNodeCommon q) {
        if (root==null||p==root|q==root) {
            return root;
        }
        BinaryTreeNodeCommon left = lowestCommonAncestor(root.left, p, q);
        BinaryTreeNodeCommon right = lowestCommonAncestor(root.right, p, q);
        if (left==null&&right==null) {
            return null;
        }
        if (left!=null&&right==null) {
            return left;
        }
        if (left==null&&right!=null) {
            return right;
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNodeCommon root = new BinaryTreeNodeCommon(3);
        root.left = new BinaryTreeNodeCommon(5);
        root.right = new BinaryTreeNodeCommon(1);
        root.left.left = new BinaryTreeNodeCommon(6);
        root.left.right = new BinaryTreeNodeCommon(2);
        root.left.right.left = new BinaryTreeNodeCommon(7);
        root.left.right.right = new BinaryTreeNodeCommon(4);
        root.right.left = new BinaryTreeNodeCommon(0);
        root.right.right = new BinaryTreeNodeCommon(8);
        new LowestCommonAncestor().lowestCommonAncestor(root, new BinaryTreeNodeCommon(5), new BinaryTreeNodeCommon(4));
    }

}

class BinaryTreeNodeCommon {
    int val;
    BinaryTreeNodeCommon left;
    BinaryTreeNodeCommon right;

    BinaryTreeNodeCommon(int x) {
        val = x;
    }

}
