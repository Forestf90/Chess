package chess.panels;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import chess.Move;
import chess.Position;
import chess.SideColor;



import chess.AI;

public class GamePanelAI extends GamePanel{

	
	public SideColor playerAI;
	
	public GamePanelAI() {
		chooseColor();
	}
	private void chooseColor() {
		Object[] options = { "White", "Black" };
		int response =JOptionPane.showOptionDialog(null,"Chose color you want to play","Chose your color",
				JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE,null,options, options[0]);
				
		if (response == 0) {
			playerAI = SideColor.BLACK;  
			whiteMove^=false;
	    } 
		else if (response == 1) {
			playerAI = SideColor.WHITE; 
	        whiteMove^=true;
	        oponentTurn();
	    } 


	}
	@Override
	void oponentTurn() {
		if(endGame) return;
		enabled= false;
		newAI(playerAI);
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
		
		Move move = AI.minMax(piecesBoard, col, -100000, +100000);
		if(move!= null) {
		moveChessman(move.end, move.start );
		}
		else
			AI(col);

	}
		
	
	
	
	
	
	
}
