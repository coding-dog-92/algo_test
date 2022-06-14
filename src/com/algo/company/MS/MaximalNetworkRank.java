package com.algo.company.MS;

public class MaximalNetworkRank {
    public static int maximalNetworkRank(int n, int[][] roads) {
        int maxRank = 0;
        boolean[][] connectMap = new boolean[n][n];
        int[] degree = new int[n];
        for (int i = 0; i < roads.length; i++) {
            int[] currentPair = roads[i];
            degree[currentPair[0]] ++;
            degree[currentPair[1]] ++;
            connectMap[currentPair[0]][currentPair[1]] = true;
            connectMap[currentPair[1]][currentPair[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (connectMap[i][j]) {
                    maxRank = Math.max(maxRank, degree[i]+degree[j]-1);
                } else {
                    maxRank = Math.max(maxRank, degree[i] + degree[j]);
                }
            }
        }
        return maxRank;
    }

    public static void main(String[] args) {
        int[][] roads = new int[][] {
                {0,1},{1,2},{3,4},{4,5}
        };
        System.out.println(maximalNetworkRank(6, roads));
    }
}
