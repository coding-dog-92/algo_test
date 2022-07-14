package com.algo.lc.dp;

public class JumpGame {

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump2(new int[]{0}));
    }

    /**
     * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 105
     *
     * dp[i] = dp[j]&&j+nums[j]>=i, 0<=j<i
     */

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length+1];
        dp[0] = true;
        dp[1] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[j] && j+nums[j-1]>=i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length];
    }

    public boolean canJump1(int[] nums) {
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i<=right) {
                right = Math.max(right, i+nums[i]);
                if (right>=nums.length-1)
                    return true;
            }
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
