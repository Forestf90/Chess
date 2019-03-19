package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	protected BufferedImage boardImg;
	static final int SQUARE_SIZE =64;
	ArrayList<Position> possibleMoves;
	public Chessman[][] piecesBoard;
	
	
	public GamePanel() {
		boardImg = new BufferedImage(8*SQUARE_SIZE ,8*SQUARE_SIZE ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		this.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
		
		piecesBoard = new Chessman[8][8];
		possibleMoves=new ArrayList<Position>();
		generatePieces();
		generateRooks();
		generateKnights();
		generateBishops();
		generateQueen();
		generateKing();
		
		MouseListner();
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
		g.setColor(Color.RED);
		for(Position ch: possibleMoves) {
			g.fillRect(ch.x*SQUARE_SIZE, ch.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
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
		Rook tempBlack2 = new Rook(SideColor.BLACK, 7, 0);
			
		piecesBoard[0][7] = tempWhite1;
		piecesBoard[7][7] = tempWhite2;
		piecesBoard[0][0] = tempBlack1;
		piecesBoard[7][0] = tempBlack2;		
	}
	
	private void generateKnights() {
		
		Knight tempWhite1 = new Knight(SideColor.WHITE, 1, 7);
		Knight tempWhite2 = new Knight(SideColor.WHITE, 6, 7);
		Knight tempBlack1 = new Knight(SideColor.BLACK, 1, 0);
		Knight tempBlack2 = new Knight(SideColor.BLACK, 6, 0);
		
		piecesBoard[1][7] = tempWhite1;
		piecesBoard[6][7] = tempWhite2;
		piecesBoard[1][0] = tempBlack1;
		piecesBoard[6][0] = tempBlack2;		
	}
	
	private void generateBishops() {
			
		Bishop tempWhite1 = new Bishop(SideColor.WHITE, 2, 7);
		Bishop tempWhite2 = new Bishop(SideColor.WHITE, 5, 7);
		Bishop tempBlack1 = new Bishop(SideColor.BLACK, 2, 0);
		Bishop tempBlack2 = new Bishop(SideColor.BLACK, 5, 0);
			
		piecesBoard[2][7] = tempWhite1;
		piecesBoard[5][7] = tempWhite2;
		piecesBoard[2][0] = tempBlack1;
		piecesBoard[5][0] = tempBlack2;		
	}
	
	private void generateQueen() {
		
		Queen tempWhite = new Queen(SideColor.WHITE, 3, 7);
		Queen tempBlack = new Queen(SideColor.BLACK, 3, 0);
		
		piecesBoard[3][7] = tempWhite;
		piecesBoard[3][0] = tempBlack;
				
	}
	
private void generateKing() {
		
		King tempWhite = new King(SideColor.WHITE, 4, 7);
		King tempBlack = new King(SideColor.BLACK, 4, 0);
		
		piecesBoard[4][7] = tempWhite;
		piecesBoard[4][0] = tempBlack;
				
	}
public void MouseListner() {
	 addMouseListener(new MouseAdapter(){ 
         public void mousePressed(MouseEvent me) { 
           //blokowanie(me.getX() ,me.getY());
        	 int tempX =me.getX()/SQUARE_SIZE;
        	 int tempY =me.getY()/SQUARE_SIZE;
           possibleMoves =piecesBoard[tempX][tempY].GetMoves(piecesBoard);
           repaint();
         }
       }); 
	 
	 addMouseMotionListener(new MouseMotionListener() {
		 @Override
		public void mouseMoved(MouseEvent me) {
			// focus(me.getX() ,me.getY());	
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		 
		 
	 });
	
}
}
