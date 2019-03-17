package chess;

public class Rook extends Chessman {

	public Rook(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
