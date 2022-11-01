package com.algo.basic;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2,34,3,2,54,3,43,6,43,2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 快速选择,我们并不需要完全排序，只要知道左边比他小的有n-k-1个就行
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n-1;
        k=n-k;
        while(left<=right) {
            int pivot = partition(nums, left, right);
            if(k==pivot) return nums[pivot];
            else if(k>pivot) left = pivot+1;
            else right = pivot-1;
        }
        return -1;
    }

    static void sort(int[] nums) {
        sort(nums, 0, nums.length-1);
    }

    static void sort(int[] nums, int left, int right) {
        if (left>=right) return;
        int pivot = partition(nums, left, right);
        sort(nums, left, pivot-1);
        sort(nums, pivot+1, right);
    }

    static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i=left, j=right;
        while (i<j) {
            while (i<j && nums[j]>=pivot) j--;
            while (i<j && nums[i]<=pivot) i++;
            if(i>=j) break;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        nums[left] = nums[i];
        nums[i] = pivot;
        return i;
    }
}
