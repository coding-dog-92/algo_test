package com.algo.strings;

import javafx.util.Pair;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (s.length()<2) {
            return s;
        }
        int maxLen = 0, start = 0;
        for (int i = 0; i < n; i++) {
            // try to generate odd length substring
            Pair<Integer, Integer> odd = expand(s, n, i, i);
            Pair<Integer, Integer> even = expand(s, n, i, i + 1);
            Pair<Integer, Integer> max = odd.getValue()>even.getValue()? odd:even;
            if (max.getValue()>maxLen) {
                maxLen = max.getValue();
                start = max.getKey();
            }
        }
        return s.substring(start, start+maxLen);
    }

    public Pair<Integer, Integer> expand(String s, int n, int left, int right) {
        while (left>=0 && right<n) {
            if (s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        // length = (right-1)-(left+1)+1 = right-left-1
        return new Pair<>(left+1, right-left-1);
    }

    public static void main(String[] args) {
        String s = new LongestPalindromicSubstring().longestPalindrome("babda");
        System.out.println(s);
    }

    public String longestPalindrome1(String s) {
        int len = s.length();
        if(len < 2) return s;

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            int[] odd = centerSpread(s, i, i);
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }

}
