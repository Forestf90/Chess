package chess;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.panels.GamePanel;
import chess.panels.GamePanelHot;


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
        c.insets = new Insets(15, 40, 15, 40);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
		this.add(menuPanel);
		
		name = new JLabel("Choose playing mode:");
		hot = new JButton("Hot-Seat");
		lan = new JButton("LAN");
		ai = new JButton("AI");
		
		
		hot.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        new GameFrame(new GamePanelHot());
		        setVisible(false);
		        dispose();
		    }
	  });
		
		menuPanel.add(name ,c);
		menuPanel.add(hot ,c);
		menuPanel.add(lan ,c);
		menuPanel.add(ai, c);
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);


	}
}
