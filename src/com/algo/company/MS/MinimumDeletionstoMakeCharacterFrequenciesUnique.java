package com.algo.company.MS;

import java.util.HashSet;
import java.util.Set;

public class MinimumDeletionstoMakeCharacterFrequenciesUnique {

    public int minDeletions(String s) {
        int[] countArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            countArr[s.charAt(i)-'a'] ++;
        }
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0 && set.contains(countArr[i])) {
                res ++;
                countArr[i] --;
            }
            set.add(countArr[i]);
        }
        return res;
    }
}
