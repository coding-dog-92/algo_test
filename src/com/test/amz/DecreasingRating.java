package com.test.amz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DecreasingRating {

    public static void main(String[] args) {
//        System.out.println(new DecreasingRating().count(new int[]{4,3,5,4,3}, 3));
        System.out.println(new DecreasingRating().countDecreasingRatings(new int[]{4,3,5,4,3}));
    }

    /**
     * [4,3,5,4,3]
     * 5+3+1
     * [5,4,3,2,1]->5+4+3+2+1->(5+1)*5/2->n*(n+1)/2
     *
     */
    public long countDecreasingRatings(int[] ratings) {
        long count = 0;
        long currentLen = 0;
        for (int i = 0; i < ratings.length; i++) {
            currentLen++;
            if (i == ratings.length-1 || ratings[i]-1!=ratings[i+1]) {
                count += currentLen*(currentLen+1)/2;
                currentLen = 0;
            }
        }
        return count;
    }


    public int countConsecutiveNumbers(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i==nums.length-1||nums[i]!=nums[i+1]-1) {
                count++;
            }
        }
        return count;
    }

    public List<List<Integer>> rangeConsecutiveNumbers(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            currentList.add(nums[i]);
            if (i==nums.length-1||nums[i]!=nums[i+1]-1) {
                result.add(currentList);
                currentList = new ArrayList<>();
            }
        }
        return result;
    }

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n-k+1];
        for (int i = 0, j=0; i < n; i++) {
            // check first
            while (!deque.isEmpty() && deque.getFirst()<i-k+1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[i]>nums[deque.getLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i-k+1>=0) {
                res[j++] = nums[deque.getFirst()];
            }
        }
        return res;
    }
}
