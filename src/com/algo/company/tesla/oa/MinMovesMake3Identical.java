package com.algo.company.tesla.oa;

public class MinMovesMake3Identical {

    public static void main(String[] args) {
        System.out.println(move("baaaaa"));
        System.out.println(move("baaabbaabbba"));
        System.out.println(move("baabab"));
    }

    public static int move(String s) {
        int n = s.length();
        int i = 0, res = 0;
        while (i<n) {
            int len = 1;
            while (i+len<n&&s.charAt(i)==s.charAt(i+len)) len++;
            res += len/3;
            i += len;
        }
        return res;
    }
}
