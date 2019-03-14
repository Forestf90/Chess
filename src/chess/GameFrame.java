package chess;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public GameFrame() {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.pack();
		this.setVisible(true);
	}
}
