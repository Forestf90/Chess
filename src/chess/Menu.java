package chess;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JFrame {

	JPanel menuPanel ;
	JButton hot;
	JButton lan;
	JButton ai;
	JLabel name;
	public Menu() {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
		this.add(menuPanel);
		
		name = new JLabel("Choose playing mode:");
		hot = new JButton("Hot-Seat");
		lan = new JButton("LAN");
		ai = new JButton("AI");

		
		menuPanel.add(name ,c);
		menuPanel.add(hot ,c);
		menuPanel.add(lan ,c);
		menuPanel.add(ai, c);
		
		
		this.pack();
		this.setVisible(true);


	}
}
