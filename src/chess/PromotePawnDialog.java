package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class PromotePawnDialog {

	
	public static int Show() {



		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setSize(new Dimension(200, 64));
		panel.setLayout(null);

		JLabel label1 = new JLabel("This file requires administrator rights.");
		label1.setVerticalAlignment(SwingConstants.BOTTOM);
		label1.setBounds(0, 0, 200, 32);
		label1.setFont(new Font("Arial", Font.BOLD, 10));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label1);

		JLabel label2 = new JLabel("Are you sure you want to continue?");
		label2.setVerticalAlignment(SwingConstants.TOP);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Arial", Font.BOLD, 10));
		label2.setBounds(0, 32, 200, 32);
		panel.add(label2);

		UIManager.put("OptionPane.minimumSize", new Dimension(300, 120));
		int input = JOptionPane.showConfirmDialog(null, panel, "Admin Rights Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

		// 0=yes, 1=no, 2=cancel
		//System.out.println(input);
		return input;

	}
}
