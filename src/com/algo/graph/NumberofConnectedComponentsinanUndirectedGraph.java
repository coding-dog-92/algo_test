package com.algo.graph;

import java.util.*;

public class NumberofConnectedComponentsinanUndirectedGraph {

    List<List<Integer>> adjacencyList = new ArrayList<>();
    Map<Integer, Integer> visited = new HashMap<>();

    public int countComponents(int n, int[][] edges) {
        // build adjacency list
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // step 2
        for (int i = 0; i < n; i++) {
            if (!visited.containsKey(i)) {
                dfs(i, i);
            }
        }

        HashSet<Integer> set = new HashSet<>(visited.values());
        return set.size();
    }

    public void dfs(int node, int color) {
        if (visited.containsKey(node)) {
            return;
        }
        visited.put(node, color);
        for (Integer n : adjacencyList.get(node)) {
            dfs(n, color);
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                new int[]{0,1},
                new int[]{1,2},
                new int[]{3,4}
        };
        int res = new NumberofConnectedComponentsinanUndirectedGraph().countComponents(5, arr);
        System.out.println(res);
    }


}
