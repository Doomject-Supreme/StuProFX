package StuPro;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NicknamesLabel extends JLabel {

	String text;
	
	public NicknamesLabel(String text) {
		this.setText(text);
//		this.setFont(new Font("Nintendo DS BIOS", Font.BOLD, 50));
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.setForeground(Color.decode("#AFEDD9"));

	}

}
