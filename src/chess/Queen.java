package chess;

import java.util.ArrayList;

public class Queen extends Chessman {

	public Position pos;
	public Queen(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		
		loadImage();
		
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
	
		
		
		return moves;
		
	}
}
