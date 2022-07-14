package com.algo.company.meta;

public class ValidWordAbbreviation {

    public static boolean valid(String a, String b) {
        if (a == null && b==null) {
            return true;
        }
        if (a==null || b==null) {
            return false;
        }
        char[] chars = b.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])&&i>0) {
                for (int i1 = 0; i1 < chars[i]-'0'-1; i1++) {
                    builder.append(chars[i-1]);
                }
            } else {
                builder.append(chars[i]);
            }
        }
        String res = builder.toString();
        System.out.println(res);
        return a.equals(res);
    }

    public static boolean validExact(String a, String b) {
        if (a == null && b==null) {
            return true;
        }
        if (a==null || b==null) {
            return false;
        }
        char[] achars = a.toCharArray();
        char[] bchars = b.toCharArray();
        int ap = 0;
        for (int i = 0; i < bchars.length; i++) {
            if (Character.isDigit(bchars[i])&&i>0) {
                ap += bchars[i]-'0'+1;
            } else if (achars[ap]!=bchars[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validExact("facebook", "f6k"));
    }
}
