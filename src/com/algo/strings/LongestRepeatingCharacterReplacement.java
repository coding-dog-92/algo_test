package com.algo.strings;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }
        int left = 0, right = 0, max = 0;
        int[] map = new int[26];
        while (right<s.length()) {
            int index = s.charAt(right)-'A';
            map[index]++;
            max = Math.max(max, map[index]);
            if (max+k < right-left+1) {
                map[s.charAt(left)-'A']--;
                left++;
            }
            right++;
        }
        return right-left;
    }

    public int longestSubstring(String s) {
        int left = 0, right = 0, max = 1, curLen = 1;
        Character c = null;
        for (right=0;right<s.length();right++){
            if (c != null && s.charAt(right) != c){
                curLen = 1;
            } else {
                curLen++;
                if (curLen<max) {
                    left++;
                }
            }
            max = Math.max(max, curLen);
            c = s.charAt(right);
        }
        return max;
    }

    public static void main(String[] args) {
        int res = new LongestRepeatingCharacterReplacement().longestSubstring("AABBBBCC");
        System.out.println(res);
    }
}
