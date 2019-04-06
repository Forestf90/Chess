package chess.panels;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import chess.Position;
import chess.SideColor;
import chess.pieces.Chessman;

public class GamePanelAI extends GamePanel{

	
	public GamePanelAI() {
		chooseColor();
	}
	private void chooseColor() {
		Object[] options = { "White", "Black" };
		int response =JOptionPane.showOptionDialog(null,"Chose color you want to play","Chose your color",
				JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE,null,options, options[0]);
		
		
	    if (response == JOptionPane.NO_OPTION) {
	    	whiteMove =false;
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
		
	}
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	void AI(SideColor col) {
		if(whiteMove == false)
		{
						
			ArrayList<Position> newPosition = getAllMoves(SideColor.BLACK);
			int random = new Random().nextInt(newPosition.size());
			moveChessman(newPosition.get(random));
		}
		
	}
	

}
