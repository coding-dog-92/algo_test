package com.algo.lc.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerticalOrderTraversaloaBinaryTree {

    Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();
    int minColumn = Integer.MAX_VALUE, maxColumn = Integer.MIN_VALUE;
    Map<Integer, Integer> columnMaxRow = new HashMap<>();
    Map<Integer, Integer> columnMinRow = new HashMap<>();


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, 0);
        for (int i = minColumn; i <= maxColumn; i++) {
            Map<Integer, List<Integer>> curMap = map.get(i);
            List<Integer> tmpList = new ArrayList<>();
            for (int j = columnMinRow.get(i); j <= columnMaxRow.get(i); j++) {
                List<Integer> integerList = curMap.get(j);
                if (integerList==null) continue;
                integerList.sort((a, b)->a-b);
                tmpList.addAll(integerList);
            }
            res.add(tmpList);
        }
        return res;
    }

    public void dfs(TreeNode node, int row, int column) {
        if (node == null) return;
        if (!map.containsKey(column)) {
            map.put(column, new HashMap<>());
        }
        if (!map.get(column).containsKey(row)) {
            map.get(column).put(row, new ArrayList<>());
        }
        map.get(column).get(row).add(node.val);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);
        if (!columnMaxRow.containsKey(column)) {
            columnMaxRow.put(column, row);
        } else {
            columnMaxRow.put(column, Math.max(row, columnMaxRow.get(column)));
        }
        if (!columnMinRow.containsKey(column)) {
            columnMinRow.put(column, row);
        } else {
            columnMinRow.put(column, Math.min(row, columnMinRow.get(column)));
        }
        dfs(node.left, row+1,column-1);
        dfs(node.right, row+1,column+1);
    }
}
