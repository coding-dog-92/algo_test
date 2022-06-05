package com.algo.arrays;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums.length==1) {
            return nums[0];
        }
        int start = 0, end = nums.length-1;
        if (nums[start] < nums[end]) {
            return nums[0];
        }
        while (start<=end) {
            int mid = (start+end)/2;
            if (nums[mid]>nums[mid+1]) {
                return nums[mid+1];
            }
            if (nums[mid]<nums[mid-1]) {
                return nums[mid];
            }
            if (nums[mid]>nums[0]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return -1;
    }
}
