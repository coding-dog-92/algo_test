package com.algo;

import java.util.ArrayList;
import java.util.List;

public class LC99 {
    private List<List<Integer>> ans = new ArrayList<>();
    private int count = 0;

    public List<List<Integer>> combine(int n, int k) {
        getCombine(n, k, 1, new ArrayList<>());
        return ans;
    }

    public void getCombine(int n, int k, int start, List<Integer> list) {
        if(k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int i = start;i <= n - k + 1;i++) {
            list.add(i);
            getCombine(n, k - 1, i+1, list);
            list.remove(list.size() - 1);
        }
    }

    public int getCount(int n, int k, int start) {
        if (k==0){
            count ++;
            return count;
        }
        for (int i = start; i <= n - k + 1; i++) {
            getCount(n, k-1, i+1);
        }
        return count;
    }

    public int getAllCount(int n, int k) {
        return getCount(n, k, 1);
    }

    public static void main(String[] args) {
        LC99 lc99 = new LC99();
        lc99.combine(4,2);
        System.out.println(lc99.ans);
    }
}
