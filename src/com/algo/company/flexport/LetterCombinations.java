package com.algo.company.flexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
     * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
     */

    Map<String, String> digitMap = new HashMap<String, String>(){{
//        put("2", "abc");
//        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");

        put("23", "XYZ");
    }};

    List<String> res;
    int n;

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        n = digits.length();
        if (n==0) return res;
        dfs(digits, 0, new StringBuilder());
        return res;
    }

    void dfs(String digits, int idx, StringBuilder path) {
        if (idx==n) {
            res.add(path.toString());
            return;
        }
//        如果key的长度不止1，比如23对应"txw"，那么就要遍历
        for (int j=idx+1;j<=n;j++) {
            String cur = digits.substring(idx, j);
            String str = digitMap.get(cur);
            if (str==null || str.length()==0) continue;
            for (int i=0;i<str.length();i++) {
                path.append(str.charAt(i));
                dfs(digits, j, path);
                path.deleteCharAt(path.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> res = new LetterCombinations().letterCombinations("2345");
        System.out.println(res);

        res = new LetterCombinations().letterCombinations1("2345");
        System.out.println(res);
    }

    public List<String> letterCombinations1(String digits) {
        List<String> result = new ArrayList<>();
        dfs(result, digitMap, digits, 0, new StringBuilder());
        return result;
    }

    public static void dfs(List<String> result, Map<String, String> map, String digits, int index, StringBuilder current) {
        if (index == digits.length()) {
            result.add(current.toString());
        } else {
            for (int j = index + 1; j <= digits.length(); j++) {
                String key = digits.substring(index,j);
                String value = map.get(key);
                if (value == null) {
                    continue;
                }
                for(int i = 0; i < value.length(); i++) {
                    current.append(value.charAt(i));
                    dfs(result, map, digits, j, current);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }
}
