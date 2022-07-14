package com.algo.company.booking;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MinKnightMoves {

    /**
     * https://leetcode.ca/all/1197.html
     * @param x
     * @param y
     * @return
     */
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        int res = 0;
        int[] xDir = {1,1,2,2,-1,-1,-2,-2};
        int[] yDir = {2,-2,1,-1,2,-2,1,-1};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        Set<String> set = new HashSet<>();
        set.add("0-0");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                if (x == pos[0] && y == pos[1]) return res;
                for (int j = 0; j < xDir.length; j++) {
                    int px = pos[0]+xDir[j];
                    int py = pos[1]+yDir[j];
                    String key = px+"-"+py;
                    if (!set.contains(key) && px>=-2 && py>=-2) {
                        queue.offer(new int[]{px, py});
                        set.add(key);
                    }
                }
            }
            res ++;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new MinKnightMoves().minKnightMoves(5,5));
    }
}
