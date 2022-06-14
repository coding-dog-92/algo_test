package com.algo.lc.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resList = new ArrayList<>();
        int start = 0, n = intervals.length;
        while (start<n&&intervals[start][1]<newInterval[0]) {
            resList.add(intervals[start]);
            start++;
        }
        while (start<n&&newInterval[1]>=intervals[start][0]) {
            newInterval[0] = Math.min(intervals[start][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[start][1], newInterval[1]);
            start++;
        }
        resList.add(newInterval);
        while (start<n) {
            resList.add(intervals[start]);
            start++;
        }
        return resList.toArray(new int[resList.size()][2]);
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                new int[]{1,3},
                new int[]{6,9}
        };
        int[][] insert = new InsertInterval().insert(arr, new int[]{2, 5});
        System.out.println(Arrays.deepToString(insert));
    }
}
