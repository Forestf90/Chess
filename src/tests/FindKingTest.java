package tests;

import chess.Position;
import chess.SideColor;
import chess.panels.GamePanel;
import chess.pieces.Bishop;
import chess.pieces.Chessman;
import chess.pieces.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindKingTest {

    @Test
    void findKingTest1() {

        Chessman[][] board = new Chessman[8][8];
        board[5][5] = new King(SideColor.BLACK, 5, 5);

        Position p = GamePanel.findKing(board, SideColor.BLACK);
        assertTrue(p.x == 5 && p.y == 5);

    }


    @Test
    void findKingTest2() {

        Chessman[][] board = new Chessman[8][8];
        board[3][4] = new King(SideColor.BLACK, 3, 4);
        board[2][2] = new King(SideColor.WHITE, 2, 2);
        board[2][3] = new Bishop(SideColor.BLACK, 2, 3);

        Position p = GamePanel.findKing(board, SideColor.BLACK);
        assertTrue(p.x == 3 && p.y == 4);

    }
}
