package com.algo.google;

public class CordTree {
    /**
     * A cord tree is a binary tree of strings.
     * A node in this tree can be a leaf node or an internal node.
     * An internal node has two children, a left child and a right child. It also has a length of all the children under it
     * A leaf nodes have a value and a length
     *
     *                                 #      InternalNode, 26
     *                                 #      /              \
     *                                 #     /                \
     *                                 #    /                  \
     *                                 # Leaf(5, ABCDE)      InternalNode, 21
     *                                 #                       /           \
     *                                 #                      /             \
     *                                 #                     /               \
     *                                 #                    /                 \
     *                                 #         Leaf(10, FGHIJKLMNO)     Leaf(11, PQRSTUVWXYZ)
     * Q1: Define a Data Structure that represents a Cord tree.
     * Q2: Define a function that takes in a tree and an index and returns the character at that index.
     */

    public static void main(String[] args) {
        Node root = new InternalNode(26, new LeafNode(5, "ABCDE"), new InternalNode(21,
                new LeafNode(10, "FGHIJKLMNO"), new LeafNode(11, "PQRSTUVWXYZ")));

        Tree cordTree = new Tree(root);
        System.out.println(cordTree.findCordAtIndex(10));
        System.out.println(cordTree.findCordAtIndex(3));
        System.out.println(cordTree.findCordAtIndex(16));
    }
}

class Node{
    public int length;

    public Node(int length) {
        this.length = length;
    }
}

class LeafNode extends Node{
    public String value;

    public LeafNode(int length, String value) {
        super(length);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class InternalNode extends Node{
    public Node leftChild;
    public Node rightChild;

    public InternalNode(int length, Node leftChild, Node rightChild) {
        super(length);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}

class Tree {
    public Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Character findCordAtIndex(int index) {
        return findCordAtIndex(index, root);
    }

    public Character findCordAtIndex(int index, Node root) {
        if(index <= root.length) {
            if(root instanceof LeafNode) {
                // if a leaf node, base case
                String s = ((LeafNode) root).value;
                return s.charAt(index-1);
            } else {
                // if a internal node
                // check left right
                InternalNode internalNode = (InternalNode) root;
                Node left = internalNode.leftChild;
                Node right = internalNode.rightChild;
                if(index <= left.length) {
                    // go to left side
                    return findCordAtIndex(index, left);
                } else {
                    // got to right side
                    return findCordAtIndex(index- left.length, right);
                }
            }
        }
        return null;
    }
}
