package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import chess.pieces.Chessman;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.panels.GamePanel;

public class AI {

    static int beta = 1000000;
    static int alpha = -1000000;
    static int currentDepth;
    static int maxDepth = 2;
	
	public static Move minMax(Chessman[][] board, SideColor col, int alpha, int beta) {

		for(currentDepth = 0; currentDepth < maxDepth; currentDepth++) {
							
			int bestscore = alpha;
			int bestID = 0;
			int move = 0;
			int score = 0;
			
			int i=0;
			List<Move> allMoves= getAIMoves(col, board);
			Collections.shuffle(allMoves);
			Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
			for(Move p: allMoves )
			{						
				
				score = getScore(tempBoard,p.end);
				moveChessmanAI(p.end, p.start, tempBoard);				
				move = min(currentDepth + 1, col, tempBoard, alpha, beta) ;
				score+=move;
				moveChessmanAI(p.start, p.end, tempBoard);
						
				if(score > bestscore) {
					bestID = i;
					bestscore = score;
				}
				
				i++;
			}
						
			if(bestscore == beta) {
				Move t= allMoves.get(bestID);
				allMoves=null;
				return t;
			}
							
			if(currentDepth+1 == maxDepth) {
				Move t= allMoves.get(bestID);
				allMoves=null;
				return t;
			}
				
		}						
		return null;		
	}
		
	public static int max(int depth,SideColor col, Chessman[][] board,  int alpha, int beta)
	{
		col.swapColor();
		ArrayList<Move> allMoves = getAIMoves(col, board);
		if(depth == maxDepth || allMoves.size() == 0){
			allMoves=null;
			return 0;
		}
			
		int move =0;
		int score = 0;
		int bestscore = alpha;
		
		Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
		
		for(Move p : allMoves) {
			
			score = getScore(tempBoard,p.end);
			moveChessmanAI(p.end, p.start, tempBoard);
			move = min(depth +1, col, tempBoard, alpha, beta);
			score += move;
			
			moveChessmanAI(p.start, p.end, tempBoard);
			
			if(score > bestscore)
				bestscore = score;			
			if(bestscore > beta)
				return bestscore;			
			if(bestscore > alpha)
				alpha = bestscore;			
		}	
		allMoves=null;
		return bestscore;
	}
	
	
	public static int min(int depth,SideColor col, Chessman[][] board, int alpha, int beta )
	{	
		col.swapColor();
		ArrayList<Move> allMoves = getAIMoves(col, board);
		if(depth == maxDepth || allMoves.size() == 0){
			allMoves=null;
			return 0;
		}
		
		int move = 0;
		int score = 0;
		int bestscore = beta;
		Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
		for(Move p : allMoves) {
			
			score =- getScore(tempBoard,p.end);
			moveChessmanAI(p.end, p.start, tempBoard);
			move = max(depth +1, col, tempBoard,alpha, beta);
			score += move;
						
			moveChessmanAI(p.start, p.end, tempBoard);
			if(score < bestscore)
				bestscore = score;			
			if(bestscore < alpha)
				return bestscore;			
			if(bestscore < beta)
				beta = bestscore;			
		}		
		allMoves=null;
		return bestscore;
	}
		
	public static int getScore(Chessman[][] board, Position newPosition){
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
	
	
		
	public static void moveChessmanAI(Position newPosition ,Position oldPosition, Chessman board[][] ) {

		Chessman piece = board[oldPosition.x][oldPosition.y];
		
		if(piece instanceof King && piece.notMoved) {				
			GamePanel.castling(newPosition ,piece, board);
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
						movess.addAll(GamePanel.preventCheck(board[i][j].GetMoves(board), board, board[i][j]));
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

