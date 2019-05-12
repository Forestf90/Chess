package chess;

import chess.pieces.Chessman;

public class Move {

	public Position start;
	public Position end;
	public Move(Position ix , Position iy) {
		start=ix;
		end=iy;
	}
	
	Chessman piece;
}
