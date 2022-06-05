package com.algo.arrays;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dpMax = new int[n], dpMin = new int[n];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = dpMax[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i]*dpMax[i-1], nums[i]*dpMin[i-1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i]*dpMax[i-1], nums[i]*dpMin[i-1]));
            if (dpMax[i] > res) {
                res = dpMax[i];
            }
        }
        return res;
    }
}
