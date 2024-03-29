package tests;

import chess.SideColor;
import chess.panels.GamePanel;
import chess.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {

    @Test
    void testCheck1() {
        Chessman[][] board = new Chessman[8][8];
        board[0][0] = new King(SideColor.BLACK, 0, 0);
        board[0][5] = new Rook(SideColor.WHITE, 0, 5);

        assertTrue(GamePanel.check(board, SideColor.BLACK));

    }

    @Test
    void testCheck2() {
        Chessman[][] board = new Chessman[8][8];
        board[1][6] = new King(SideColor.WHITE, 1, 6);
        board[6][1] = new Bishop(SideColor.BLACK, 6, 1);

        assertTrue(GamePanel.check(board, SideColor.WHITE));

    }


    @Test
    void testCheck3() {
        Chessman[][] board = new Chessman[8][8];
        board[5][5] = new King(SideColor.WHITE, 4, 5);
        board[3][5] = new Pawn(SideColor.BLACK, 3, 5);

        assertFalse(GamePanel.check(board, SideColor.WHITE));

    }

    @Test
    void testCheck4() {
        Chessman[][] board = new Chessman[8][8];
        board[4][5] = new King(SideColor.BLACK, 4, 5);
        board[5][5] = new Pawn(SideColor.WHITE, 5, 5);
        board[2][3] = new King(SideColor.WHITE, 2, 2);
        board[1][5] = new Knight(SideColor.BLACK, 1, 5);

        assertTrue(GamePanel.check(board, SideColor.WHITE));
    }
}
