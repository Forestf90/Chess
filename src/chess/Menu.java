package chess;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu extends JFrame {

	JPanel menuPanel ;
	JButton hot;
	JButton lan;
	JButton ai;
	public Menu() {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel , BoxLayout.PAGE_AXIS));
		this.setSize(new Dimension(250, 80));
		
		this.add(menuPanel);
		
		hot = new JButton("Hot-Seat");
		lan = new JButton("LAN");
		ai = new JButton("AI");
		
		menuPanel.add(hot);
		menuPanel.add(lan);
		menuPanel.add(ai);
		
		
		
		this.setVisible(true);


	}
}
