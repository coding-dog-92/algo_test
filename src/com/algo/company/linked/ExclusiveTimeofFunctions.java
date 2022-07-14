package com.algo.company.linked;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class ExclusiveTimeofFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();
        for(String log : logs) {
            String[] arr = log.split(":");
            int id = Integer.parseInt(arr[0]);
            int timeStamp = Integer.parseInt(arr[2]);
            if("start".equals(arr[1])) {
                stack.push(new int[]{id, timeStamp});
            } else {
                int[] current = stack.peek();
                int duration = timeStamp-current[1]+1;
                res[id] += duration;
                stack.pop();
                if(!stack.isEmpty()) {
                    int preId = stack.peek()[0];
                    res[preId] -= duration;
                }
            }
        }
        return res;
    }
}
