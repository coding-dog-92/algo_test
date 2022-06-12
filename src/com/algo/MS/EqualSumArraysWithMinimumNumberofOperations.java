package com.algo.MS;

import java.util.Arrays;

public class EqualSumArraysWithMinimumNumberofOperations {
    public int minOperations1(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) return 0;
        if (s1 < s2) return minOperations(nums2, nums1);
        int left = nums1.length-1, right = 0;
        int diff = s1 - s2;
        int count = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (diff > 0) {
            if (left<0 && right==nums2.length) return -1;
            if (left<0 || right==nums2.length) {
                if (left<0) {
                    diff -= (6-nums2[right]);
                    right++;
                } else {
                    diff -= (nums1[left]-1);
                    left--;
                }
            } else {
                if ((6-nums2[right]) > (nums1[left]-1)) {
                    diff -= (6-nums2[right]);
                    right++;
                } else {
                    diff -= (nums1[left]-1);
                    left--;
                }
            }
            count ++;
        }
        return count;
    }


    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) return 0;
        if (s1 < s2) return minOperations(nums2, nums1);
        int left = nums1.length-1, right = 0;
        int diff = s1 - s2;
        int count = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (diff > 0) {
            if (left<0 && right==nums2.length) return -1;
            if (left<0) {
                diff -= (6-nums2[right]);
                right++;
            } else if (right==nums2.length){
                diff -= (nums1[left]-1);
                left--;
            } else if ((nums1[left]-1) > (6-nums2[right])){
                diff -= (nums1[left]-1);
                left--;
            } else {
                diff -= (6-nums2[right]);
                right++;
            }
            count ++;
        }
        return count;
    }
}
