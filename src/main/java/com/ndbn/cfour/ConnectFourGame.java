package com.ndbn.cfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectFourGame {

    private static final int WIDTH = 3;
    private static final int HEIGHT= 3;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BoardModel boardModel = new BoardModel(WIDTH, HEIGHT);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);
        connectFourController.startGame(br);
    }

}