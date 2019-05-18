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
		moveChessman(newposition,piecesBoard[oldposition.x][oldposition.y]);							
	}
	
	
	public void newAI(SideColor col)
	{
		boolean maximalizingPlayer = true;
		int depth;
		Position bestpos;
		
		Move move = max(0,col,piecesBoard,-10000,10000);
		if(move!= null) {
		moveChessman(move.end, piecesBoard[move.start.x][move.start.y] );
		}
		else
			AI(col);
		
//		Move newMove = minMax(3, piecesBoard, col);
//		if(newMove!= null) {
//			max(1,col,piecesBoard,-10000,10000);
//			//moveChessman(newMove.end,piecesBoard[newMove.start.x][newMove.start.y]);
//		}
//		else {
//			AI(col);
//		}
	}
	
	// Zwarcana bedzie najlepsza lista pozycji Do moveChessman czyli pierw stara pozycja a pozniej nowa i po tej liscie iterator
	
	public Move minMax(int depth, Chessman[][] board, SideColor col) {

		Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
		int score = 0;
		int bestscore = 0;
		Position newbestmove = new Position(0,0);
		Position lastBestPosition = new Position(0,0);
	
		ArrayList<Position> pieceMoves = new ArrayList<Position>();		
		Collections.shuffle(pieceMoves);
		
		Move move = null;
		
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
		
									
		return move;		
	}
	
	
	
	
	
	public Move max(int depth,SideColor col, Chessman[][] board, int alpha, int beta)
	{
		Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);

		ArrayList<Move> Allmoves = getAIMoves(col, tempBoard);
		if(depth == 0 || Allmoves.size() == 0){
			return minMax(0,tempBoard,col);
		}
		
		Move move = null;
		Move bestmove = null;
		int score = 0;
		
		for(Move p : Allmoves) {
			
			move = min(depth -1, col.swapColor(), tempBoard, alpha, beta );
			score = Getscore(tempBoard,move.end);
			
			if(score >= beta)
			{
				break; 
			}
			if(score >= alpha)
			{
				alpha = score;
				bestmove = move; 
			}
		}
		
		return bestmove;
	}
	
	
	public Move min(int depth,SideColor col, Chessman[][] board, int alpha, int beta )
	{	
		Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
		ArrayList<Move> Allmoves = getAIMoves(col, tempBoard);
		
		if(depth == 0 || Allmoves.size() == 0){
			return null;
		}
		
		Move move = null;
		Move bestmove = null;
		int score = 0;
		
		for(Move p : Allmoves) {
			move = max(depth -1, col.swapColor(), tempBoard, alpha, beta );
			score = Getscore(tempBoard,move.end);
			
			if(score <= alpha) // fail hard beta-cutoff
			{
				break;  
			}
			if(score < beta) //alpha acts like max in MiniMax
			{
				alpha = score;
				bestmove = move; 
			}
		}
		
		return bestmove;
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
						movess.addAll(board[i][j].GetMoves(board));
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
