package tests;

import chess.Position;
import chess.SideColor;
import chess.panels.GamePanel;
import chess.pieces.Bishop;
import chess.pieces.Chessman;
import chess.pieces.King;
import chess.pieces.Queen;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PreventCheckTest {
    @Test
    void preventCheckMoves1() {

        Chessman[][] board = new Chessman[8][8];
        board[5][5] = new Queen(SideColor.BLACK, 5, 5);
        board[5][0] = new King(SideColor.WHITE, 5, 0);
        Bishop bishop = new Bishop(SideColor.WHITE, 5, 1);
        board[5][1] = bishop;

        assertTrue(GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).isEmpty());
    }

    @Test
    void preventCheckMoves2() {

        Chessman[][] board = new Chessman[8][8];
        board[5][5] = new Queen(SideColor.BLACK, 5, 5);
        board[5][0] = new King(SideColor.WHITE, 5, 0);
        Bishop bishop = new Bishop(SideColor.WHITE, 6, 0);
        board[6][0] = bishop;

        assertTrue(GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).size() == 1);

        Position temp = GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).get(0);
        assertTrue(temp.x == 5 && temp.y == 1);
    }


    @Test
    void preventCheckMoves3() {

        Chessman[][] board = new Chessman[8][8];
        board[5][5] = new Queen(SideColor.BLACK, 6, 5);
        board[5][0] = new King(SideColor.WHITE, 5, 0);
        Bishop bishop = new Bishop(SideColor.WHITE, 6, 0);
        board[6][0] = bishop;

        ArrayList<Position> temp = bishop.GetMoves(board);
        assertSame(temp, GamePanel.preventCheck(temp, board, bishop));
    }

}
