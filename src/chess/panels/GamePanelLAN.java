package chess.panels;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import chess.GameFrame;
import chess.Menu;
import chess.Position;
import chess.pieces.Chessman;

public class GamePanelLAN extends GamePanel {

	Thread reciver;
	private Socket socket ;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerSocket serverSocket;
	
	
	
	public GamePanelLAN() {
		getSocketIP();
	}
	
	public void getSocketIP() {
		JPanel p = new JPanel();
		  JTextField IPInput = new JTextField(10);
		  JTextField PortInput = new JTextField(10);

		  p.add(new JLabel("IP :"));
		  p.add(IPInput);
		  p.add(new JLabel("Port : "));
		  p.add(PortInput);
		  int resp=JOptionPane.showConfirmDialog(null, p, "Connection", JOptionPane.DEFAULT_OPTION);
		  if(resp==0) {
			  try {
				  if(IPInput.getText().isEmpty() || PortInput.getText().isEmpty()) {
					  throw new UnknownHostException("Inputs cannot be empty");
				  }
					InetAddress ipAdd=InetAddress.getByName(IPInput.getText());
					int port = Integer.parseInt(PortInput.getText());
//					socket =new Socket(ipAdd , port);
					connect(ipAdd , port);
				
			} catch (Exception e) {
				int input = JOptionPane.showConfirmDialog(null, 
		                "Input data is not valid !", "Error", JOptionPane.DEFAULT_OPTION , JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
				getSocketIP();
			}
		  }
		  else {
			  //new Menu();
			  GameFrame gf =((GameFrame) this.getParent());
			  gf.setVisible(false);
		      gf.dispose();
		  }
	}
	
	
	public void connect(InetAddress ipAdd , int port) {
		try {
			socket =new Socket(ipAdd , port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			 JOptionPane.showConfirmDialog(null, 
	                "You play as Blacks", "Connected !", JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE);
			whiteMove =false;
			oponentTurn();
			createReciver();
			
		} catch (IOException e) {
			createServer(port);
			//e.printStackTrace();
		}
	}
	
	public void createReciver() {
		reciver = new Thread() {
			public synchronized void run() {
				while(true) {
					try {
						Position recPositionOld =  (Position) ois.readObject();
						Position recPositionNew = (Position) ois.readObject();
						moveChessman(recPositionNew , recPositionOld);
						repaint();
						enabled=true;
						
					} catch ( ClassNotFoundException | IOException e) {
						JOptionPane.showConfirmDialog(null, 
				                "Connection is lost. The game is over", "Disconection",
				                JOptionPane.DEFAULT_OPTION , JOptionPane.ERROR_MESSAGE);
						closeFrame();
						break;
//						JPanel gp =((JPanel) getParent());
//						JFrame gf = (JFrame) gp.getParent();
//						  gf.setVisible(false);
//					      gf.dispose();
						//e.printStackTrace();
					} 
					
				}
			}
		};
		reciver.start();
	}
	public void createServer( int port) {
		
		try {
			serverSocket = new ServerSocket(port);;
			Socket s =serverSocket.accept();
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			createReciver();
			JOptionPane.showConfirmDialog(null, 
	                "You play as Whites", "Connected !", JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendData(Position newPosition ,Position odlPosition) {
		
		try {
			//Class g = piece.getClass();
			oos.writeObject(odlPosition);
			oos.flush();
			oos.writeObject(newPosition);
			oos.flush();
			oponentTurn();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void checkChessmanMove(Position newPosition) {
		
		super.checkChessmanMove(newPosition);
		
		sendData(newPosition , lastMove.get(0));
		
		
	}
	public void closeFrame() {
		socket=null;
		serverSocket=null;
		//JPanel gp =((JPanel) getParent());
		
		JFrame gf = (JFrame) SwingUtilities.getWindowAncestor(this);
		  gf.setVisible(false);
	      gf.dispose();
	}
	@Override
	void oponentTurn() {
		// TODO Auto-generated method stub
		enabled=false;
	}

}
