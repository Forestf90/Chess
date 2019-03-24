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
		
		
		if(pos.x <= 7 && pos.y+1 <= 7 ) {
			
			if(board[pos.x][pos.y+1] == null)
				moves.add(new Position(this.pos.x ,this.pos.y+1));
		}

		if(pos.x <= 7 && pos.y-1 >= 0 ) {
			
			if(board[pos.x][pos.y-1] == null)
				moves.add(new Position(this.pos.x ,this.pos.y-1));
		}
		
		if(pos.x+1 <= 7 && pos.y+1 <= 7 ) {
			
			if(board[pos.x+1][pos.y+1] == null)
				moves.add(new Position(this.pos.x+1 ,this.pos.y+1));
		}

		if(pos.x+1 <= 7 && pos.y-1 >= 0 ) {
			
			if(board[pos.x+1][pos.y-1] == null)
				moves.add(new Position(this.pos.x+1 ,this.pos.y-1));
		}
		
		if(pos.x-1 >= 0 && pos.y+1 <= 7 ) {
			
			if(board[pos.x-1][pos.y+1] == null)
				moves.add(new Position(this.pos.x-1 ,this.pos.y+1));
		}
		
		if(pos.x-1 >= 0 && pos.y-1 >= 0 ) {
			
			if(board[pos.x-1][pos.y-1] == null)
				moves.add(new Position(this.pos.x-1 ,this.pos.y-1));
		}
		
		if(pos.x+1 <= 7 && pos.y <= 7 ) {
			
			if(board[pos.x+1][pos.y] == null)
				moves.add(new Position(this.pos.x+1 ,this.pos.y));
		}
		
		if(pos.x-1 <= 7 && pos.y <= 7 ) {
			
			if(board[pos.x-1][pos.y] == null)
				moves.add(new Position(this.pos.x-1 ,this.pos.y));
		}
		
		return moves;
		
	}
}
