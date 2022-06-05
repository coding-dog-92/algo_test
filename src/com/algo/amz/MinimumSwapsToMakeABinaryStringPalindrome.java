package com.algo.amz;

public class MinimumSwapsToMakeABinaryStringPalindrome {
    /**
     * can change any two characters
     */
    public static int minSwapsRequired(String s) {
        int n = s.length();
        int numOfPairs = 0;
        int left = 0, right = n-1;
        while (left<=right) {
            if (s.charAt(left) != s.charAt(right)) {
                numOfPairs++;
            }
            left++;
            right--;
        }
        if ((n&1) == 0 && (numOfPairs&1) == 1) {
            return -1;
        }
        return (numOfPairs+1)/2;
    }

    public static void main(String[] args) {
        System.out.println(minSwapsRequired("0100101"));
    }
}
