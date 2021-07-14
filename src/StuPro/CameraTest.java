package StuPro;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import hdm.shared.MyBasicFrame;
import hdm.shared.Toolbox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static StuPro.App.subPanel;
import static StuPro.App.updateCO2Data;
import static java.util.concurrent.TimeUnit.*;

public class CameraTest extends JPanel  implements ActionListener{
	static BufferedImage image;
	static ArduinoCommunication arduino;
	static RPiCamera piCamera;
	static int counter = 0;
	static ArduinoCommunication arduinoRFID;


	public static void main(String[] args) throws FailedToRunRaspistillException, IOException, InterruptedException {

		CameraTest panel = new CameraTest();
		new MyBasicFrame("Camera", 320, 150, panel);
		
		piCamera = new RPiCamera("/home/pi/Pictures/AIguyz");
	}
	
	public CameraTest() {
		JButton shutter = new JButton("Shutterino");
		shutter.setFont(new Font("Arial Black", Font.PLAIN, 20));
		this.add(shutter);
		
		shutter.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		piCamera.setWidth(2000).setHeight(2000);
		piCamera.setBrightness(50);
		piCamera.turnOffPreview();
		piCamera.setTimeout(2);
		piCamera.setExposure(Exposure.SPORTS);
		piCamera.setAddRawBayer(true);

		try {
			piCamera.takeStill("photo" + counter + ".jpg");
			counter++;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Hab Foto gemacht!");		
	}
}
