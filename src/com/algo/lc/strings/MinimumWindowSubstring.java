package com.algo.lc.strings;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s==null || t == null || s.length()==0 || t.length() == 0 || t.length()>s.length()) {
            return "";
        }
        int left = 0, right = 0, count = t.length(), size = s.length()+1, start = 0;
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)] ++;
        }
        while (right<s.length()) {
            char c = s.charAt(right);
            if (need[c]>0) {
                count--;
            }
            need[c]--;
            if (count==0) {
                while (left<right && need[s.charAt(left)]<0) {
                    need[s.charAt(left)]++;
                    left++;
                }
                if (right-left+1 < size) {
                    size = right-left+1;
                    start = left;
                }
                need[s.charAt(left)]++;
                left++;
                count++;
            }
            right++;
        }
        return size==s.length()+1? "" : s.substring(start, start+size);
    }
}
