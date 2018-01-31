package com.ndbn.cfour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConnectFourControllerTest {

    @Mock
    BufferedReader bufferedReader;

    @Test
    public void isValid() throws IOException {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        assertTrue(connectFourController.isValid(2));
    }

    @Test
    public void isNotValid() throws IOException {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        assertFalse(connectFourController.isValid(10));
    }

    @Test
    public void charactersAreNotValid() throws IOException {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        assertFalse(connectFourController.isValid('c'));
    }

    @Test
    public void executeWinnerMoveReturnsTrue() {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        connectFourController.executeMove(1, PlayerColor.GREEN, "green is winner");
        connectFourController.executeMove(1, PlayerColor.GREEN, "green is winner");
        connectFourController.executeMove(1, PlayerColor.GREEN, "green is winner");

        assertTrue(connectFourController.executeMove(1, PlayerColor.GREEN, "green is winner"));
    }

    @Test
    public void executeMoveReturnsFalse() {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        assertFalse(connectFourController.executeMove(1, PlayerColor.GREEN, "green is winner"));
    }

    @Test
    public void testRedWins() throws IOException {
        BoardModel boardModel = new BoardModel(8, 6);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        when(bufferedReader.readLine())
                .thenReturn("1", "2", "1", "2", "1", "2", "1");

        connectFourController.startGame(bufferedReader);
    }

    @Test
    public void testDrawGame() throws IOException {
        BoardModel boardModel = new BoardModel(3, 3);
        BoardView boardView = new BoardView();
        ConnectFourController connectFourController = new ConnectFourController(boardModel, boardView, PlayerColor.RED, PlayerColor.GREEN);

        when(bufferedReader.readLine())
                .thenReturn("1", "1", "1", "2", "2", "2", "3", "3", "3");

        connectFourController.startGame(bufferedReader);

    }
}