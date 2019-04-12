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
	    	
            oponentTurn();
            
	    } 
	        else if (response == JOptionPane.YES_OPTION) {
	        	
	    } 
	        else if (response == JOptionPane.CLOSED_OPTION) {
	            //nie wiem czy to zablokowac czy nie bo default choose jest ustawione na white
	        	//wiec można uznać ze zamkniecie okna równa sie zaakceptowaniu defaultowej opcji
	    }
	}
	@Override
	void oponentTurn() {
		// TODO Auto-generated method stub
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
							
		ArrayList<Position> allMovesList = getAllMoves(SideColor.BLACK);
		
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
						if(piecesBoard[i][j].GetMoves(piecesBoard).size() > 0)
						{			
							random = new Random().nextInt(100);
							if(random > max)
							{								
								max = random;
								oldposition.x = i;
								oldposition.y = j;
								//to
								pieceMoves = piecesBoard[i][j].GetMoves(piecesBoard);
								random = new Random().nextInt(piecesBoard[i][j].GetMoves(piecesBoard).size());
								newposition = pieceMoves.get(random);									
							}	
						}
					}
				}			
			}
		}
		

		
		piecesBoard[oldposition.x][oldposition.y].pos = newposition;
		piecesBoard[newposition.x][newposition.y] = piecesBoard[oldposition.x][oldposition.y];
		piecesBoard[oldposition.x][oldposition.y] = null;
		
			
			
	}
	

}
