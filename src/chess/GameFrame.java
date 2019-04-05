package chess;

import javax.swing.JFrame;

import chess.panels.GamePanel;


public class GameFrame extends JFrame {


	public GameFrame(GamePanel gamePanel){
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(gamePanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
