package com.ndbn.cfour;

public class BoardModel {

    private final int width;
    private final int height;

    private int totalMovesPlayed;
    private int[][] board;

    public BoardModel(int width, int height) {
        this.width = width;
        this.height = height;

        board = new int[height][width];
        totalMovesPlayed = 0;
    }

    //get the current board status
    public int[][] getBoard() {
        return board;
    }

    public boolean insertDisc(int column, PlayerColor playerColor) {
        int i = 0;
        for(i = 0; i < height; i++) {
            if(board[i][column] == 'R' || board[i][column] == 'G') {
                board[i - 1][column] = playerColor.getColor();
                break;
            }
        }

        if(i == height) {
            board[i - 1][column] = playerColor.getColor();
        }

        totalMovesPlayed++;
        return isConnected(i - 1, column);
    }

    public boolean hasFreeSpace(int column) {
        return column >= 0 && column < height && board[0][column] == 0;
    }

    public boolean isFull() {
        return totalMovesPlayed == height * width;
    }

    public boolean isConnected(int x, int y) {
        int num = board[x][y];
        int count = 0;
        int i = y;

        while(i < width && board[x][i] == num) {
            count++;
            i++;
        }
        i = y - 1;
        while(i >= 0 && board[x][i] == num) {
            count++;
            i--;
        }
        if(count == 4) {
            return true;
        }

        count = 0;
        int j = x;
        while(j < height && board[j][y] == num) {
            count++;
            j++;
        }

        if(count == 4) {
            return true;
        }
        count = 0;
        i = x;
        j =y ;
        while(i < height && j < width && board[i][j] == num) {
            count++;
            i++;
            j++;
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 && j >= 0 && board[i][j] == num) {
            count++;
            i--;
            j--;
        }
        if(count == 4) {
            return true;
        }

        count = 0;
        i = x;
        j = y;
        while(i < height && j >= 0 && board[i][j] == num) {
            count++;
            i++;
            j--;
        }
        i = x - 1;
        j = y + 1;
        while(i >= 0 && j < width && board[i][j] == num) {
            count++;
            i--;
            j++;
        }
        if(count == 4) {
            return true;
        }
        return false;
    }
}
