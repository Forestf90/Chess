package chess;

import java.util.ArrayList;

public class Rook extends Chessman {

	public Rook(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		
		loadImage();
		
		
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
		
		
		return moves;
		
	}
}
