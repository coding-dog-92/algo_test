package com.algo.lqbz;

import java.util.Arrays;
import java.util.List;

public class AllSort {

    private List<Integer> list;

    public void allSort(int[] arr) {
        all(arr,0);
    }

    public void all(int[] arr, int start) {
        if (start==arr.length){
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = start; i < arr.length; i++) {
                swap(arr, start, i);
                all(arr, start+1);
                swap(arr, start, i);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        new AllSort().allSort(new int[]{1,2,3,4});
    }
}
