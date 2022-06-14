package com.algo.company.amz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // transfer to adjacency list
        ArrayList<Integer>[] lists = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            lists[i] = new ArrayList<>();
        }
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]] ++;
            lists[prerequisite[1]].add(prerequisite[0]);
        }
        // bfs
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer integer : lists[poll]) {
                inDegrees[integer]--;
                if (inDegrees[integer] == 0) {
                    queue.offer(integer);
                }
            }
        }

        for (int inDegree : inDegrees) {
            if (inDegree != 0) return false;
        }

        return true;
    }
}
