package com.algo.company.MS;

import com.Main;

import java.util.HashMap;
import java.util.Map;

public class DecorateRoom {
    /**
     * https://www.1point3acres.com/bbs/thread-909003-1-1.html
     */

    Map<Integer, Integer> level2LastIdxMap = new HashMap<>(), level2NoMap = new HashMap<>();
    public int decorate(int[] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 1; j <= shape[i]; j++) {
                if (!level2NoMap.containsKey(j)) level2NoMap.put(j, 1);
                if (level2LastIdxMap.containsKey(j) && i != 1+level2LastIdxMap.get(j)) {
                    int count = level2NoMap.getOrDefault(j, 1);
                    level2NoMap.put(j, count+1);
                }
                level2LastIdxMap.put(j, i);
            }
        }
        System.out.println(level2LastIdxMap);
        System.out.println(level2NoMap);
        int res = 0;
        for (Integer no : level2NoMap.values()) {
            res += no;
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new DecorateRoom().decorate(new int[]{1,3,2,1,2,1,5,3,3,4,2}));
        System.out.println(new DecorateRoom().decorate(new int[]{1,3,2,1,1,1,1,1,1,1,1}));

    }
}
