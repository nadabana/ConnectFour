package com.ndbn.cfour;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardModelTest {

    private BoardModel boardModel;

    @Test
    public void canInsertOverlappedDiscs() {
        boardModel = new BoardModel(5, 5);

        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.RED);

        assertEquals(boardModel.getBoard()[4][1], 'G');
        assertEquals(boardModel.getBoard()[3][1], 'R');
    }

    @Test
    public void tableIsFull() {
        boardModel = new BoardModel(2, 2);

        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.RED);

        boardModel.insertDisc(0, PlayerColor.GREEN);
        boardModel.insertDisc(0, PlayerColor.RED);

        assertEquals(boardModel.getBoard()[0][1], 'R');
        assertEquals(boardModel.getBoard()[1][1], 'G');
        assertEquals(boardModel.getBoard()[0][0], 'R');
        assertEquals(boardModel.getBoard()[1][0], 'G');
        assertTrue(boardModel.isFull());
    }

    @Test
    public void hasFreeSpace() {
        boardModel = new BoardModel(2, 2);

        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(0, PlayerColor.GREEN);
        boardModel.insertDisc(0, PlayerColor.RED);

        assertTrue(boardModel.hasFreeSpace(1));
        assertFalse(boardModel.hasFreeSpace(0));
    }

    @Test
    public void isNotConnected() {
        boardModel = new BoardModel(8, 6);

        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.RED);

        assertFalse(boardModel.isConnected(3, 1));
    }

    @Test
    public void isConnectedHorizontally() {
        boardModel = new BoardModel(8, 6);

        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.GREEN);

        assertTrue(boardModel.isConnected(2, 1));
    }

    @Test
    public void isConnectedVertically() {
        boardModel = new BoardModel(8, 6);

        boardModel.insertDisc(0, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(2, PlayerColor.GREEN);
        boardModel.insertDisc(3, PlayerColor.GREEN);

        assertTrue(boardModel.isConnected(5, 0));
    }

    @Test
    public void isConnectedDiagonally() {
        boardModel = new BoardModel(8, 6);

        boardModel.insertDisc(0, PlayerColor.GREEN);
        boardModel.insertDisc(1, PlayerColor.RED);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(2, PlayerColor.RED);
        boardModel.insertDisc(2, PlayerColor.RED);
        boardModel.insertDisc(1, PlayerColor.GREEN);
        boardModel.insertDisc(3, PlayerColor.RED);
        boardModel.insertDisc(3, PlayerColor.RED);
        boardModel.insertDisc(3, PlayerColor.RED);
        boardModel.insertDisc(3, PlayerColor.GREEN);

        assertTrue(boardModel.isConnected(5, 5));
    }
}