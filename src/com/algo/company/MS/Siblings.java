package com.algo.company.MS;

import java.util.ArrayList;
import java.util.List;

public class Siblings {
    /**
     * https://www.1point3acres.com/bbs/thread-909003-1-1.html
     */
    public static int siblings(int n) {
        List<Integer> list = new ArrayList<>();
        while (n>0) {
            list.add(n%10);
            n /= 10;
        }
        list.sort((a, b)->b-a);
        if (list.size()>=9 && list.get(0)>0) return -1;
        int res = 0;
        for (Integer i : list) {
            res = res*10+i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(siblings(83));
    }
}
