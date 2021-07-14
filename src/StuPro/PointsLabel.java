package StuPro;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PointsLabel extends JLabel {

	String text;
	
	public PointsLabel(String text) {
		this.setText(text);
//		this.setFont(new Font("Nintendo DS BIOS", Font.BOLD, 50));
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.setForeground(Color.decode("#AFEDD9"));

	}

}
