package com.algo.arrays;

public class BestTimetoBuyandSellStock {

    public int maxProfit(int[] prices) {
        int max = 0, curMin = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < curMin) {
                curMin = prices[i];
            }
            max = Math.max(max, prices[i]-curMin);
        }
        return max;
    }
}
