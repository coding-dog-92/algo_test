package com.algo.matrix;

public class MaximalSquare {


    public static int maximalSquare(char[][] matrix) {
        if (matrix.length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int maxLen = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i==0 || j==0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1])+1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen*maxLen;
    }

    public static int maximalSquare1(char[][] matrix) {
        if (matrix.length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int maxSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    maxSize = Math.max(maxSize, 1);
                    int x = i+1, y = j+1;
                    boolean isSquare = true;
                    while (x<m && y<n) {
                        // check row
                        for (int k = i; k <= x; k++) {
                            if (matrix[k][y]=='0') {
                                isSquare = false;
                                break;
                            }
                        }
                        if (!isSquare) break;
                        // check column
                        for (int k = j; k <= y; k++) {
                            if (matrix[x][k]=='0') {
                                isSquare = false;
                                break;
                            }
                        }
                        if (!isSquare) break;
                        System.out.println("i="+i+", j="+j+", x="+x);
                        maxSize = Math.max(maxSize, (x-i+1)*(x-i+1));
                        x++;
                        y++;
                    }
                }
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        char[][] arr = new char[][] {
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','0','0','0'},
                {'0','1','1','1','1','0','0','0'},
        };
        System.out.println(maximalSquare(arr));
    }
}
