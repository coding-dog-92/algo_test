package com.algo.company.flexport;

public class DecodeWays {
    // 使用dp
    // 对于当前字符，有两种解析方式，需要加起来
    // 首先只要不为0，自己可以解析为单个字符，那么dp[i]=dp[i-1]
    // 只有26个字母，所以解析的时候最多考虑当前字符的前一位，只有前一位不为0且两位解析之后小于26才是合法
    // 则dp[i]=dp[i-2]
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        s = " "+s;
        for(int i=1;i<=n;i++) {
            if(s.charAt(i)!='0') dp[i]+=dp[i-1];
            if(i>1&&s.charAt(i-1)!='0') {
                int num = 10*(s.charAt(i-1)-'0')+s.charAt(i)-'0';
                // System.out.println(num);
                if(num>0&&num<=26) dp[i]+=dp[i-2];
            }
        }
        return dp[n];
    }
}
