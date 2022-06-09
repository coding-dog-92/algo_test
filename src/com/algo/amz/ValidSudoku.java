package com.algo.amz;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        // valid row
        Set<Character> rowSet = new HashSet<>();
        Set<Character>[] columnSets = new Set[9];
        for (int i = 0; i < 9; i++) {
            columnSets[i] = new HashSet();
        }
        Set<Character>[] subSets = new Set[9];
        for (int i = 0; i < 9; i++) {
            subSets[i] = new HashSet();
        }
        for (int i = 0; i < 9; i++) {
            rowSet.clear();
            for (int j = 0; j < 9; j++) {
                if (rowSet.contains(board[i][j])) {
                    return false;
                }
                if (columnSets[j].contains(board[i][j])) {
                    return false;
                }
                int idx = 3*(i/3)+j/3;
                if (subSets[idx].contains(board[i][j])) {
                    return false;
                }
                if (board[i][j] != '.') {
                    rowSet.add(board[i][j]);
                    columnSets[j].add(board[i][j]);
                    subSets[idx].add(board[i][j]);
                }
            }
        }

        return true;
    }

    public static int getIdx(int i, int j) {
        return 3*(i/3)+j/3;
    }

    public static void main(String[] args) {
        System.out.println(getIdx(3,0));
    }

}
