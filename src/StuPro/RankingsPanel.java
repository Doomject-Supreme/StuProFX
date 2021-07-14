package StuPro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RankingsPanel extends JPanel {
	static int widthOfScreen;
	static int heightOfScreen;

	public RankingsPanel() throws IOException, InterruptedException {
		screenSize();

		// this.setSize(widthOfScreen / 3, heightOfScreen);
		this.setBackground(Color.decode("#393C3A"));

		 weeklyRankingLabels();
		// recentRankingsLabels();
	}

	public void recentRankingsLabels() throws FileNotFoundException, InterruptedException {
		Rankings.recentRanking();

		GridLayout recentGrid = new GridLayout(Rankings.recent.size(), 1);
		this.setLayout(recentGrid);

		for (String s : Rankings.recent) {
			NicknamesLabel label = new NicknamesLabel(s.toString());
			label.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(label);
		}
	}

	public void weeklyRankingLabels() throws FileNotFoundException, InterruptedException {

		GridLayout weeklyGrid = new GridLayout(5, 2);
		this.setLayout(weeklyGrid);

		Rankings.weeklyRanking();

		for (int i = 0; i < Rankings.weekly.size(); i++) {
			if (i < 5) {
				NicknamesLabel labelNickname = new NicknamesLabel(Rankings.weekly.get(i).nickname);
				PointsLabel labelPoints = new PointsLabel(Integer.toString(Rankings.weekly.get(i).points));
				this.add(labelNickname);
				this.add(labelPoints);
			} else {
				break;
			}
		}
	}

	public static void screenSize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen
																		// in pixels
		widthOfScreen = (int) size.getWidth();
		heightOfScreen = (int) size.getHeight();
	}
}
