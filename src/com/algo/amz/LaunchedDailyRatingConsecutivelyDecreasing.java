package com.algo.amz;

public class LaunchedDailyRatingConsecutivelyDecreasing {

    /**
     *
     * [4,3,5,4,3]
     * 5+3+1
     * 5 one day periods: 4 3 5 4 3
     * 3 two day periods: 43 54
     * 1 three day period: 543
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

    public static void main(String[] args) {
//        System.out.println(new DecreasingRating().count(new int[]{4,3,5,4,3}, 3));
        System.out.println(new DecreasingRating().countDecreasingRatings(new int[]{4,3,5,4,3}));
    }


}
