package chess;


import javax.swing.JFrame;
import javax.swing.JMenuBar;

import chess.panels.GamePanel;


public class GameFrame extends JFrame  {


	private GamePanel panel;
	public GameFrame(GamePanel gamePanel){
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = gamePanel;
		this.add(panel);


		this.setJMenuBar(new JMenuBar());
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
