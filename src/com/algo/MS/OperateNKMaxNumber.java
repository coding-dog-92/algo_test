package com.algo.MS;

import java.util.Arrays;

public class OperateNKMaxNumber {
    public static int operate(int n, int k) {
        int[] position = new int[3];
        for (int i = 2; i >= 0; i--) {
            position[i] = n%10;
            n /= 10;
        }
        System.out.println(Arrays.toString(position));
        for (int i = 0; i < position.length; i++) {
            if (k == 0) {
                break;
            }
            if (position[i] >= 9) {
                continue;
            }
            while (position[i]<9 && k>0) {
                position[i] ++;
                k--;
            }
        }
        System.out.println(Arrays.toString(position));
        int res = 0;
        for (int i = 0; i < position.length; i++) {
            res = position[i] + res * 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(operate(191, 4));
    }
}
