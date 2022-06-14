package com.algo.company.amz;

import java.util.Arrays;

public class Maxdeviationamongallsubstrings {
    /**
     *  abbbcacbcdce
     *  [1,-1,-1,{-1],0,1},0,-1,0,0,0,0
     *  max sum of subarray
     *  -1 must occur
     *  dp1[i] sum of subarray end with nums[i]
     *  dp2[i] sum of subarray start with nums[i]
     *  res = dp1[i]+dp2[i]-nums[i]
     */
    public int largestVariance(String s) {
        int n = s.length();
        int[] countArr = new int[26];
        for (int i = 0; i < n; i++) {
            countArr[s.charAt(i)-'a']++;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (countArr[i] == 0 || countArr[j] == 0) {
                    continue;
                }
                if (i==j) continue;
                int[] numsArr = new int[n];
                for (int k = 0; k < n; k++) {
                    if (s.charAt(k)-'a'==i) {
                        numsArr[k] = 1;
                    } else if (s.charAt(k)-'a'==j) {
                        numsArr[k] = -1;
                    }
                }
                res = Math.max(res, maxSum(numsArr));
                System.out.println("i="+i+", j="+j);
                System.out.println(Arrays.toString(numsArr));
                System.out.println(res);
            }
        }
        return res;
    }

    int maxSum(int[] nums) {
        int res = 0;
        int n = nums.length;
        int[] dp1 = new int[n];
        dp1[0] = nums[0];
//        dp2[n-1] = nums[n-1];
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i-1]+nums[i], nums[i]);
        }
        int curSum = 0;
        for (int i = n-1; i >=0; i--) {
            curSum = Math.max(curSum+nums[i], nums[i]);
            if (nums[i] == -1) {
                res = Math.max(dp1[i]+curSum-nums[i], res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int res = new Maxdeviationamongallsubstrings().largestVariance("bbc");
        System.out.println(res);
    }
}
