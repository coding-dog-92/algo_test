package com.algo.company.flexport;

public class TicTacToe {
    int[] rows, cols;
    int dia, antiDia,n;

    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        dia = 0;
        antiDia = 0;
        this.n = n;
    }

    public int move(int row, int col, int player) {
        int val = player==1? 1 : -1;
        rows[row] += val;
        cols[col] += val;
        if(row==col) dia+=val;
        if(row+col==n-1) antiDia+=val;
        if(Math.abs(rows[row])==n || Math.abs(cols[col])==n || Math.abs(dia)==n || Math.abs(antiDia)==n) return player;
        return 0;
    }
}
