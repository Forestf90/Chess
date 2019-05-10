package chess.panels;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chess.GameFrame;
import chess.Menu;

public class GamePanelLAN extends GamePanel {

	private Socket socket ;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ServerSocket serverSocket;
	
	
	
	public GamePanelLAN() {
		getSocketIP();
	}
	
	public void getSocketIP() {
		JPanel p = new JPanel();
		//p.setLayout(new SpringLayout());
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
					InetAddress ipadd=InetAddress.getByName(IPInput.getText());
					int port = Integer.parseInt(PortInput.getText());
				
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
	@Override
	void oponentTurn() {
		// TODO Auto-generated method stub
		enabled=false;
	}

}
