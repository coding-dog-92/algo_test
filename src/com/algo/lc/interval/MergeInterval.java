package com.algo.lc.interval;

import java.util.*;

public class MergeInterval {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        Deque<int[]> resList = new LinkedList<>();

        for (int[] interval : intervals) {
            if (resList.isEmpty() || resList.getLast()[1]<interval[0]) {
                resList.add(interval);
            } else {
                resList.getLast()[1] = Math.max(resList.getLast()[1], interval[1]);
            }
        }
        return resList.toArray(new int[resList.size()][2]);
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                new int[]{1,3},
                new int[]{2,6},
                new int[]{8,16},
                new int[]{15,18}
        };
        int[][] insert = new MergeInterval().merge(arr);
        System.out.println(Arrays.deepToString(insert));
    }
}
