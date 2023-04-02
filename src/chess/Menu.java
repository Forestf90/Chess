package chess;


import chess.panels.GamePanelAI;
import chess.panels.GamePanelHot;
import chess.panels.GamePanelLAN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame {

    public Menu() {
        super("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font f = new Font(Font.DIALOG, Font.PLAIN, 24);
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(15, 40, 15, 40);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        this.add(menuPanel);

        JLabel name = new JLabel("Choose playing mode:");
        JButton hot = new JButton("Hot-Seat");
        JButton lan = new JButton("Multiplayer");
        JButton ai = new JButton("AI");

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
                } catch (Exception ignored) {

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

        menuPanel.add(name, c);
        menuPanel.add(hot, c);
        menuPanel.add(lan, c);
        menuPanel.add(ai, c);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
