package com.algo.strings;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) {
            return false;
        }
        int[] map = new int[26];
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)-'a'] ++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (map[index] >0) {
                map[index] --;
                count --;
            }
        }
        return count==0;
    }
}
