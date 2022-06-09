package com.algo.amz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StrengthPassword {
    /**
     * find password Strength for a given passwrod. e.g. if password= "good" . Then iterate over all substrings and find the distinct char counts:-
     * g = 1,
     * o = 1,
     * o = 1,
     * d = 1,
     * go = 2,
     * oo = 1,
     * od = 2,
     * goo = 2,
     * ood‍‍‌‍‍‌‍‌‍‌‌‍‍‍‌‌‍‍‍‌ = 2,
     * good = 3
     * and at the end add all. here 16 is the password strength and return.
     * Expected output = 16.
     */
    public static long passwordStrength(String password) {
        Map<Character, Integer> map = new HashMap<>();
        int curNum = 1;
        long count = 1;
        map.put(password.charAt(0), 1);
        for (int i = 1; i < password.length(); i++) {
            curNum += 1 + i - map.getOrDefault(password.charAt(i), 0);
            map.put(password.charAt(i), i + 1);
            count += curNum;
        }
        return count;
    }

    public static long passwordStrength1(String password) {
        if (password==null ||password.length()==0) {
            return 0;
        }
        long count = 1;
        int curNum = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(password.charAt(0), 1);
        for (int i = 1; i < password.length(); i++) {
            curNum += i+1-map.getOrDefault(password.charAt(i), 0);
            map.put(password.charAt(i), i+1);
            count+=curNum;
        }
        return count;
    }

    /**
     * dp: number of distinct letters for all substring end at i
     * lastIndex: last ‍‍‌‍‍‍‌‍‌‍‍‍‌‍‌‍‍‍‌‍index for each letter
     * There are total (i + 1) substring at [0, i].
     * Letter i add at most i + 1 new distinct letter in dp also need to substract previous substring count which already having letter i is lastIndex + 1,
     * dp = dp[i - 1] + i + 1 - (lastIndex + 1)
     * Time complexity: O(n) Space complexity: O(1)
     */
    public static long another(String s) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (i == 0)
                dp[i] = 1;
            else {
                dp[i] = dp[i - 1] + i + 1 - (last[index] + 1);
            }
            ans += dp[i];
            last[index] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(appealSum("abbca"));
    }


    /**
     * s, len, num of substrings, res
     * c, 1, 1, 1
     * co, 2, 3, 1*2+2=4
     * cod, 3, 6, 3*1+2*2+1=8, 3*1+2*2+1*3=10
     * code, 4, 4+3+2+1=10, 4*1+2*3+3*2+4=20
     *
     *
     * dp[i] = substrings end at i
     * s, index, len, dp[i]
     * c, 0, 1, 1
     * co, 1, 2, 1+2=3
     * cod, 2, 3, 1+2+3=6
     * code, 3, 4, 1+2+3+4=10
     *
     * res = dp[0]+dp[1]...+dp[3]
     * dp[i] = dp[i-1]+i+1
     *
     * a, 0, 1, 1
     * ab, 1, 2, 1+2=3
     * abb, 2, 3, 1+2+3-2=4  (b,bb,abb)
     * abbc, 3, 4, 1+2+3+4-2=8  (c,bc,bbc,abbc)
     * abbca, 4, 5, 1+2+3+4+5-3=12  (a,ca,bca,bbca,abbca)
     *
     * res = 1+3+4+8+12 = 28
     * when we add a new character, only res of s[j+1,i-1] to s[i-1,i-1] will increase
     * i-1-(j+1)+1 = i-1-j=i-j-1
     * dp[i] = dp[i-1]+(i-last[i])
     * @param s
     * @return
     */
    public static long appealSum(String s) {
        if (s==null||s.length()==0) return 0;
        long res = 0;
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] dp = new int[n];
        dp[0] = 1;
        last[s.charAt(0)-'a'] = 0;
        res += dp[0];
        // dp[i] = dp[i-1]+i-last[i];
        for (int i = 1; i < n; i++) {
            int idx = s.charAt(i)-'a';
            dp[i] = dp[i-1]+i-last[idx];
            last[idx] = i;
            res += dp[i];
        }
        return res;
    }

}
