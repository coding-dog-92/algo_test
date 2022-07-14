package com.algo.company.uber;

import java.math.BigDecimal;
import java.util.Arrays;

public class FindTheNextSmallestPalindrome {

    /**
     * palindrome
     * odd:
     * 12311->12321
     * 12345->12321->12421
     * 999->1009->1001
     *
     * 12945->12921->13021->13031
     *
     * 1.mix=left+mid
     * 2.if reverse(left)<right
     *      mix += 1
     * 3.res = mix+reverse(mix)-reverse(mix)[0]
     *
     *
     * even:
     * 1220->1221
     * 1234->1221->1331
     * 1996->2096->2002
     */
    public String solve(String A) {
        if (A.equals(reverse(A))) {
            A = stringPlus(A, 1);
        }
        int length = A.length();
        if ((length & 1) == 1) {
            return solveOdd(A, length);
        }
        return solveEven(A, length);
    }

    String solveOdd(String A, int n) {
        String left = A.substring(0, n/2);
        String mid = A.substring(n/2, n/2+1);
        String right = A.substring(n/2+1);
        String mix = left+mid;
        if (compareString(right, reverse(left))==1) {
            mix = stringPlus(mix, 1);
        }
        return mix+reverse(mix).substring(1);
    }

    String solveEven(String A, int n) {
        String left = A.substring(0, n/2);
        String right = A.substring(n/2);
        if (compareString(right, reverse(left))==1) {
            left = stringPlus(left, 1);
        }

        return left+reverse(left);
    }

    String stringPlus(String s, int k) {
        char[] chars = s.toCharArray();
        int[] sum = new int[s.length()+1];
        int idx = s.length()-1;
        for (int i = sum.length-1; i >=0 ; i--) {
            if (i==sum.length-1) {
               sum[i] = chars[idx--]-'0'+k;
            } else if (idx>=0){
                sum[i] =  chars[idx--]-'0';
            }
        }
        for (int i = sum.length-1; i >0 ; i--) {
            sum[i-1] += sum[i]/10;
            sum[i] %= 10;
        }
        sum[0] += sum[1]/10;
        StringBuilder res = new StringBuilder();
        if (sum[0] == 1) {
            res.append(1);
        }
        for (int i = 1; i < sum.length; i++) {
            res.append(sum[i]);
        }
        return res.toString();
    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    int compareString(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i)-'0'>b.charAt(i)-'0') {
                return 1;
            } else if (a.charAt(i)-'0'<b.charAt(i)-'0') {
                return -1;
            }
        }
        return 0;
    }



    public String find(String A) {
        BigDecimal oriNum = new BigDecimal(A).add(BigDecimal.valueOf(1));
        while (!isPalindrome(String.valueOf(oriNum))) {
            oriNum = oriNum.add(BigDecimal.valueOf(1));
        }
        return String.valueOf(oriNum);
    }



    public boolean isPalindrome(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return s.equals(reverse);
    }

    public static void main(String[] args) {
        String s = new FindTheNextSmallestPalindrome().solve("171152");
        System.out.println(s);
//        System.out.println(new FindTheNextSmallestPalindrome().compareString("171", "152"));
    }
}
