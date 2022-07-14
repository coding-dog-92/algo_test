package com.algo.company.amz;

import java.util.Arrays;

public class DecodeStringIntoOriginalString {

    /**
     *  You are given an encoded string  and number of rows, Convert it to original string
     *
     *  Input: mnesi___ya__k____mime  N = 3
     *
     *  Output : my name is mike
     *
     *  Explanation : Read the matrix in a diagonal way starting from [0][0] index until the end of row and start from the top
     *  again to decode it. _ are treated as space.
     *
     *  m n e s i _  _
     *
     *   _ y a _ _ k _
     *
     *   _ _ _ m i m e
     */

    public static String decode(String s, int n) {
        int len = s.length();
        int colNum = len/n;
        char[][] matrix = new char[n][colNum];
        for (int i = 0; i < len; i++) {
            int row = i/colNum;
            int col = i%colNum;
            matrix[row][col] = s.charAt(i);
        }
        System.out.println(Arrays.deepToString(matrix));
        StringBuilder res = new StringBuilder();
        for (int k = 0; k < colNum; k++) {
            int j = k;
            for (int i = 0; i < n; i++) {
                if (j>colNum-1) break;
                System.out.println(i + " " + j);
                res.append(matrix[i][j]=='_'? ' ' : matrix[i][j]);
                j++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(decode("mnesi___ya__k____mime",3));
    }
}
