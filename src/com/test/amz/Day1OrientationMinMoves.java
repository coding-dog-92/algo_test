package com.test.amz;

public class Day1OrientationMinMoves {

    /**
     *Given an array of binary digits, 0 and 1, sort the array so that all zeros are at one end and all ones are at the other. Which end does not matter. To sort the array, swap any two adjacent elements. Determine the minimum number of swaps to sort the array.
     *
     * Example
     * arr = [0, 1, 0, 1]
     * With 1 move, switching elements 1 and 2 yields [0, 0, 1, 1], a sorted array
     *
     * Function Description
     * Complete the function minMoves
     *
     * minMoves has the following parameter(s):
     * int arr[n]: an array of binary digits
     *
     * Returns
     * int: the minimum number of moves necessarry
     *
     * Constraints
     *
     * 1 <= n <= 10^5
     * arr[i] is in the set {0, 1}
     * Another Example
     * arr = [1, 1, 1, 1, 0, 0, 0 0]
     * We return 0 because we do not need to swap any elements
     */
    public static int minMoves(int[] arr) {
        int n = arr.length;
        int leftNum = 0, rightNum = 0;
        int curLeft = 0, curRight = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i]== 0) {
                curLeft++;
            } else {
                leftNum+=(curLeft+leftNum);
                curLeft=0;
            }
        }
        for (int i = n-1; i >= 0; i--) {
            if (arr[i]== 0) {
                curRight++;
            } else {
                rightNum+=(curRight+rightNum);
                curRight=0;
            }
        }
        return Math.min(leftNum, rightNum);
    }

    public static int minMoves1(int[] arr) {
        return Math.min(move(arr, 0), move(arr, 1));
    }

    public static int move(int[] arr, int k) {
        int count = 0, last = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==k) {
                count += i-last;
                last++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(minMoves1(new int[]{1, 1, 1, 1, 0, 1, 0, 1}));
    }
}
