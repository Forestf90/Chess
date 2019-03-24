package chess;

import java.util.ArrayList;

public class Knight extends Chessman {

	public Knight(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		
		loadImage();
		
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
				
		if(pos.x+1 <= 7 && pos.y+2 <= 7 ) {
			
			if(board[pos.x+1][pos.y+2] == null ){
				moves.add(new Position(this.pos.x+1 ,this.pos.y+2));
			}
			if(board[pos.x+1][pos.y+2] != null ){
				if(board[pos.x+1][pos.y+2].color != this.color) {
					
					moves.add(new Position(this.pos.x+1 ,this.pos.y+2));
				}
			}		
			
		}
		if(pos.x+2 <= 7 && pos.y+1 <= 7 ) {
			if(board[pos.x+2][pos.y+1] == null){
				moves.add(new Position(this.pos.x+2 ,this.pos.y+1));		
			}
			if(board[pos.x+2][pos.y+1] != null ){
				if(board[pos.x+2][pos.y+1].color != this.color) {
					
					moves.add(new Position(this.pos.x+2 ,this.pos.y+1));
				}
			}		
		}
		if(pos.x+1 <= 7 && pos.y-2 >= 0 ) {
			if(board[pos.x+1][pos.y-2] == null){
				moves.add(new Position(this.pos.x+1 ,this.pos.y-2));											
			}
			if(board[pos.x+1][pos.y-2] != null ){
				if(board[pos.x+1][pos.y-2].color != this.color) {
					
					moves.add(new Position(this.pos.x+1 ,this.pos.y-2));
				}
			}		
		}	
				
		if(pos.x+2 <= 7 && pos.y-1 >= 0 ) {
							
			if(board[pos.x+2][pos.y-1] == null){
				moves.add(new Position(this.pos.x+2 ,this.pos.y-1));
			}
			if(board[pos.x+2][pos.y-1] != null ){
				if(board[pos.x+2][pos.y-1].color != this.color) {
					
					moves.add(new Position(this.pos.x+2 ,this.pos.y-1));
				}
			}		
		}
	
		
		if(pos.x-1 >= 0 && pos.y+2 <= 7 ) {
			
			if(board[pos.x-1][pos.y+2] == null){
				moves.add(new Position(this.pos.x-1 ,this.pos.y+2));
			}
			if(board[pos.x-1][pos.y+2] != null ){
				if(board[pos.x-1][pos.y+2].color != this.color) {
					
					moves.add(new Position(this.pos.x-1 ,this.pos.y+2));
				}
			}					
		}
		
		
		if(pos.x-2 >= 0 && pos.y+1 <= 7 ) {
			
			if(board[pos.x-2][pos.y+1] == null){
				moves.add(new Position(this.pos.x-2 ,this.pos.y+1));
			}
			if(board[pos.x-2][pos.y+1] != null ){
				if(board[pos.x-2][pos.y+1].color != this.color) {
					
					moves.add(new Position(this.pos.x-2 ,this.pos.y+1));
				}
			}					
		}
		
		if(pos.x-1 >= 0 && pos.y-2 >= 0 ) {
			if(board[pos.x-1][pos.y-2] == null){
				moves.add(new Position(this.pos.x-1 ,this.pos.y-2));			
			}
			if(board[pos.x-1][pos.y-2] != null ){
				if(board[pos.x-1][pos.y-2].color != this.color) {
					
					moves.add(new Position(this.pos.x-1 ,this.pos.y-2));
				}
			}		
		}
			
		if(pos.x-2 >= 0 && pos.y-1 >= 0 ) {
				
			if(board[pos.x-2][pos.y-1] == null){
				moves.add(new Position(this.pos.x-2 ,this.pos.y-1));						
			}
			if(board[pos.x-2][pos.y-1] != null ){
				if(board[pos.x-2][pos.y-1].color != this.color) {
					
					moves.add(new Position(this.pos.x-2 ,this.pos.y-1));
				}
			}		
		}
		
			
		return moves;
		
	}
}
