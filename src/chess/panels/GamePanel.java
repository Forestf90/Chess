package chess.panels;

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
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chess.Menu;
import chess.Position;
import chess.SideColor;
import chess.pieces.Bishop;
import chess.pieces.Chessman;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public abstract class GamePanel extends JPanel{
	
	protected BufferedImage boardImg;
	public int SQUARE_SIZE =64;
	ArrayList<Position> possibleMoves;
	ArrayList<Position> lastMove;
	Position checkPosition;
	Chessman selected;
	protected Position focus;
	public Chessman[][] piecesBoard;
	
	public boolean whiteMove;
	public boolean enabled;
	
	abstract void oponentTurn();
	
	public GamePanel() {
		boardImg = new BufferedImage(8*SQUARE_SIZE ,8*SQUARE_SIZE ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		this.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
		
		piecesBoard = new Chessman[8][8];
		possibleMoves=new ArrayList<Position>();
		lastMove = new ArrayList<Position>();
		focus = new Position(0,0);
		whiteMove= true;
		enabled =true;
		
		
		generatePieces();
		MouseListner();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(boardImg , 0 ,0 ,8*SQUARE_SIZE,8*SQUARE_SIZE , null);
		
		for(Position lm: lastMove) {
			g.setColor(new Color(0, 102, 102,128));
			g.fillRect(lm.x*SQUARE_SIZE, lm.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
		}
		if(checkPosition!=null) {
			g.setColor(new Color(255,0,0,128));
			g.fillRect(checkPosition.x*SQUARE_SIZE, checkPosition.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
		}

		
		g.setColor(new Color(128,128,128,196));
		g.fillRect(focus.x*SQUARE_SIZE, focus.y*SQUARE_SIZE,SQUARE_SIZE , SQUARE_SIZE);
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
			((Graphics2D) g).setStroke(new BasicStroke(4));
			g.drawRect(selected.pos.x*SQUARE_SIZE+2, selected.pos.y*SQUARE_SIZE+2, SQUARE_SIZE-4, SQUARE_SIZE-4 );
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
				g.fillOval(ch.x*SQUARE_SIZE+SQUARE_SIZE/4, ch.y*SQUARE_SIZE+SQUARE_SIZE/4,
						SQUARE_SIZE/2, SQUARE_SIZE/2);
				
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
	        	 if((piecesBoard[tempX][tempY]==null && selected==null) || !enabled) return;
	        	 else if(selected==null) {
	            	 selected =piecesBoard[tempX][tempY];
	            	 if(selected.color==SideColor.WHITE && !whiteMove) {
	            		 selected=null;
	            		 return;
	            	 }
	            	 else if(selected.color==SideColor.BLACK && whiteMove) {
	            		 selected=null;
	            		 return;
	            	 }
	                 possibleMoves =preventCheck(selected.GetMoves(piecesBoard), piecesBoard , selected);
	                 repaint();
	        	 }else if(selected!=null) {
	        		 if(piecesBoard[tempX][tempY]!=null && piecesBoard[tempX][tempY].color== selected.color) {
	                	 selected =piecesBoard[tempX][tempY];
	                     possibleMoves =preventCheck(selected.GetMoves(piecesBoard), piecesBoard , selected);
	                     repaint();
	        		 }else {
	        			 
	        			 checkChessmanMove(new Position(tempX ,tempY));
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
	        	 focus.x=me.getX()/SQUARE_SIZE;
	        	 focus.y=me.getY()/SQUARE_SIZE;
	        	 repaint();
			}
	
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		
	}

	public void checkChessmanMove(Position newPosition) {
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
			
			if(selected instanceof Pawn) {
				((Pawn) selected).startPosition=false;
				if(newPosition.y==7 || newPosition.y==0) {
					String[] buttons = { "Rook", "Knight", "Bishop", "Queen" };    
					int result = JOptionPane.showOptionDialog(null,  "Promote your Pawn to:","Promotion",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[3]);
					
	
					switch(result) {
					case 0:
						selected=new Rook(selected.color , selected.pos.x,selected.pos.y);
						break;
					case 1:
						selected=new Knight(selected.color , selected.pos.x,selected.pos.y);
						break;
					case 2:
						selected=new Bishop(selected.color , selected.pos.x,selected.pos.y);
						break;
					case 3:
						selected=new Queen(selected.color , selected.pos.x,selected.pos.y);
						break;
					}
				}
			}
			moveChessman(newPosition , selected);
			//selected.pos=null;
			oponentTurn();
			
		}else return;
	
	}


	public void moveChessman(Position newPosition ,Chessman piece) {
		lastMove.clear();
		lastMove.add(piece.pos);
		lastMove.add(newPosition);
		
		piecesBoard[newPosition.x][newPosition.y]=piece;
		piecesBoard[piece.pos.x][piece.pos.y] =null;
		piece.pos=newPosition;
		
		SideColor c =piece.color.swapColor();
	
		boolean isCheck =check(piecesBoard ,c);
		if(!isCheck) checkPosition=null;
		else {
			checkPosition = findKing(piecesBoard ,c);
		}
		
		checkStalemate(piece.color.swapColor() , piecesBoard);
		//else checkPosition.add(kingPosition);
	}


	public ArrayList<Position> getAllMoves(SideColor col ,Chessman board[][]){
		ArrayList<Position> moves =new ArrayList<Position>();
	
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(board[i][j] != null)
				{				
					if(board[i][j].color == col) moves.addAll(board[i][j].GetMoves(board));
				}			
			}
		}
		return moves;
	}
	
	public ArrayList<Position> getAllSafeMoves(SideColor col ,Chessman board[][]){
		ArrayList<Position> moves =new ArrayList<Position>();
	
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(board[i][j] != null)
				{				
					if(board[i][j].color == col) moves.addAll(preventCheck(board[i][j].GetMoves(board),board,board[i][j]));
				}			
			}
		}
		return moves;
	}
	public Position findKing(Chessman board[][] , SideColor col) {
		Position kingPosition = new Position(0,0);
		for(int i=0; i<board.length ; i++) {
			for(int j=0 ; j<board[i].length;j++) {
				if(board[i][j]!=null) {
					if(board[i][j] instanceof King && board[i][j].color==col){
						kingPosition =new Position(i,j);
					}
				}
			}
		}
		return kingPosition;
	}
	public boolean check(Chessman board[][],SideColor col) {

		Position kingPosition =findKing(board , col);
		SideColor c =col.swapColor();
		
		ArrayList<Position> enemyMoves =getAllMoves(c ,board);
		
		for(Position p : enemyMoves) {
			if(kingPosition.x==p.x &&kingPosition.y==p.y) {
				return true;
			}
		}
		
		return false;
	}

	public ArrayList<Position> preventCheck(ArrayList<Position> moves , Chessman board[][], Chessman piece){
		
		ArrayList<Position> newMoves = new ArrayList<Position>();
		for(Position p: moves) {
			Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);

			tempBoard[piece.pos.x][piece.pos.y]=null;
			tempBoard[p.x][p.y]=piece;
			boolean isCheck = check(tempBoard, piece.color);
			if(!isCheck)newMoves.add(p);

		}
		
		
		return newMoves;
	}

	public void checkMate() {
		
	}
	
	public void checkStalemate(SideColor col , Chessman[][] board) {
		ArrayList<Position> any = getAllSafeMoves(col , board);
		
		if(any.isEmpty()) {
			JOptionPane.showMessageDialog(null,col.getBetterString() +" have no more available moves. The game ends with a draw. " ,
					"Stalemate",JOptionPane.INFORMATION_MESSAGE);
			new Menu();
			SwingUtilities.windowForComponent(this).dispose();
		}
		
	}

}
