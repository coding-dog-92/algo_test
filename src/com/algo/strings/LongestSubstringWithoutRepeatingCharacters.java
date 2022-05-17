package com.algo.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        // char, position
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(map.get(c), left);
            }
            ans = Math.max(ans, right-left+1);
            map.put(c, right+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(" "));
    }
}
