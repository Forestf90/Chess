package chess.panels;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chess.Menu;
import chess.Move;
import chess.Position;
import chess.SideColor;
import chess.pieces.Chessman;
import chess.pieces.King;

import javax.swing.JOptionPane;

public class GamePanelAI extends GamePanel{

	
	public boolean playerWhite;
	
	public GamePanelAI() {
		chooseColor();
	}
	private void chooseColor() {
		Object[] options = { "White", "Black" };
		int response =JOptionPane.showOptionDialog(null,"Chose color you want to play","Chose your color",
				JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE,null,options, options[0]);
				
		if (response == 0) {
			playerWhite = true;  
			whiteMove^=false;
	    } 
		if (response == 1) {
	        playerWhite = false; 
	        whiteMove^=true;
	    } 
	    else  {    	
	        //SwingUtilities.windowForComponent(this).dispose();
	    }

	}
	@Override
	void oponentTurn() {
		if(playerWhite==true) {
			AIBlackTurn();
		}
		if(playerWhite==false) {
			AIWhiteTurn();			
		}		
	}
	
	void AIBlackTurn() {
		enabled= false;
		newAI(SideColor.BLACK);
		enabled= true;
		}
	
	void AIWhiteTurn() {
		
		enabled= false;
		newAI(SideColor.WHITE);
		enabled= true;		
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	void AI(SideColor col) {
	
		ArrayList<Position> pieceMoves = new ArrayList<Position>();
		
		
		Position newposition = new Position(0,0);;
		Position oldposition = new Position(0,0);;
		int max = 0;
		int random = 0;
		//czym sie rusze
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(piecesBoard[i][j] != null){				
					if(piecesBoard[i][j].color == col){
						//zmien na preventCheck(piecesBoard[i][j].GetMoves(piecesBoard), piecesBoard ,piecesBoard[i][j])
						if(preventCheck(piecesBoard[i][j].GetMoves(piecesBoard), piecesBoard ,piecesBoard[i][j]).size() >0)
						{			
							random = new Random().nextInt(100);
							if(random > max)
							{								
								max = random;
								oldposition.x = i;
								oldposition.y = j;
								//to
								//no i tutaj tez jak w tym ife
								pieceMoves = preventCheck(piecesBoard[i][j].GetMoves(piecesBoard), piecesBoard ,piecesBoard[i][j]);
								random = new Random().nextInt(preventCheck(piecesBoard[i][j].GetMoves(piecesBoard), piecesBoard ,piecesBoard[i][j]).size());
								newposition = pieceMoves.get(random);									
							}	
						}
					}
				}			
			}
		}		
		moveChessman(newposition,piecesBoard[oldposition.x][oldposition.y]);							
	}
	
	
	public void newAI(SideColor col)
	{
		
		int depth;
		Position bestpos;
		Chessman [][] tempBoard = new Chessman[piecesBoard.length][];
		for(int i = 0; i < piecesBoard.length; i++)
			tempBoard[i] = piecesBoard[i].clone();
		
		Move newMove = minMax(3, tempBoard, col);
		if(newMove!= null) {
			
			moveChessman(newMove.end,piecesBoard[newMove.start.x][newMove.start.y]);
		}
		else {
			AI(col);
		}
	}
	
	// Zwarcana bedzie najlepsza lista pozycji Do moveChessman czyli pierw stara pozycja a pozniej nowa i po tej liscie iterator
	
	public Move minMax(int depth, Chessman[][] board, SideColor col) {
		Move move = null;
		
		int score = 0;
		int bestscore = 0;
		Position newbestmove = new Position(0,0);
		Position lastBestPosition = new Position(0,0);
	
		ArrayList<Position> pieceMoves = new ArrayList<Position>();		
		Collections.shuffle(pieceMoves);
		
		
		if (col == SideColor.WHITE) {
			
			for(int i = 0; i <=7; i++){
				for(int j = 0; j <=7; j++) {
					if(board[i][j] != null){				
						if(board[i][j].color == col){
								pieceMoves.addAll(preventCheck(board[i][j].GetMoves(board), board, board[i][j]));
								if(pieceMoves.size()>0)
								{
							for(Position p : pieceMoves) {
								score = Getscore(board,p); // jakim chujem liczyc te punkty funkcja potrzeba??
								if(score > bestscore) {
									score = bestscore;
									move = new Move(new Position(board[i][j].pos.x,board[i][j].pos.y),p);
								}
							}
							pieceMoves.clear();
								}
							
						}				
					}			
				}
			}			
		}
		
		//moveChessmanAI(newbestmove, board[lastBestPosition.x][lastBestPosition.y],board);
		
		//col.swapColor();
		if (col == SideColor.BLACK) {
			
			for(int i = 0; i <=7; i++){
				for(int j = 0; j <=7; j++) {
					if(board[i][j] != null){				
						if(board[i][j].color == col){
								pieceMoves.addAll(preventCheck(board[i][j].GetMoves(board), board, board[i][j]));
								if(pieceMoves.size()>0)
								{
							for(Position p : pieceMoves) {
								score = Getscore(board,p);
								if(score > bestscore) {
									score = bestscore;
									move = new Move(new Position(board[i][j].pos.x,board[i][j].pos.y),p);
									
								}
							}
							pieceMoves.clear();
								}
						}				
					}			
				}
			}			
		}
									
		return move;		
	}
	
	public int Getscore(Chessman[][] board, Position newPosition){
		int score =0;
		if(board[newPosition.x][newPosition.y] == null)
		{
			score = 0;
		}
		else {
			score = board[newPosition.x][newPosition.y].Value;
		}
		
		return score;
	}
	
	public void moveChessmanAI(Position newPosition ,Chessman piece, Chessman[][] board) {
		lastMove.clear();
		lastMove.add(piece.pos);
		lastMove.add(newPosition);
		
		if(piece instanceof King) {							
			castling(newPosition ,piece);
		}
		
		board[newPosition.x][newPosition.y]=piece;
		board[piece.pos.x][piece.pos.y] =null;
		piece.pos=newPosition;
		piece.notMoved = false;
		SideColor c =piece.color.swapColor();
		
	
		boolean isCheck =check(board ,c);
		if(!isCheck) {
			checkPosition=null;
			checkStalemate(piece.color.swapColor() , board);
		}
		else {
			checkPosition = findKing(board ,c);
			checkmate(piece.color.swapColor() , board);
		}
		
		
		
		//else checkPosition.add(kingPosition);
	}
	
}
