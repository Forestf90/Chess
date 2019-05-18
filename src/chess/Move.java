package chess;

import chess.pieces.Chessman;

public class Move {

	public Position start;
	public Position end;
	public Move(Position startowa , Position koncowa) {
		start=startowa;
		end=koncowa;
	}
	
	Chessman piece;
}
