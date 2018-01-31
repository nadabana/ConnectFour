package com.ndbn.cfour;

public class BoardView {
    public void printBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| " + (char)board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
