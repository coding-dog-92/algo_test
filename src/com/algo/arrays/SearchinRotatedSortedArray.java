package com.algo.arrays;

public class SearchinRotatedSortedArray {

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while (start<=end) {
            int mid = (start+end)/2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[mid]>=nums[start]) {
                if (target>=nums[start] && target<nums[mid]) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }
            } else {
                if (target<=nums[end] && target>nums[mid]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
        return -1;
    }
}
