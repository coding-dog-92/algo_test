package com.algo.company.meta;

import javafx.util.Pair;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {

    public Map<Integer, List<Integer>> map = new HashMap<>();
    public Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root==null)
            return null;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            Integer order = pair.getValue();
            if (map.containsKey(order)) {
                map.get(order).add(node.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                map.put(order, list);
            }
            min = Math.min(min, order);
            max = Math.max(max, order);
            if (node.left!=null)
                queue.offer(new Pair<>(node.left, order-1));
            if (node.right!=null)
                queue.offer(new Pair<>(node.right, order+1));
        }

        for (int i = min; i <= max; i++) {
            resList.add(map.get(i));
        }
        return resList;
    }

    public void BFS(TreeNode root, int order) {
        if (root==null)
            return;
        if (map.containsKey(order)) {
            List<Integer> list = map.get(order);
            list.add(root.val);
            map.put(order, list);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(order, list);
        }
        BFS(root.left, order-1);
        BFS(root.right, order+1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        new BinaryTreeVerticalOrderTraversal().verticalOrder(root);
    }

}




 class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
