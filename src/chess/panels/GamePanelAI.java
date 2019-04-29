package chess.panels;

import java.util.ArrayList;

import java.util.Random;

import javax.swing.JOptionPane;

import chess.Position;
import chess.SideColor;
import chess.pieces.Chessman;

import javax.swing.JOptionPane;

public class GamePanelAI extends GamePanel{

	
	public GamePanelAI() {
		chooseColor();
	}
	private void chooseColor() {
		Object[] options = { "White", "Black" };
		int response =JOptionPane.showOptionDialog(null,"Chose color you want to play","Chose your color",
				JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE,null,options, options[0]);
		
		
	    if (response == JOptionPane.NO_OPTION) {
	    	//tu chyba blokowalem ruch druga strona - nie pamietam - tez napraw xD
            oponentTurn();
            
	    } 
	        else if (response == JOptionPane.YES_OPTION) {
	        	
	    } 
	        else if (response == JOptionPane.CLOSED_OPTION) {

	    }
	}
	@Override
	void oponentTurn() {
		// napraw zeby bot umial grac bialymi
		enabled= false;
		AI(SideColor.BLACK);
		enabled= true;
		whiteMove^=false;
		
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	void AI(SideColor col) {
		//jak nie uzywasz to to wywal					
		ArrayList<Position> allMovesList = getAllMoves(SideColor.BLACK,piecesBoard);
		
		ArrayList<Position> pieceMoves = new ArrayList<Position>();
		
		
		Position newposition = new Position(0,0);;
		Position oldposition = new Position(0,0);;
		int max = 0;
		int random = 0;
		//czym sie rusze
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(piecesBoard[i][j] != null){				
					if(piecesBoard[i][j].color == SideColor.BLACK){
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
