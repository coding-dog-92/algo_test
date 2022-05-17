package com.algo.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
        int leftNumOfElements = matrix.length * matrix[0].length;
        while (leftNumOfElements > 0) {
            // left->right
            for (int i = left; i <= right && leftNumOfElements > 0; i++) {
                res.add(matrix[top][i]);
                leftNumOfElements -- ;
            }
            top ++;
            // top->bottom
            for (int i = top; i <= bottom && leftNumOfElements > 0; i++) {
                res.add(matrix[i][right]);
                leftNumOfElements --;
            }
            right --;
            // right->left
            for (int i = right; i >= left && leftNumOfElements > 0; i--) {
                res.add(matrix[bottom][i]);
                leftNumOfElements --;
            }
            bottom --;
            // bottom->top
            for (int i = bottom; i >= top && leftNumOfElements > 0; i--) {
                res.add(matrix[i][left]);
                leftNumOfElements --;
            }
            left ++;
        }
        return res;
    }
}
