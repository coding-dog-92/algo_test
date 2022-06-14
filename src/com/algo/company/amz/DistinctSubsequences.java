package com.algo.company.amz;

import java.util.Arrays;

public class DistinctSubsequences {
    String s, t;
    int m, n;
    int[][] res;
    public int numDistinct(String s, String t) {
        if (s==null||t==null||t.length()>s.length()) return 0;
        this.s = s;
        this.t = t;
        m = s.length();
        n = t.length();
        res = new int[m][n];
        for (int[] re : res) {
            Arrays.fill(re, -1);
        }
        return numCount(0, 0);
    }

    int numCount(int x, int y) {
        if (m-x < n-y) return 0;
        // match
        if (y >= n) return 1;
        if (res[x][y] != -1) return res[x][y];
        if (s.charAt(x) == t.charAt(y)) {
            res[x][y] = numCount(x+1, y+1)+numCount(x+1, y);
        } else {
            res[x][y] = numCount(x+1, y);
        }
        return res[x][y];
    }
}
