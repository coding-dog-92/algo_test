package com.algo.lc.interval;

import java.util.Arrays;

public class NonOverlappingIntervals {


    public int eraseOverlapIntervalsGreddy(int[][] intervals) {
        if (intervals.length==0) {
            return 0;
        }
        Arrays.sort(intervals, (a,b)->Integer.compare(a[1], b[1]));
        int n = intervals.length;
        int ans = 1, right = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (right<=intervals[i][0]) {
                ans++;
                right = intervals[i][1];
            }
        }
        return n-ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        /**
         * use dp[i] represents the maximum number of intervals we use to guarantee no overlaps in intervals
         */
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        int n = intervals.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i-1; j++) {
                if (intervals[j-1][1]<=intervals[i-1][0]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return n-Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                new int[]{1,2},
                new int[]{2,3},
                new int[]{3,4},
                new int[]{1,3}
        };
        int insert = new NonOverlappingIntervals().eraseOverlapIntervals(arr);
        System.out.println(insert);
    }
}
