package StuPro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StandingsPanel extends JPanel implements ActionListener {

	Student[] school;
	NicknamesLabel labelFirstNickname;
	PointsLabel labelFirstPoints;
	
	NicknamesLabel labelSecondNickname;
	PointsLabel labelSecondPoints;
	
	NicknamesLabel labelThirdNickname;
	PointsLabel labelThirdPoints;
	
	NicknamesLabel labelFourthNickname;
	PointsLabel labelFourthPoints;
	
	NicknamesLabel labelFifthNickname;
	PointsLabel labelFifthPoints;
	

	public StandingsPanel(Student[] school) {
		this.school = school;

		GridLayout grid = new GridLayout(5, 2);
		this.setLayout(grid);

		
		/*
		try {
			image = ImageIO.read(new File("assets/background.jpg"));

		} catch (IOException ex) {
		}
*/
		this.setBackground(Color.decode("#393C3A"));
		
		if (this.school[0] != null) {
			labelFirstNickname = new NicknamesLabel(this.school[0].nickname);
			labelFirstPoints = new PointsLabel(Integer.toString(this.school[0].points));
		} else {
			labelFirstNickname = new NicknamesLabel("");
			labelFirstPoints = new PointsLabel("");
		}
		this.add(labelFirstNickname);
		this.add(labelFirstPoints);

		if (this.school[1] != null) {
			labelSecondNickname = new NicknamesLabel(this.school[1].nickname);
			labelSecondPoints = new PointsLabel(Integer.toString(this.school[1].points));
		} else {
			labelSecondNickname = new NicknamesLabel("");
			labelSecondPoints = new PointsLabel("");
		}
		this.add(labelSecondNickname);
		this.add(labelSecondPoints);

		if (this.school[2] != null) {
			labelThirdNickname = new NicknamesLabel(this.school[2].nickname);
			labelThirdPoints = new PointsLabel(Integer.toString(this.school[2].points));
		} else {
			labelThirdNickname = new NicknamesLabel("");
			labelThirdPoints = new PointsLabel("");
		}
		this.add(labelThirdNickname);
		this.add(labelThirdPoints);

		if (this.school[3] != null) {
			labelFourthNickname = new NicknamesLabel(this.school[3].nickname);
			labelFourthPoints = new PointsLabel(Integer.toString(this.school[3].points));
		} else {
			labelFourthNickname = new NicknamesLabel("");
			labelFourthPoints = new PointsLabel("");
		}
		this.add(labelFourthNickname);
		this.add(labelFourthPoints);

		if (this.school[4] != null) {
			labelFifthNickname = new NicknamesLabel(this.school[4].nickname);
			labelFifthPoints = new PointsLabel(Integer.toString(this.school[4].points));
		} else {
			labelFifthNickname = new NicknamesLabel("");
			labelFifthPoints = new PointsLabel("");
		}
		this.add(labelFifthNickname);
		this.add(labelFifthPoints);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image scaledImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, this); // see javadoc for more info on the parameters
	}
*/
}
