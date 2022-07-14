package com.algo.company.uber;

public class UpDownMove {

    public static int move(String[] moves) {
        int y = 0;
        for (String s : moves) {
            if ("U".equals(s)) y+=1;
            else y -= 1;
        }
        return Math.abs(y);
    }

    public static void main(String[] args) {
        System.out.println(move(new String[]{"U","U","D"}));
    }
}
