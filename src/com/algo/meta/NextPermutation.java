package com.algo.meta;

import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        if (nums==null || nums.length < 2) {
            return;
        }
        int left = 0,right = nums.length-1;
        boolean find = false;
        for (int i = nums.length-1; i > 0 ; i--) {
            if (nums[i]>nums[i-1]) {
                left = i-1;
                right = i;
                find = true;
                break;
            }
        }
        if (find) {
            for (int i = nums.length-1; i >= left; i--) {
                if (nums[i]>nums[left]) {
                    swap(nums, i, left);
                    break;
                }
            }
        }

        int l = right, r = nums.length-1;
        if (!find) {
            l = 0;
        }
        while (l<r){
            swap(nums, l++, r--);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,5,4,3,2,1};
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }
}
