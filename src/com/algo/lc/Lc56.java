package com.algo.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc56 {
    //    56. Merge Intervals
//    Medium
//    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//    Example 1:
//
//    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//    Output: [[1,6],[8,10],[15,18]]
//    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//    Example 2:
//    Input: intervals = [[1,4],[4,5]]
//    Output: [[1,5]]
//    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//            Constraints:
//            1 <= intervals.length <= 104
//    intervals[i].length == 2
//            0 <= starti <= endi <= 104
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b)->a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        int[] pre = intervals[0];
        for(int i=1;i<n;i++) {
            int[] cur = intervals[i];
            if(cur[0]>pre[1]) {
                res.add(pre);
                pre = cur;
            } else {
                pre[1] = Math.max(pre[1], cur[1]);
            }
        }
        res.add(pre);
        return res.toArray(new int[0][0]);
    }
}
