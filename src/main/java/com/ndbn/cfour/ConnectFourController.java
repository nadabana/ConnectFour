package com.ndbn.cfour;

import java.io.BufferedReader;
import java.io.IOException;

public class ConnectFourController {

    private static final String PLAYER_ONE_WIN_MSG = "Player 1 [RED] wins!";
    private static final String PLAYER_TWO_WIN_MSG = "Player 2 [RED] wins!";
    private static final String PLAYER_ONE_PROMPT = "Player 1 [RED] - choose column (1-7):";
    private static final String PLAYER_TWO_PROMPT = "Player 2 [GREEN] - choose column (1-7):";
    private static final String COLUMN_FULL_MSG = "Column is full!";
    private static final String DRAW_MSG = "It's a draw!!!";
    private static final String INVALID_INPUT = "Invalid Input!!!";

    private PlayerColor player1Color;
    private PlayerColor player2Color;

    private BoardModel boardModel;
    private BoardView boardView;

    public ConnectFourController(BoardModel boardModel, BoardView boardView, PlayerColor player1Color, PlayerColor player2Color) {
        this.boardModel = boardModel;
        this.boardView = boardView;

        this.player1Color = player1Color;
        this.player2Color = player2Color;
    }

    public int readColumn(BufferedReader br, String promptMsg) {
        System.out.println("\n\n" + promptMsg);
        try {
            return Integer.parseInt(br.readLine()) - 1;
        } catch (IOException | NumberFormatException e) {
            System.out.println("\n\n" + INVALID_INPUT);
            return -1;
        }
    }

    public boolean isValid(int input) {
        if(boardModel.hasFreeSpace(input)) {
            return true;
        } else {
            System.out.println(COLUMN_FULL_MSG);
            return false;
        }
    }

    public boolean executeMove(int column, PlayerColor playerColor, String winMsg) {
        if(boardModel.insertDisc(column, playerColor)) {
            boardView.printBoard(boardModel.getBoard());
            System.out.println("\n\n" + PLAYER_ONE_WIN_MSG);
            return true;
        }

        boardView.printBoard(boardModel.getBoard());
        return false;
    }

    public int readInput(BufferedReader br, String promptMsg) {
        int column;
        do {
            column = readColumn(br, promptMsg);
        } while(!isValid(column));
        return column;
    }

    public void startGame(BufferedReader br) {;
        while(true) {
            int column = readInput(br, PLAYER_ONE_PROMPT);

            if(executeMove(column, player1Color, PLAYER_ONE_WIN_MSG)) {
                break;
            }

            if(boardModel.isFull()) {
                System.out.print(DRAW_MSG);
                break;
            }

            column = readInput(br, PLAYER_TWO_PROMPT);

            if(executeMove(column, player2Color, PLAYER_TWO_WIN_MSG)) {
                break;
            }

            if(boardModel.isFull()) {
                System.out.print(DRAW_MSG);
                break;
            }
        }
    }
}
