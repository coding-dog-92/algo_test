package com.algo.company.booking;

import java.util.PriorityQueue;

public class ClosestTopKHotels {
    /**
     * Find the top K closest hotels within a price range. In the input grid 0 means a blocker which you can't pass through, 1 means road which you can use to navigate, any value above 1 is the price of the hotel located at x,y. You will be given this grid, price range and origin coordinates. You should return K hotels within price range and closest to the given origin.
     *
     * Input:
     * [
     * [1,2,0,1],
     * [1,3,0,1],
     * [0,2,5,1]
     * ]
     * https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/
     */
    public static void main(String[] args) {
//        System.out.println(shiftString("abcd", 1, 2));
        System.out.println(minimumResult(new int[]{10,20,7}, 4));
    }

    /**
     * Given a string, number of leftShift, number of rightShift. Return the result
     * example s=abcd, leftShift=1, rightShift=2
     * output dabc
     * @param s
     * @param left
     * @param right
     * @return
     */
    public static String shiftString(String s, int left, int right) {
        if (left==right) return s;
        boolean isLeft = left>right;
        int step = Math.abs(left-right);
        if (isLeft) {
            return s.substring(step)+s.substring(0,step);
        }
        return s.substring(s.length()-1)+s.substring(0, s.length()-1);
    }

    /**
     * Given an array of integers, perform some number k of operations. Each operation consists of
     * removing an element from the array, dividing it by 2 and inserting the ceiling of that result back
     * into the array. Minimize the sum of the elements in the final array.
     * Example:
     * nums = [10, 20, 7], k = 4
     * output 14
     *
     * Pick Pick/2 Ceiling Array
     * 7 3.5 4 [10,20,4]
     * 20 10 10 [10,10,4]
     * 10 5 5 [10,5,4]
     * 10 5 5 [5,5,4]
     * The sum of the final array is 5 + 5 + 4 = 14, and that sum is minimal.
     */
    public static int minimumResult(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a,b)->b-a);
        for (int num : nums) {
            queue.offer(num);
        }
        for (int i = 0; i < k; i++) {
            int cur = queue.poll();
            double ceil = Math.ceil(cur / 2.0);
            queue.offer((int)ceil);
        }
        int sum = 0;
        while (!queue.isEmpty()) sum+=queue.poll();
        return sum;
    }
}
