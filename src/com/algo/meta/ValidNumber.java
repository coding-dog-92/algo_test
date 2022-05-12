package com.algo.meta;

public class ValidNumber {
    /**
     * 1.at least 1 digit
     * 2.a sign must be in first or after e/E
     * 3.dot after digit
     * 4.e after digit
     *
     */
    public static boolean isNumber(String s) {
        boolean haveDigit = false, haveDot = false, haveExponent = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                haveDigit = true;
            } else if (c == '+' || c=='-'){
                if (i>0 && s.charAt(i-1)!='e'&&s.charAt(i-1)!='E') {
                    return false;
                }
            } else if(c=='e' || c=='E') {
                if (!haveDigit || haveExponent) {
                    return false;
                }
                haveExponent = true;
                haveDigit = false;
            } else if (c == '.') {
                if (haveExponent || haveDot) {
                    return false;
                }
                haveDot = true;
            } else {
                return false;
            }
        }
        return haveDigit;
    }
}
