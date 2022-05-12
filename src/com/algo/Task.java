package com.algo;

public class Task {

    public static void main(String[] args) {
        System.out.println(add(5,2));
        System.out.println(minus(5,2));
    }

    public static int add(int a, int b) {
        while (b!=0) {
            int sum = a^b;
            int carry = (a&b)<<1;
            a = sum;
            b = carry;
        }
        return a;
    }

    public static int minus(int a, int b) {
        while (b!=0) {
            int sum = a^b;
            int carry = (~a&b)<<1;
            a = sum;
            b = carry;
        }
        return a;
    }

    public static int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n&(n-1);
        }
        return sum;
    }



    public static int[] countBits(int n) {
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            res[i] = res[i>>1]+(i&1);
        }
        return res;
    }
}
