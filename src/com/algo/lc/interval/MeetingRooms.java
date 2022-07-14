package com.algo.lc.interval;

import java.util.Arrays;

public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length==0) {
            return true;
        }
        Arrays.sort(intervals, (a,b)->(a[1]-b[1]));
        int right = intervals[0][1], n = intervals.length;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0]<right) {
                return false;
            }
            right = intervals[i][1];
        }
        return true;
    }
}
