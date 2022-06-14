package com.algo.lc.arrays;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
