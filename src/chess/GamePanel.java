package chess;

import java.awt.BasicStroke;
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
	Chessman selected;
	public Chessman[][] piecesBoard;
	
	
	public GamePanel() {
		boardImg = new BufferedImage(8*SQUARE_SIZE ,8*SQUARE_SIZE ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		this.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
		
		piecesBoard = new Chessman[8][8];
		possibleMoves=new ArrayList<Position>();
		
		generatePieces();
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
		g.setColor(new Color(0,255,0,  192));
		if(selected!=null) {
			((Graphics2D) g).setStroke(new BasicStroke(5));
			g.drawRect(selected.pos.x*SQUARE_SIZE, selected.pos.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
		}
		for(Position ch: possibleMoves) {
			if(piecesBoard[ch.x][ch.y]!= null) {
				if(piecesBoard[ch.x][ch.y].color!=selected.color) {
					g.setColor(new Color(255,0,0,  192));
					g.drawRect(ch.x*SQUARE_SIZE, ch.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
				}
			}
			else {
				g.setColor(new Color(0,255,0,  192));
				g.fillOval(ch.x*SQUARE_SIZE, ch.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				
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
			piecesBoard[i][6] =new Pawn(SideColor.WHITE ,i ,6);;
			piecesBoard[i][1] =new Pawn(SideColor.BLACK ,i ,1);
		}
		
		piecesBoard[0][7] = new Rook(SideColor.WHITE, 0, 7);
		piecesBoard[7][7] = new Rook(SideColor.WHITE, 7, 7);
		piecesBoard[0][0] = new Rook(SideColor.BLACK, 0, 0);
		piecesBoard[7][0] = new Rook(SideColor.BLACK, 7, 0);
		
		piecesBoard[1][7] = new Knight(SideColor.WHITE, 1, 7);
		piecesBoard[6][7] = new Knight(SideColor.WHITE, 6, 7);
		piecesBoard[1][0] = new Knight(SideColor.BLACK, 1, 0);
		piecesBoard[6][0] = new Knight(SideColor.BLACK, 6, 0);
		
		piecesBoard[2][7] = new Bishop(SideColor.WHITE, 2, 7);
		piecesBoard[5][7] = new Bishop(SideColor.WHITE, 5, 7);
		piecesBoard[2][0] = new Bishop(SideColor.BLACK, 2, 0);
		piecesBoard[5][0] = new Bishop(SideColor.BLACK, 5, 0);	
		
		piecesBoard[3][7] = new Queen(SideColor.WHITE, 3, 7);
		piecesBoard[3][0] = new Queen(SideColor.BLACK, 3, 0);
		
		piecesBoard[4][7] = new King(SideColor.WHITE, 4, 7);
		piecesBoard[4][0] = new King(SideColor.BLACK, 4, 0);
		
	}


public void MouseListner() {
	 addMouseListener(new MouseAdapter(){ 
         public void mousePressed(MouseEvent me) { 
           //blokowanie(me.getX() ,me.getY());
        	 int tempX =me.getX()/SQUARE_SIZE;
        	 int tempY =me.getY()/SQUARE_SIZE;
        	 if(piecesBoard[tempX][tempY]==null && selected==null) return;
        	 else if(selected==null) {
            	 selected =piecesBoard[tempX][tempY];
                 possibleMoves =selected.GetMoves(piecesBoard);
                 repaint();
        	 }else if(selected!=null) {
        		 if(piecesBoard[tempX][tempY]!=null && piecesBoard[tempX][tempY].color== selected.color) {
                	 selected =piecesBoard[tempX][tempY];
                     possibleMoves =selected.GetMoves(piecesBoard);
                     repaint();
        		 }else {
        			 
        			 moveChessman(new Position(tempX ,tempY));
        			 selected=null;
        			 possibleMoves.clear();
        			 repaint();
        		 }
        		 
        	 }
           
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

public void moveChessman(Position newPosition) {
	if(piecesBoard[newPosition.x][newPosition.y]!=null) {
		//piecesBoard[newPosition.x][newPosition.y]=null;
		//TODO Add pieces to hit list
	}
	boolean contains= false;
	
	for(Position m : possibleMoves) {
		
		if(m.x==newPosition.x && m.y==newPosition.y) {
			contains=true;
			break;
		}
	}

	if(contains) {
		piecesBoard[newPosition.x][newPosition.y]=selected;
		piecesBoard[selected.pos.x][selected.pos.y] =null;
		selected.pos=newPosition;
		if(selected instanceof Pawn) {
			((Pawn) selected).startPosition=false;
			if(selected.pos.y==7 || selected.pos.y==0) {
				//TODO zamiana pionka na figure
			}
		}
	}else return;

}

}
