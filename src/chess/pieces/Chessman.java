package chess.pieces;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import chess.Position;
import chess.SideColor;



public abstract class Chessman {

	private BufferedImage sprite;
	
	public BufferedImage img;
	public SideColor color;
	public boolean notMoved;
	public Position pos;
	
	protected void loadImage() {
		
		
		URL trace =getClass().getResource("resources/Chess_Pieces_Sprite64.png");
		try {
		sprite  = ImageIO.read(trace);
		}
		catch(IOException e)
		{
			System.err.println("Error");
			e.printStackTrace();
		}
		int spriteNumX=0;
		int spriteNumY=0;
		
		if(this instanceof Pawn) spriteNumX=5;
		if(this instanceof Rook) spriteNumX=4;
		if(this instanceof Knight) spriteNumX=3;
		if(this instanceof Bishop) spriteNumX=2;
		if(this instanceof Queen) spriteNumX=1;
		if(this instanceof King) spriteNumX=0;
		
		
		int imgSize =64;
		if(color==SideColor.BLACK) spriteNumY+=imgSize;
		
		img = sprite.getSubimage(spriteNumX*imgSize, spriteNumY,
				imgSize, imgSize);
		

	}
	public abstract ArrayList<Position> GetMoves(Chessman[][] board);
}
