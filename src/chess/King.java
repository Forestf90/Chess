package chess;

public class King extends Chessman {

	public King(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
