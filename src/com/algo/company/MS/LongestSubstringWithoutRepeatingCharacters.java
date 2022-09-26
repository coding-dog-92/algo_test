package com.algo.company.MS;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s==null || s.length()==0) return 0;
        int[] map = new int[128];
        int left = 0, len = 0;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            left = Math.max(left, map[c]);
            len = Math.max(len, i-left+1);
            map[c] = i+1;
        }
        return len;
    }

    public static void main(String[] args) {
//        int res = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abba");
//        System.out.println(res);
        StringBuilder sb = new StringBuilder("abcd");
        sb = sb.delete(sb.length()-1, sb.length());
        System.out.println(sb.toString());
    }
}
