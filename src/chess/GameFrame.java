package chess;

import javax.swing.JFrame;

import chess.AI.AI;
import chess.LAN.Server;

public class GameFrame extends JFrame {

	GamePanel GamePanelObj ;
	public GameFrame(Server  server , AI Ai)throws IllegalArgumentException {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanelObj = new GamePanel();
		this.add(GamePanelObj);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
