package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	protected BufferedImage boardImg;
	static final int SQUARE_SIZE =64;
	
	public Chessman[][] piecesBoard;
	
	
	public GamePanel() {
		boardImg = new BufferedImage(8*SQUARE_SIZE ,8*SQUARE_SIZE ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		this.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
		
		piecesBoard = new Chessman[8][8];
		generatePieces();
		generateRooks();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(boardImg , 0 ,0 ,8*SQUARE_SIZE,8*SQUARE_SIZE , null);
		
		for(int i=0 ; i<piecesBoard.length ;i++) {
			for(int j=0 ; j<piecesBoard[i].length;j++) {
				if(piecesBoard[i][j]==null) continue;
				else {
					g.drawImage(piecesBoard[i][j].img,  i*SQUARE_SIZE,
							j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE , null);
				}
			}
		}
	}
	
	private void drawBoard()
	{

		Graphics2D g = (Graphics2D) boardImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.decode("#71292a"));
		g.fillRect(0,0,8*SQUARE_SIZE, 8*SQUARE_SIZE);
		
		g.setColor(Color.decode("#f1e4c1"));
		for(int i=0 ; i<8 ;i++) {
			for(int j=i%2; j<8 ; j+=2) {
				g.fillRect(i*SQUARE_SIZE, j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}
		}

	}
	private void generatePieces() {
		
		for(int i=0 ; i<8 ; i++) {
			Pawn tempWhite = new Pawn(SideColor.WHITE ,i ,6);
			Pawn tempBlack = new Pawn(SideColor.BLACK ,i ,1);
			
			piecesBoard[i][6] =tempWhite;
			piecesBoard[i][1] =tempBlack;
		}
	}
	
	private void generateRooks() {
		
			Rook tempWhite1 = new Rook(SideColor.WHITE, 0, 7);
			Rook tempWhite2 = new Rook(SideColor.WHITE, 7, 7);
			Rook tempBlack1 = new Rook(SideColor.BLACK, 0, 0);
			Rook tempBlack2 = new Rook(SideColor.BLACK, 8, 0);
			
			piecesBoard[0][7] = tempWhite1;
			piecesBoard[7][7] = tempWhite2;
			piecesBoard[0][0] = tempBlack1;
			piecesBoard[7][0] = tempBlack2;		
	}
	
}
