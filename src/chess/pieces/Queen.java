package chess.pieces;

import java.util.ArrayList;

import chess.Position;
import chess.SideColor;

public class Queen extends Chessman {

	public Queen(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		Value = 1000;
		loadImage();
		
	}
	
	public void loadImage() {
		if(this.color==SideColor.WHITE) {
			imgSrc=1;
		}else imgSrc=7;
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
		int movesRight = 7 - this.pos.x;
		int movesLeft = this.pos.x;
		int movesUp = this.pos.y;
		int movesDown = 7 - this.pos.y;		
		int i = 0;
		
		while(movesUp>0 ) {
			
			movesUp--;
			i++;
			
			if(board[pos.x][pos.y-i] == null){
				moves.add(new Position(this.pos.x ,this.pos.y-i));
								
			}
			
			if(board[pos.x][pos.y-i] != null) {							
				if(board[this.pos.x][this.pos.y-i].color != this.color) {
					moves.add(new Position(this.pos.x ,this.pos.y-i));
					break;				
				}
				else 
					break;				
			}		
		}
				
		i = 0;
			
		while(movesDown>0 ) {
			
			movesDown--;
			i++;
			if(board[pos.x][pos.y+i] == null) {
				moves.add(new Position(this.pos.x ,this.pos.y+i));
			}
			
			if(board[pos.x][pos.y+i] != null) {							
				if(board[this.pos.x][this.pos.y+i].color != this.color) {
					moves.add(new Position(this.pos.x ,this.pos.y+i));
					break;				
				}
				else 
					break;				
			}		
		}
				
		i = 0;
			
		while(movesLeft>0 ) {
			
			movesLeft--;
			i++;
			if(board[pos.x-i][pos.y] == null) {
				moves.add(new Position(this.pos.x-i ,this.pos.y));				
			}
			
			if(board[pos.x-i][pos.y] != null) {							
				if(board[this.pos.x-i][this.pos.y].color != this.color) {
					moves.add(new Position(this.pos.x-i ,this.pos.y));
					break;				
				}
				else 
					break;				
			}		
		}
				
		i = 0;
				
		while(movesRight>0 ) {
			
			movesRight--;
			i++;
			if(board[pos.x+i][pos.y] == null) {
				moves.add(new Position(this.pos.x+i ,this.pos.y));			
			}
			if(board[pos.x+i][pos.y] != null) {							
				if(board[this.pos.x+i][this.pos.y].color != this.color) {
					moves.add(new Position(this.pos.x+i ,this.pos.y));
					break;				
				}
				else 
					break;				
			}		
		}
		
		//NE
			 i = 1;
				
				while(this.pos.x+i <= 7 && this.pos.y-i >=0 ) {
				
					if(board[pos.x+i][pos.y-i] == null){
						moves.add(new Position(this.pos.x+i ,this.pos.y-i));
										
					}
					
					if(board[pos.x+i][pos.y-i] != null) {							
						if(board[this.pos.x+i][this.pos.y-i].color != this.color) {
							moves.add(new Position(this.pos.x+i ,this.pos.y-i));
							break;				
						}
						else 
							break;				
					}	
					i++;
				}
				
				//NW		
				i = 1;
				while(this.pos.x-i >= 0 && this.pos.y-i >=0 ) {

					if(board[pos.x-i][pos.y-i] == null){
						moves.add(new Position(this.pos.x-i ,this.pos.y-i));
										
					}
					
					if(board[pos.x-i][pos.y-i] != null) {							
						if(board[this.pos.x-i][this.pos.y-i].color != this.color) {
							moves.add(new Position(this.pos.x-i ,this.pos.y-i));
							break;				
						}
						else 
							break;				
					}	
					i++;
				}
				
				//SE
				i = 1;
				while(this.pos.x+i <= 7 && this.pos.y+i <=7 ) {
					
					if(board[pos.x+i][pos.y+i] == null){
						moves.add(new Position(this.pos.x+i ,this.pos.y+i));
										
					}
					
					if(board[pos.x+i][pos.y+i] != null) {							
						if(board[this.pos.x+i][this.pos.y+i].color != this.color) {
							moves.add(new Position(this.pos.x+i ,this.pos.y+i));
							break;				
						}
						else 
							break;				
					}
					i++;
				}
				
				//SW
				i = 1;
				
				while(this.pos.x-i >= 0 && this.pos.y+i <=7 ) {
					
					if(board[pos.x-i][pos.y+i] == null){
						moves.add(new Position(this.pos.x-i ,this.pos.y+i));
										
					}
					
					if(board[pos.x-i][pos.y+i] != null) {							
						if(board[this.pos.x-i][this.pos.y+i].color != this.color) {
							moves.add(new Position(this.pos.x-i ,this.pos.y+i));
							break;				
						}
						else 
							break;				
					}
					i++;
				}

		return moves;
		
	}
}
