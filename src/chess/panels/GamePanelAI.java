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
import chess.pieces.Pawn;
import chess.pieces.Queen;
import jdk.nashorn.internal.ir.CallNode.EvalArgs;

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
		moveChessman(newposition,oldposition);							
	}
	
	
	public void newAI(SideColor col)
	{
		
		Move move = minMax(3, piecesBoard, col);
		if(move!= null) {
		moveChessman(move.end, move.start );
		}
		else
			AI(col);

	}
		
	public Move minMax(int depth, Chessman[][] board, SideColor col) {

		int bestscore = 0;
		int bestID = 0;
		while(depth > 0) {			
			int move = 0;
			int score = -100000;
			
			int i=0;
			List<Move> allMoves= getAIMoves(col, board);
			Collections.shuffle(allMoves);
			
			for(Move p: allMoves )
			{						
				Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
				score = Getscore(tempBoard,p.end);
				moveChessmanAI(p.end, p.start, tempBoard);				
				move = min(depth, col, tempBoard) ;
				score+=move;
				moveChessmanAI(p.start, p.end, tempBoard);
						
				if(score >= bestscore) {
					bestID = i;
					bestscore = score;
				}
				
				i++;
			}
						
			if(depth-1 == 0)
				return allMoves.get(bestID);

		depth --;	
		}						
		return null;		
	}
	
	
	
	
	
	public int max(int depth,SideColor col, Chessman[][] board)
	{
		col.swapColor();
		ArrayList<Move> allMoves = getAIMoves(col, board);
		if(depth == 0 || allMoves.size() == 0){
			return 0;
		}
			
		int move =0;
		int score = 0;
		int bestscore = -100000;
		
		for(Move p : allMoves) {
			Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
			score = Getscore(tempBoard,p.end);
			moveChessmanAI(p.end, p.start, tempBoard);
			move = min(depth -1, col, tempBoard);
			score += move;
			
			moveChessmanAI(p.start, p.end, tempBoard);
			
			if(score > bestscore)
				bestscore = score;			
		}		
		return bestscore;
	}
	
	
	public int min(int depth,SideColor col, Chessman[][] board)
	{	
		col.swapColor();
		ArrayList<Move> allMoves = getAIMoves(col, board);
		if(depth == 0 || allMoves.size() == 0){
			return 0;
		}
		
		int move = 0;
		int score = 0;
		int bestscore = 100000;
		
		for(Move p : allMoves) {
			Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
			score = - Getscore(tempBoard,p.end);
			moveChessmanAI(p.end, p.start, tempBoard);
			move = max(depth -1, col, tempBoard);
			score += move;
			
			
			moveChessmanAI(p.start, p.end, tempBoard);
			
			if(score < bestscore)
				bestscore = score;						
		}		
		return bestscore;
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
	
	
		
	public void moveChessmanAI(Position newPosition ,Position oldPosition, Chessman board[][] ) {

		Chessman piece = board[oldPosition.x][oldPosition.y];
		
		if(piece instanceof King && piece.notMoved) {				
			castling(newPosition ,piece, board);
		}
		else if(piece instanceof Pawn) {
			((Pawn) piece).startPosition=false;
				if(newPosition.y==7 || newPosition.y==0) {
					Position tempP= piece.pos;
					SideColor tempC= piece.color;
					piece = new Queen(tempC , tempP.x , tempP.y);
					}
			}
		
		board[newPosition.x][newPosition.y]=piece;
		board[piece.pos.x][piece.pos.y] =null;
		piece.pos=newPosition;
		piece.notMoved = false;
		
	}
	
	
	public static ArrayList<Move> getAIMoves(SideColor col ,Chessman board[][]){
		ArrayList<Move> moves =new ArrayList<Move>();
	
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(board[i][j] != null)
				{				
					if(board[i][j].color == col)
					{
						ArrayList<Position> movess =new ArrayList<Position>();
						movess.addAll(preventCheck(board[i][j].GetMoves(board), board, board[i][j]));
						for(Position p : movess) {
							moves.add(new Move(board[i][j].pos, p));
							
						}
					}					
				}			
			}
		}
		return moves;
	}
	
	
	
	
	
}
