package chess;

public class Queen extends Chessman {

	public Queen(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
