package chess;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GamePanel GamePanelObj ;
	public GameFrame() {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanelObj = new GamePanel();
		this.add(GamePanelObj);
		
		this.pack();
		this.setVisible(true);
	}
}
