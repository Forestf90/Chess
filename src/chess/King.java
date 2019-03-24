package chess;

import java.util.ArrayList;

public class King extends Chessman {

	public King(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		
		loadImage();
		
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
		if(board[pos.x+1][pos.y+2] == null)
			moves.add(new Position(this.pos.x+1 ,this.pos.y+2));
								
		return moves;
		
	}
}
