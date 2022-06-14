package com.algo.company.amz;

import java.util.ArrayDeque;
import java.util.Deque;

public class TotalPower {

    /**
     *
     */
    public static long findTotalPower(int[] power) {
        return 0;
    }

    /**
     *Input: arr = [3,1,2,4]
     * Output: 17
     * Explanation:
     * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
     * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
     * Sum is 17.
     */
    public static int sumSubarrayMins(int[] arr) {
        final int MOD = 1000000007;
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        long sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i]<arr[stack.getFirst()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.getFirst();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i>=0 ; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.getFirst()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.getFirst();
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            sum = sum + (long) arr[i] *(i-left[i])*(right[i]-i);
            if (sum>=MOD) {
                sum-=MOD;
            }
        }
        return (int) sum;

//        int n = arr.length;
//        long sum = 0;
//        for (int i = 0; i < n; i++) {
//            int min = arr[i];
//            for (int j = i; j < n; j++) {
//                if (arr[j]<min) {
//                    min = arr[j];
//                }
//                sum+=min;
//            }
//            if (sum>=MOD) {
//                sum-=MOD;
//            }
//        }
//        return (int)sum;
    }
}
