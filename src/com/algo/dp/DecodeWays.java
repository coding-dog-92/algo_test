package com.algo.dp;

public class DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }

    /**
     *"226"
     * 3
     * dp[i] = {
     *     dp[i-1] when 1<=a=s[i-1]<=9
     *     dp[i-2] when 10<=b=s[i-2]*10+s[i-1]<=26
     *     dp[i-1]+dp[i-2] when condition1 and condition2
     * }
     */
    public static int numDecodings(String s) {
        if (s.length()==0)
            return 0;
        char[] chars = s.toCharArray();
        int first = 1, second = 1;
        for (int i = 1; i <= s.length(); i++) {
            int current = 0;
            int a = chars[i - 1] - '0';
            int b =i<2? a : (chars[i-2]-'0')*10+a;
            if(a>=1 && a<=9){
                current = second;
            }
            if (b>=10 && b<=26) {
                current += first;
            }
            first = second;
            second = current;
        }
        return second;
    }
}
