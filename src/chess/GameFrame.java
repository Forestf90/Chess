package chess;


import chess.panels.GamePanel;

import javax.swing.*;


public class GameFrame extends JFrame {

    public GameFrame(GamePanel gamePanel) {
        super("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(gamePanel);


        this.setJMenuBar(new JMenuBar());
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
