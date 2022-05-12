package com.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
//        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
//        System.out.println(maxProduct(new int[]{-1,-2,-9,-6}));
//        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
//        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));



    }

    public static int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }
        int right = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            leftProduct[i] = leftProduct[i] * right;
            right = right * nums[i];
        }
        return  leftProduct;
    }

    // [-2,1,-3,4,-1,2,1,-5,4]
    /**
     * dp; index~[0,nums.length-1]
     * dp[i] maxSum from 0 to i
     * dp[i] = max(dp[i-1]+nums[i], nums[i])
     * [5,4,-1,7,8]
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            System.out.println(nums[i]);
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            sum = Math.max(sum, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return sum;
    }

    public static int maxSubArray1(int[] nums) {
        int pre = nums[0];
        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            pre = Math.max(nums[i], nums[i] + pre);
            sum = Math.max(sum, pre);
        }
        return sum;
    }


    // [-4,-3,-2]
    /**
     * dp[i] = max(dp[i]*nums[i], nums[i])
     * -1,-2,-9,-6
     */
    public static int maxProduct(int[] nums) {
        int preMax = nums[0], preMin = nums[0], maxProduct = nums[0];
        for (int i=1;i<nums.length;i++) {
            int tmpMax = preMax, tmpMin = preMin;
            preMax = Math.max(Math.max(tmpMax*nums[i], nums[i]), tmpMin*nums[i]);
            preMin = Math.min(Math.min(tmpMax*nums[i], nums[i]),  tmpMin*nums[i]);
            maxProduct = Math.max(maxProduct, preMax);
        }
        return maxProduct;
    }

    public static int maxProduct1(int[] nums) {
        int[] maxArr = new int[nums.length];
        maxArr[0] = nums[0];
        int[] minArr = new int[nums.length];
        minArr[0] = nums[0];

        int sum = nums[0];
        for (int i=1;i<nums.length;i++) {
            maxArr[i] = Math.max(Math.max(nums[i], nums[i] * maxArr[i-1]), nums[i] * minArr[i-1]);
            minArr[i] = Math.min(Math.min(nums[i], nums[i] * maxArr[i-1]), nums[i] * minArr[i-1]);
            sum = Math.max(sum, maxArr[i]);
        }
//        System.out.println(Arrays.toString(maxArr));
//        System.out.println(Arrays.toString(minArr));

        return sum;
    }


    /**
     * [4,5,6,7,8,0,1,2]
     * [0,1,2,4,5,6,7,8]
     * [3,4,5,1,2]
     * [2,1]
     * all the elements left to point > first element
     * all the elements right to point < first element
     */
    public static int findMin(int[] nums) {
        if (nums[0] < nums[nums.length-1]) {
            return  nums[0];
        }
        int low=0, high=nums.length-1;
        while (low<high) {
            int mid = (low+high)/2;
            if (nums[mid]>=nums[0]) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return nums[low];

    }


    /**
     *
     *
     *
     * ==========================================================================
     *
     *
     *
     */


    /**
     * 根据id查询对应配置
     *
     * @param nums
     * @param target
     * @return
     * [4,5,6,7,0,1,2], target = 0
     * [0,1,2,4,5,6,7]
     * if nums[pivot]<target then right
     * else if nums[pivot]>target and target>nums[low] left
     * else right
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0]==target? 0 : -1;
        }
        int low = 0, high = nums.length-1;
        while (low<=high) {
            int mid = low+(high-low)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid]>=nums[low]) {
                if (target>=nums[low] && target<nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else {
                if (target>nums[mid] && target<=nums[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }
        return -1;
    }




    /*
      [-1,0,1,2,-1,-4]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums.length<3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++) {
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int target = -nums[i];
            int left = i+1, right = nums.length-1;
            while (left<right) {
                if (nums[left]+nums[right] == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left<right && nums[left]==nums[left-1]) {
                        left++;
                    }
                    while (left<right && nums[right]==nums[right+1]) {
                        right--;
                    }
                } else if (nums[left]+nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }


    /**
     * [2,7,11,15], target = 9
     * [1,2]
     * -1,-1,1,1,1,1,1,1
     * -2
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers.length < 2) {
            return res;
        }
        int left = 0, right = numbers.length-1;
        while (left<right) {
            if (numbers[left]+numbers[right]==target) {
                return new int[]{left+1, right+1};
            } else if (numbers[left]+numbers[right]>target) {
                right--;
                while (left<right && numbers[right]==numbers[right+1]) {
                    right--;
                }
            } else {
                left++;
                while (left<right && numbers[left]==numbers[left-1]) {
                    left++;
                }
            }
        }
        return res;
    }


    /**
     * [1,8,6,2,5,4,8,3,7]
     * 49
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int res = 0, left=0, right=height.length-1;
        while (left<right) {
            int tmpArea = (right-left)*Math.min(height[left], height[right]);
            if (tmpArea>res) {
                res = tmpArea;
            }
            if (height[left]<height[right]) {
                left ++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * coins = [1,2,5], amount = 11
     * 3
     * F(i) = minF(i-c)+1
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i>=c) {
                    dp[i] = Math.min(dp[i], dp[i-c]+1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        if (dp[amount] == amount+1) {
            return -1;
        }
        return dp[amount];
    }


    /**
     * [10,9,2,5,3,7,101,18]
     * 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * dp[i] = max(dp[j])+1, 0<=j<i when nums[i]>num[j]
     *
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            if (dp[i]>len) {
                len = dp[i];
            }
        }
        return len;
    }


    /**
     * text1 = "abcde", text2 = "ace"
     * 3
     * The longest common subsequence is "ace" and its length is 3.
     * dp[i] = dp[j]+1, 0<=j<i when text2[i]=text1[j]
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        String longStr = text1, shortStr = text2;
        int res = 0;
        if (text2.length()>text1.length()) {
            longStr = text2;
            shortStr = text1;
        }
        int[] dp = new int[shortStr.length()];
        Arrays.fill(dp, 0);
        for (int i = 0; i < longStr.length(); i++) {
            if (longStr.charAt(i) == shortStr.charAt(0)) {
                dp[0] = 1;
                break;
            }
        }
        for (int i = 0; i < shortStr.length(); i++) {
            for (int j = 0; j < longStr.length(); j++) {
                if (shortStr.charAt(i) == longStr.charAt(i)) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            if (dp[i]>res) {
                res = dp[i];
            }
        }
        return res;
    }
}
