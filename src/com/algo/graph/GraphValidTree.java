package com.algo.graph;

import java.util.*;

public class GraphValidTree {
    List<List<Integer>> adjList = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) {
            return false;
        }
        for (int i=0;i<n;i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return dfs(0, -1) && visited.size()==n;
    }

    public boolean dfs(int node, int from) {
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        for (Integer neighbor : adjList.get(node)) {
            if (neighbor==from) {
                continue;
            }
            boolean b = dfs(neighbor, node);
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
