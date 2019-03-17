package chess;

public class Knight extends Chessman {

	public Knight(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
