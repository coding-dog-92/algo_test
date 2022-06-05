package com.algo.MS;

public class Largestpossiblesquareforcutsticks {
    public static int largest(int a, int b) {
        int maxLength = (a+b)/4;
        while (maxLength>0) {
            if ((a/maxLength + b/maxLength) >= 4) {
                break;
            }
            maxLength--;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int largest = largest(1, 2);
        System.out.println(largest);
    }
}
