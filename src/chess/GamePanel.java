package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	protected BufferedImage BoardImg;
	static int SquareSize =32;
	public GamePanel() {
		drawBoard();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
	}
	
	protected void drawBoard()
	{

		Graphics2D g = (Graphics2D) BoardImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.decode("71292a"));
		g.fillRect(8*SquareSize, 8*SquareSize, SquareSize, SquareSize);
		
		g.setColor(Color.decode("f1e4c1"));
		for(int i=0 ; i<8 ;i++) {
			for(int j=i%2; j<8 ; j+=2) {
				g.fillRect(i*SquareSize, j*SquareSize, SquareSize, SquareSize);
			}
		}
		


	}
}
