package com.algo.lc.matrix;

public class WordSearch {

    public static void main(String[] args) {
        char[][] arr = new char[][] {
                new char[]{'A','B','C','E'},
                new char[]{'S','F','E','S'},
                new char[]{'A','D','E','E'}
        };
        boolean exist = new WordSearch().exist(arr, "ABCESEEEFS");
        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        char[] wordsChar = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean ret = dfs(board, i, j, wordsChar, 0, new int[m][n], m, n);
                if (ret) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, char[] wordsChar, int idx, int[][] visited, int m, int n) {
        if (i<0 || i>= m || j<0 || j>= n) {
            return false;
        }
        if (visited[i][j] != 0) {
            return false;
        }
        if (board[i][j] != wordsChar[idx]) {
            return false;
        }
        if (idx == wordsChar.length-1) {
            return true;
        }
        visited[i][j] = 1;
        boolean b =  dfs(board, i, j+1, wordsChar, idx+1, visited, m, n)
                || dfs(board, i, j-1, wordsChar, idx+1, visited, m, n)
                || dfs(board, i+1, j, wordsChar, idx+1, visited, m, n)
                || dfs(board, i-1, j, wordsChar, idx+1, visited, m, n);
        if (b) return true;
        visited[i][j] = 0;
        return false;
    }
}
