package chess;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import chess.panels.GamePanel;


public class GameFrame extends JFrame implements ComponentListener {

	private int height;
	private int width;
	private GamePanel panel;
	public GameFrame(GamePanel gamePanel){
		super("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = gamePanel;
		this.add(panel);
		addComponentListener(this);
//		this.getContentPane().setPreferredSize(new Dimension(gamePanel.SQUARE_SIZE *8,gamePanel.SQUARE_SIZE*8 ));
//		this.getContentPane().setBounds(0, 0, gamePanel.SQUARE_SIZE *8, gamePanel.SQUARE_SIZE *8);
		this.setJMenuBar(new JMenuBar());
		//this.getJMenuBar().setBounds(0, 0, getWidth(), 25);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		height= 64*8;
		width = 64*8;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		if(getHeight()!=e.getComponent().getHeight()) {

	        panel.SQUARE_SIZE = e.getComponent().getHeight()/8;
	        panel.setPreferredSize(new Dimension(panel.SQUARE_SIZE, panel.SQUARE_SIZE));
	        this.repaint();
			super.pack();
		}
		else if(getWidth()!=e.getComponent().getWidth()){

			panel.SQUARE_SIZE = e.getComponent().getWidth()/8;
			panel.setPreferredSize(new Dimension(panel.SQUARE_SIZE, panel.SQUARE_SIZE));
	        this.repaint();
			super.pack();
		}
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
