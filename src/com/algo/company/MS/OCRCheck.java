package com.algo.company.MS;

public class OCRCheck {
    public boolean check(String s, String t) {
        String fullS = getFull(s);
        String fullT = getFull(t);
        System.out.println(fullS);
        System.out.println(fullT);
        if (fullS.length() != fullT.length()) {
            return false;
        }
        for (int i = 0; i < fullS.length(); i++) {
            if (fullS.charAt(i) != '?' && fullT.charAt(i) != '?' && fullS.charAt(i) != fullT.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public String getFull(String s) {
        char fillString = '?';
        StringBuilder sb = new StringBuilder();
        int curNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int n = c-'0';
                curNum = curNum * 10 + n;
            } else {
                if (curNum>0) {
                    for (int j = 0; j < curNum; j++) {
                        sb.append(fillString);
                    }
                    curNum = 0;
                }
                sb.append(c);
            }
            if (i == s.length()-1 &&curNum>0) {
                for (int j = 0; j < curNum; j++) {
                    sb.append(fillString);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        boolean res = new OCRCheck().check("0a", "a");
        System.out.println(res);
    }
}
