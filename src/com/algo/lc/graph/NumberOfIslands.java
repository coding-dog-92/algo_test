package com.algo.lc.graph;

public class NumberOfIslands {


    /**
     * time complexity O(m*n)
     * space complexity O(m*n)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    num++;
                    dfs(grid, i, j);
                }
            }
        }
        return num;
    }

    public int dfs(char[][] grid, int i, int j) {
        if (i<0||i>=grid.length||j<0||j>=grid[0].length) {
            return 0;
        }
        if (grid[i][j] != '1'){
            return 0;
        }
        grid[i][j] = '2';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
        return 0;
    }
}
