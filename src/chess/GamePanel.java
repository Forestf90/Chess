package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	protected BufferedImage BoardImg;
	static int SquareSize =64;
	public GamePanel() {
		BoardImg = new BufferedImage(8*SquareSize ,8*SquareSize ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		//this.setPrefferedSize(8*SquareSize, 8*SquareSize);
		this.setPreferredSize(new Dimension(8*SquareSize, 8*SquareSize));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(BoardImg , 0 ,0 ,8*SquareSize,8*SquareSize , null);
		
	}
	
	protected void drawBoard()
	{

		Graphics2D g = (Graphics2D) BoardImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.decode("#71292a"));
		g.fillRect(0,0,8*SquareSize, 8*SquareSize);
		
		g.setColor(Color.decode("#f1e4c1"));
		for(int i=0 ; i<8 ;i++) {
			for(int j=i%2; j<8 ; j+=2) {
				g.fillRect(i*SquareSize, j*SquareSize, SquareSize, SquareSize);
			}
		}
		


	}
}
