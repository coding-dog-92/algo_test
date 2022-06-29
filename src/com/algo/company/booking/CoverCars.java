package com.algo.company.booking;

import java.util.Arrays;

public class CoverCars {
    /**
     *  You are given an List of positions of cars as to where they are parked. You are also given an integer K.
     *  The cars needs to be covered with a roof. You have to find the minimum length of roof that takes to cover K cars.
     *
     *  Input : 12,6,5,2      K = 3
     *
     *  Explanation :  There are two possible roofs that can cover K cars. One that covers the car in 2,5,6 parking spots and
     *  another roof which covers 5,6,12 parking spots. The length of these two roofs are 5 and 8 respectively. Return 5
     *  since that's the roof with minimum length that covers K cars.
     *
     *  Output : 5
     */
    public static int findMinLengthCoverCars(int[] cars, int k) {
        Arrays.sort(cars);
        int n = cars.length;
        if (k>=n) return cars[n-1]-cars[0]+1;
        int left = 0, right = k-1;
        int res = cars[n-1]-cars[0]+1;
        while (right<n) {
            res = Math.min(cars[right]-cars[left]+1, res);
            left++;
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findMinLengthCoverCars(new int[]{12,6,5,2}, 9));
    }
}
