package chess;

import java.io.Serializable;

public class Position implements Serializable {

	public int x;
	public int y;
	
	public Position(int ix , int iy) {
		x=ix;
		y=iy;
	}
}
