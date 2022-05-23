package com.algo.strings;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length==0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        int[] countArr = new int[26];
        for (String str : strs) {
            Arrays.fill(countArr, 0);
            for (int i = 0; i < str.length(); i++) {
                countArr[str.charAt(i)-'a'] ++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : countArr) {
                sb.append(i);
                sb.append("#");
            }
            String index = sb.toString();
            if (!map.containsKey(index)) {
                map.put(index, new ArrayList<>());
            }
            map.get(index).add(str);
        }
        res.addAll(map.values());
        return res;
    }
}
