package com.algo.arrays;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int start = 0, end = height.length-1;
        int maxArea = 0;
        while (start <= end) {
            int area = Math.min(height[start], height[end]) * (end-start);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return maxArea;
    }
}
