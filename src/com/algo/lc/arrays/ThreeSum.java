package com.algo.lc.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums==null || nums.length<3) {
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n-2; i++) {
            if (nums[i]>0) {
                continue;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int target = -nums[i];
            int start = i+1, end = n-1;
            while (start<end) {
                if (nums[start]+nums[end] == target) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    //
                    start++;
                    end--;
                    while (start<end && nums[start]==nums[start-1]) start++;
                    while (start<end && nums[end]==nums[end+1]) end--;
                } else if (nums[start]+nums[end] > target) {
                    end--;
                } else {
                    start++;
                }

            }
        }
        return res;
    }
}
