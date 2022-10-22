package com.algo.company.flexport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZBoard {
    private char[][] board;
    private char[][] tmpBoard;
    public ZBoard(){
    }
    public void move(int[] current, int[] target, boolean isNextPlayer){
        if (canMove(current,target,isNextPlayer)){
            if (isNextPlayer){
                tmpBoard[target[0]][target[1]] = '2';
            }else {
                tmpBoard[target[0]][target[1]] = '1';
            }
            board = tmpBoard.clone();
        }else {
            System.out.println("Target position couldn't arrival!");
        }
    }
    public boolean canMove(int[] current, int[] target, boolean isNextPlayer){
        tmpBoard = board.clone();
        int x = current[0];
        int y = current[1];
        int targetX = target[0];
        int targetY = target[1];
        return moveNextStep(tmpBoard,x,y,targetX,targetY,isNextPlayer);
    }
    private boolean moveNextStep(char[][] newBoard,
                                 int x,int y,
                                 int targetX,int targetY,
                                 boolean isNextPlayer){
        // ⾛出范围
        // ||⽩棋：已经⾛到x坐标⼩于⽬标坐标x了说明⽆法到达
        // ||⿊棋：已经⾛到x坐标⼤于⽬标坐标x了说明⽆法到达
        if (x==targetX&&y==targetY){
            return true;
        }
        if (!inAreas(x,y)||
                (isNextPlayer&&(targetX>x))||
                (!isNextPlayer&&(targetX<x))){
            return false;
        }
        boolean ans = false;
        char tmp = newBoard[x][y];
        if (isNextPlayer){
            // ⽩旗⾛法
            if (newBoard[x][y]!='1'){
                newBoard[x][y] = '2';
                ans = moveNextStep(newBoard,x-1,y-1,targetX,targetY,isNextPlayer)||
                        moveNextStep(newBoard,x-1,y+1,targetX,targetY,isNextPlayer);
                if (!ans){
                    newBoard[x][y] = tmp;
                }
            }
        }else {
            // ⿊旗⾛法
            if (newBoard[x][y]!='2'){
                newBoard[x][y] = '1';
                ans = moveNextStep(newBoard,x+1,y+1,targetX,targetY,isNextPlayer)||
                        moveNextStep(newBoard,x+1,y-1,targetX,targetY,isNextPlayer);
                if (!ans){
                    newBoard[x][y] = tmp;
                }
            }
        }
        return ans;
    }
    /**
     *
     * @param isNextPlayer 那个player的可能要⾛的steps
     * @return
     */
    public List<int[]> getAllNextStep(boolean isNextPlayer){
        List<int[]> allNextStep = new ArrayList<>();
        if (isNextPlayer){
            getNextStep(board.length-1, board.length-1, allNextStep,isNextPlayer);
        }else {
            getNextStep(0,0, allNextStep,isNextPlayer);
        }
        return allNextStep;
    }
    private void getNextStep(int i,int j,List<int[]> steps,boolean isNextPlayer){
        if (!inAreas(i,j)){
            return;
        }
        if (board[i][j]=='0'){
            steps.add(new int[]{i,j});
            return;
        }
        if (isNextPlayer){
            // ⽩旗⾛法
            if (board[i][j]=='2'){
                getNextStep(i-1,j-1,steps,isNextPlayer);
                getNextStep(i-1,j+1,steps,isNextPlayer);
            }
        }else {
            // ⿊旗⾛法
            if (board[i][j]=='1'){
                getNextStep(i+1,j+1,steps,isNextPlayer);
                getNextStep(i+1,j-1,steps,isNextPlayer);
            }
        }
    }
    private boolean inAreas(int i,int j){
        return 0<=i&&i< board.length&&
                0<=j&&j<board.length;
    }
    public static void printArr(char[][] board){
        StringBuilder ans = new StringBuilder();
        ans.append("[");
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                ans.append(board[i][j]);
                if (j==board.length-1){
                    ans.append(',');
                };
            }
        }
        ans.append("]");
        System.out.println(new String(ans));
    }
    public static void main(String[] args) {
        ZBoard bg = new ZBoard();
        int n = 4;
        bg.board = new char[][]{
                {'1','0','0','0'},
                {'0','1','0','0'},
                {'0','0','2','0'},
                {'0','0','0','2'}
        };
        bg.board = new char[][]{
                {'0','0','0','0'},
                {'0','0','0','0'},
                {'0','0','2','0'},
                {'0','0','0','2'}
        };
        List<int[]> black = bg.getAllNextStep(false);
        for (int[] ints : black) {
            System.out.println(Arrays.toString(ints));
        }
//        int[] current = {1,1};
//        int[] target = {3,1};
//        boolean ans = bg.canMove(current,target,false);
//        System.out.println(ans);
//        bg.move(current,target,false);
//        printArr(bg.board);
    }

}
