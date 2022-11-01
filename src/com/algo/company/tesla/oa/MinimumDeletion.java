package com.algo.company.tesla.oa;

public class MinimumDeletion {

    public static void main(String[] args) {
        System.out.println(minCost("abccbd", new int[]{0,1,2,3,4,5}));
        System.out.println(minCost("aabbcc", new int[]{1,2,1,2,1,2}));
        System.out.println(minCost("aaaa", new int[]{3,4,5,6}));
        System.out.println(minCost("ababa", new int[]{10,5,10,5,10}));
    }

    // 相同颜色的气球不能连续出现，因此在一个连续的区间内，只保留耗时最高的那个气球，其他的都要删除
    public static int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int sum = neededTime[0], res = 0, max = neededTime[0];
        for(int i=1;i<n;i++) {
            // System.out.println(i+"---"+sum+"-----"+max);
            if(colors.charAt(i)==colors.charAt(i-1)) {
                sum += neededTime[i];
                max = Math.max(max, neededTime[i]);
            } else {
                res += sum-max;
                sum = neededTime[i];
                max = neededTime[i];
            }
            // System.out.println(i+"---"+sum+"-----"+max);
        }
        res += sum-max;
        return res;
    }
}
