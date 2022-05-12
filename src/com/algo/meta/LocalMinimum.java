package com.algo.meta;

public class LocalMinimum {

    public static int localMinimum(int[] arr) {
        int size = arr.length;
        if (arr[0] <= arr[1])
            return arr[0];
        if (arr[size - 1] <= arr[size - 2])
            return arr[size - 1];
        int left = 0;
        int right = size - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            if (arr[mid] <= arr[mid + 1] && arr[mid] <= arr[mid - 1])
                return arr[mid];
            else if (arr[mid] >= arr[mid + 1]) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }
        return -1;
    }
}
