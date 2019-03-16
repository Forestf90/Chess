package chess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import chess.SideColor;

public abstract class Chessman {

	private BufferedImage sprite;
	
	public BufferedImage img;
	SideColor color;
	public int posX;
	public int posY;
	
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
		int spriteNum=0;
		
		if(this instanceof Pawn) spriteNum=0;
		
		if(color==SideColor.BLACK) spriteNum+=4;
		
		img = sprite.getSubimage(spriteNum*GamePanel.SQUARE_SIZE, 0,
				GamePanel.SQUARE_SIZE, GamePanel.SQUARE_SIZE);
	}
}
