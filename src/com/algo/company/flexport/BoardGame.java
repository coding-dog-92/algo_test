package com.algo.company.flexport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGame {

    /**
     * ⼀个棋盘，啥也不说。两边对⼿，没有block
     * 上⾯对⼿只能往下⾛，往斜对⻆⾛。即左下和右下
     * 下⾯对⼿只能往上⾛，斜对⻆⾛。左上和右上
     * 题解：
     * 1.getAllNextStep(char turn, char[][] board)
     * 2.canMove(Position current, Position target)
     * 3.move(Position current, Position target).
     */
    static class Panel{
        int m, n;
        int[][] board, tmpBoard;
        public Panel(int m, int n) {
            this.m = m;
            this.n = n;
            this.board = new int[m][n];
        }
        public void initPlayer(Player player) {
            board[player.getCurX()][player.getCurY()] = player.getPlayerVal();
        }
        public Panel(int[][] board) {
            this.board = board;
            this.m = board.length;
            this.n = board[0].length;
        }
        public List<int[]> getAllNextStep(Player player) {
            List<int[]> res = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];
            dfsGetNextStep(player.getCurX(), player.getCurY(), player, res, visited);
            return res;
        }
        private void dfsGetNextStep(int i, int j, Player player, List<int[]> path, boolean[][] visited) {
            if (!inArea(i, j)) return;
            if (visited[i][j]) return;
            // 只需要找到一个可达的位置就行，不需要递归寻找路径，每个颜色移动方向如果不会重叠，那么不需要visited，否则会导致无限循环
            if (board[i][j]==0) {
                path.add(new int[]{i,j});
                return;
            }
            if (board[i][j]==player.val) {
                int[][] dirs = player.getDirections();
                for(int k = 0; k< dirs.length; k++) {
                    visited[i][j] = true;
                    int x = i+dirs[k][0], y = j+dirs[k][1];
                    dfsGetNextStep(x, y, player, path, visited);
                    visited[i][j] = false;
                }
            }
        }
        public boolean canMove(int targetX, int targetY, Player player) {
            if (!inArea(targetX, targetY)) return false;
            tmpBoard = board.clone();
            return dfsCanMove(player.getCurX(), player.getCurY(), targetX, targetY, player, new boolean[m][n]);
        }
        private boolean dfsCanMove(int i, int j, int targetX, int targetY, Player player, boolean[][] visited) {
            if (i==targetX&&j==targetY) return true;
            if (!inArea(i, j)) return false;
            if (visited[i][j]) return false;
            int oldVal = tmpBoard[i][j];
            if (oldVal==0 || oldVal== player.getPlayerVal()) {
                int[][] dirs = player.getDirections();
                for(int k = 0; k< dirs.length; k++) {
                    visited[i][j] = true;
                    tmpBoard[i][j] = player.getPlayerVal();
                    int x = i+dirs[k][0], y = j+dirs[k][1];
                    boolean res = dfsCanMove(x, y, targetX, targetY, player, visited);
                    if (res) return true;
                    visited[i][j] = false;
                    tmpBoard[i][j] = oldVal;
                }
            }
            return false;
        }
        public void move(int targetX, int targetY, Player player) {
            if (!canMove(targetX, targetY, player)) {
                System.out.println("cannot move to target!");
                return;
            }
            tmpBoard[targetX][targetY] = player.getPlayerVal();
            board = tmpBoard;
        }
        private boolean inArea(int i, int j) {
            return i>=0&&i<m&&j>=0&&j<n;
        }
        public void printBoard() {
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }

    interface IPlayer {
        String getPlayerName();
        int getPlayerVal();
        int[][] getDirections();
        int getCurX();
        int getCurY();
    }

    static abstract class Player implements IPlayer{
        String name;
        int val, curX, curY;
        int[][] directions;
        @Override
        public String getPlayerName() {
            return name;
        }
        @Override
        public int getPlayerVal() {
            return val;
        }
        @Override
        public int[][] getDirections() {
            return directions;
        }
        @Override
        public int getCurX() {
            return curX;
        }
        @Override
        public int getCurY() {
            return curY;
        }
        public Player(String name, int val, int curX, int curY, int[][] directions) {
            this.name = name;
            this.val = val;
            this.curX = curX;
            this.curY = curY;
            this.directions = directions;
        }
    }
    static class BlackPlayer extends Player {
        public BlackPlayer(String name, int val, int curX, int curY, int[][] directions) {
            super(name, val, curX, curY, directions);
        }
    }
    static class WhitePlayer extends Player {
        public WhitePlayer(String name, int val, int curX, int curY, int[][] directions) {
            super(name, val, curX, curY, directions);
        }
    }

    public static void main(String[] args) {
//        int[][] board = {
//                {0,0,0,0},
//                {0,0,0,0},
//                {0,0,0,0},
//                {0,0,0,0}
//        };
        Panel panel = new Panel(4,4);
        Player black = new BlackPlayer("black", 1, 0, 0, new int[][]{{1,1},{1,-1}});
        Player white = new BlackPlayer("white", 2, 3, 3, new int[][]{{-1,1},{-1,-1}});
        panel.initPlayer(black);
        panel.initPlayer(white);
//        List<int[]> allNextStep = panel.getAllNextStep(black);
//        for (int[] ints : allNextStep) {
//            System.out.println(Arrays.toString(ints));
//        }
//        boolean canMove = panel.canMove(2, 3, black);
//        System.out.println(canMove);
        panel.printBoard();
        panel.move(2,0,black);
        System.out.println("=============");
        panel.printBoard();
        panel.move(1,3,white);
        System.out.println("=============");
        panel.printBoard();
    }
}
