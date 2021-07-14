package hdm.shared;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * All-purpose Swing frame that places a given Swing component into its center.
 */
@SuppressWarnings("serial")
public class MyBasicFrame extends JFrame {

	/**
	 * Creates and shows a new frame, with the given component at its center.
	 * 
	 * @param text Title of window.
	 * @param width Width of window (in pixel).
	 * @param height Height of window (in pixel).
	 * @param component Swing component (e.g. a JPanel) to be placed inside frame.
	 */
	public MyBasicFrame(String text, int width, int height, JComponent component) {
		super(text);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (component != null) {
			this.getContentPane().add(component);
		}
		this.setUndecorated(true);
		this.setVisible(true);
	}

	/**
	 * Creates and shows a new frame with default title and dimensions, with a given
	 * component at its center.
	 * 
	 * @param component Swing component (e.g. a JPanel) to be placed inside frame.
	 */
	public MyBasicFrame(JComponent component) {
		this("", 500, 300, component);
	}
}
