package chess.panels;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

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
import chess.SideColor;
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
		  PortInput.setText("1234");
		  IPInput.setText("127.0.0.1");

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
						if(socket==null && serverSocket ==null) return;
						Position recPositionOld =  (Position) ois.readObject();
						Position recPositionNew = (Position) ois.readObject();
						threadMoveChess(recPositionNew , recPositionOld);
						repaint();
						enabled=true;

					} catch ( ClassNotFoundException | IOException  e) {
						//e.printStackTrace();
						if(endGame) return;
						JOptionPane.showConfirmDialog(null, 
				                "Connection is lost. The game is over", "Disconection",
				                JOptionPane.DEFAULT_OPTION , JOptionPane.ERROR_MESSAGE);
						closeFrame();
						break;
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
			e.printStackTrace();
		}
	}
	public void sendData(Position newPosition ,Position oldPosition) {
		
		try {
			oos.writeObject(oldPosition);
			oos.flush();
			oos.writeObject(newPosition);
			oos.flush();
			oponentTurn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void threadMoveChess(Position newPosition ,Position currentPosition) {
		super.moveChessman(newPosition, currentPosition);
	}
	@Override
	public void moveChessman(Position newPosition ,Position currentPosition) {
		super.moveChessman(newPosition, currentPosition);
		sendData(newPosition, currentPosition);
	}
	
	@Override
	public void closeFrame() {
		resetSockets();
		super.closeFrame();
	}

	
	public void resetSockets() {
		try {
			if(oos!=null) {
				oos.close();
				ois.close();
				oos=null;
				ois=null;
			}
			if(socket!=null) {
				socket.close();
				socket=null;
			}
			if(serverSocket!=null) {
				serverSocket.close();
				serverSocket=null;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	@Override
	void oponentTurn() {
		enabled=false;
	}

}
