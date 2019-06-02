package chess.pieces;

import java.util.ArrayList;

import chess.Position;
import chess.SideColor;

public class King extends Chessman {

	
	public King(SideColor col, int x, int y) {
		color = col;
		pos = new Position(x, y);
		notMoved= true;
		Value = 10000;
		loadImage();

	}
	public void loadImage() {
		if(this.color==SideColor.WHITE) {
			imgSrc=0;
		}else imgSrc=6;
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {

		ArrayList<Position> moves = new ArrayList<Position>();
		;

		if (pos.x <= 7 && pos.y + 1 <= 7) {

			if (board[pos.x][pos.y + 1] == null)
				moves.add(new Position(this.pos.x, this.pos.y + 1));

			
			  if(board[this.pos.x][this.pos.y+1] !=null) {
			  if(board[this.pos.x][this.pos.y+1].color != this.color) moves.add(new
			  Position(this.pos.x ,this.pos.y+1)); }
			 
		}
		if (pos.x <= 7 && pos.y - 1 >= 0) {

			if (board[pos.x][pos.y - 1] == null)
				moves.add(new Position(this.pos.x, this.pos.y - 1));

			
			  if(board[this.pos.x][this.pos.y-1] !=null) {
			  if(board[this.pos.x][this.pos.y-1].color != this.color) moves.add(new
			  Position(this.pos.x ,this.pos.y-1)); }
			 
		}

		if (pos.x + 1 <= 7 && pos.y + 1 <= 7) {

			if (board[pos.x + 1][pos.y + 1] == null)
				moves.add(new Position(this.pos.x + 1, this.pos.y + 1));

			
			  if(board[this.pos.x+1][this.pos.y+1] !=null) {
			  if(board[this.pos.x+1][this.pos.y+1].color != this.color) moves.add(new
			  Position(this.pos.x+1 ,this.pos.y+1)); }
			 
		}

		if (pos.x + 1 <= 7 && pos.y - 1 >= 0) {

			if (board[pos.x + 1][pos.y - 1] == null)
				moves.add(new Position(this.pos.x + 1, this.pos.y - 1));

			
			  if(board[this.pos.x+1][this.pos.y-1] !=null) {
			  if(board[this.pos.x+1][this.pos.y-1].color != this.color) moves.add(new
			  Position(this.pos.x+1 ,this.pos.y-1)); }
			 
		}

		if (pos.x - 1 >= 0 && pos.y + 1 <= 7) {

			if (board[pos.x - 1][pos.y + 1] == null)
				moves.add(new Position(this.pos.x - 1, this.pos.y + 1));

			
			  if(board[this.pos.x-1][this.pos.y+1] !=null) {
			  if(board[this.pos.x-1][this.pos.y+1].color != this.color) moves.add(new
			  Position(this.pos.x-1 ,this.pos.y+1)); }
			 
		}

		if (pos.x - 1 >= 0 && pos.y - 1 >= 0) {

			if (board[pos.x - 1][pos.y - 1] == null)
				moves.add(new Position(this.pos.x - 1, this.pos.y - 1));

			
			  if(board[this.pos.x-1][this.pos.y-1] !=null) {
			  if(board[this.pos.x-1][this.pos.y-1].color != this.color) moves.add(new
			  Position(this.pos.x-1 ,this.pos.y-1)); }
			 
		}

		if (pos.x + 1 <= 7 && pos.y <= 7) {

			if (board[pos.x + 1][pos.y] == null)
				moves.add(new Position(this.pos.x + 1, this.pos.y));

			
			  if(board[this.pos.x+1][this.pos.y] !=null) {
			  if(board[this.pos.x+1][this.pos.y].color != this.color) moves.add(new
			  Position(this.pos.x+1 ,this.pos.y)); }
			 
		}

		if (pos.x - 1 >= 0 && pos.y <= 7) {

			if (board[pos.x - 1][pos.y] == null)
				moves.add(new Position(this.pos.x - 1, this.pos.y));

			
			  if(board[this.pos.x-1][this.pos.y] !=null) {
			  if(board[this.pos.x-1][this.pos.y].color != this.color) moves.add(new
			  Position(this.pos.x-1 ,this.pos.y)); }
			 
		}
		
		if (this.notMoved == true)
		{
			int pom = 1;
			
			while(this.pos.x-pom >= 0 ){
				if(board[pos.x-pom][pos.y] != null){
					if(board[pos.x-pom][pos.y] instanceof Rook) {
						if(board[pos.x-pom][pos.y].notMoved == true) {
							moves.add(new Position(this.pos.x - 2, this.pos.y));						
							break;
						}
					}
					else 					
						break;					
				}
				pom++;
			}		
		}
		
		if (this.notMoved == true){
			int pom = 1;
			
			while(this.pos.x+pom <= 7){
				if(board[pos.x+pom][pos.y] != null){
					if(board[pos.x+pom][pos.y] instanceof Rook) {
						if(board[pos.x+pom][pos.y].notMoved == true) {
							moves.add(new Position(this.pos.x + 2, this.pos.y));						
							break;
						}
					}
					else 					
						break;					
				}
				pom++;
			}			
		}
		
		return moves;

	}
}
