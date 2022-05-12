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
        System.out.println(passwordStrength1("good"));
    }

}
