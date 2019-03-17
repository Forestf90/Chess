package chess;

public class Bishop extends Chessman {

	public Bishop(SideColor col , int x , int y) {
		color =col;
		posX = x;
		posY = y;
		
		loadImage();
	}
}
