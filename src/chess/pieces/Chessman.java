package chess.pieces;



import java.util.ArrayList;

import chess.Position;
import chess.SideColor;



public abstract class Chessman {

	public SideColor color;
	public boolean notMoved;
	public Position pos;
	
	public short imgSrc;
	
	abstract void loadImage() ;	

	
	public abstract ArrayList<Position> GetMoves(Chessman[][] board);
}
