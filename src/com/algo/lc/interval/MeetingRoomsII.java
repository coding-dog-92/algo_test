package com.algo.lc.interval;

import java.util.Arrays;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));
        int[][] list = new int[intervals.length*2][2];
        int i = 0;
        for (int[] interval : intervals) {
            list[i++] = new int[]{interval[0], 1};
            list[i++] = new int[]{interval[1], -1};
        }
        Arrays.sort(list, (a,b)->(a[0]-b[0]));
        int ans = 0, sum = 0;
        for (int[] ints : list) {
            sum += ints[1];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
