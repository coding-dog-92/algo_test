package com.algo.lc.trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val+","+left+","+right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(arr));
        return redeserialize(queue);
    }
    public TreeNode deserialize1(String data) {
        String[] nodes = data.split(",");
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(nodes));
        return buildTree(queue);
    }

    public TreeNode redeserialize(Queue<String> queue) {
        String poll = queue.poll();
        if ("null".equals(poll)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(poll));
        node.left = redeserialize(queue);
        node.right = redeserialize(queue);
        return node;
    }

    public TreeNode buildTree(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("X")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }

    public static void main(String[] args) {
        SerializeandDeserializeBinaryTree ob = new SerializeandDeserializeBinaryTree();
        /**
         *         1
         *     2       3
         *   4  null  null null
         * null null
         *
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        String serialize = ob.serialize(root);
        System.out.println(serialize);
    }
}
