package chess;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.panels.GamePanelAI;
import chess.panels.GamePanelHot;
import chess.panels.GamePanelLAN;



public class Menu extends JFrame {

	JPanel menuPanel ;
	JButton hot;
	JButton lan;
	JButton ai;
	JLabel name;
	public Menu() {
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font f =new Font(Font.DIALOG, Font.PLAIN, 24);
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
		lan = new JButton("Multiplayer");
		ai = new JButton("AI");
		
		name.setFont(f);
		hot.setFont(f);
		lan.setFont(f);
		ai.setFont(f);
		
		menuPanel.setBackground(Color.decode("#bb4446"));
		hot.setBackground(Color.decode("#f1e4c1"));
		hot.setOpaque(true);
		lan.setBackground(Color.decode("#f1e4c1"));
		lan.setOpaque(true);
		ai.setBackground(Color.decode("#f1e4c1"));
		ai.setOpaque(true);
		
		hot.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        new GameFrame(new GamePanelHot());
		        setVisible(false);
		        dispose();
		    }
	  });
		
		lan.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
			        new GameFrame(new GamePanelLAN());
			        setVisible(false);
			        dispose();
		    	}catch(Exception ee){
		    		
		    	}

		    }
	  });
		
		ai.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        new GameFrame(new GamePanelAI());
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
