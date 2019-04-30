package chess.panels;

import java.util.ArrayList;

import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chess.Menu;
import chess.Position;
import chess.SideColor;
import chess.pieces.Chessman;

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
		AI(SideColor.BLACK);
		enabled= true;
		}
	
	void AIWhiteTurn() {
		
		enabled= false;
		AI(SideColor.WHITE);
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
	
}
