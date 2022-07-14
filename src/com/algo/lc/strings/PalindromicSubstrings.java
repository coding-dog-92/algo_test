package com.algo.lc.strings;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length()<2) {
            return 1;
        }
        int sum = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            sum += expand(s, n, i, i);
            sum += expand(s, n, i, i+1);
        }
        return sum;
    }

    public int expand(String s, int n, int left, int right) {
        int sum = 0;
        while (left>=0 && right<n) {
            if (s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
                sum++;
            } else {
                break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int num = new PalindromicSubstrings().countSubstrings("aaa");
        System.out.println(num);
    }
}
