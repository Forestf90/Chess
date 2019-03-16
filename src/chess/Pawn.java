package chess;

public class Pawn extends Chessman {

	public Pawn(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
