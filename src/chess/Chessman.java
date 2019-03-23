package chess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import chess.SideColor;

public abstract class Chessman {

	private BufferedImage sprite;
	
	public BufferedImage img;
	SideColor color;

	public Position pos;
	
	protected void loadImage() {
		
		
		File trace = new File("./resources/Chess_Pieces_Sprite.png");
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
		
		if(color==SideColor.BLACK) spriteNumY+=GamePanel.SQUARE_SIZE;
		
		img = sprite.getSubimage(spriteNumX*GamePanel.SQUARE_SIZE, spriteNumY,
				GamePanel.SQUARE_SIZE, GamePanel.SQUARE_SIZE);
	}
	
	public abstract ArrayList<Position> GetMoves(Chessman[][] board);
}
