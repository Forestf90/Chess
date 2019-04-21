package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.SideColor;
import chess.panels.GamePanel;
import chess.pieces.Chessman;
import chess.pieces.King;
import chess.pieces.Rook;

class CheckTest {

	@Test
	void testCheck() {
		Chessman board[][] = new Chessman[8][8];
		board[0][0] = new King(SideColor.BLACK , 0 ,0);
		board[0][5] = new Rook(SideColor.WHITE , 0, 5);
		
		assertEquals(GamePanel.check(board , SideColor.BLACK), true);
		
	}

}
