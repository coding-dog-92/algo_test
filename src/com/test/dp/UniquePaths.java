package com.test.dp;

import java.util.Arrays;

public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }

    /**
     *m = 3, n = 7
     *28
     * dp[i][j] = {
     *     dp[i][j-1]+dp[i-1][j]
     * }
     */
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n];
    }
}
