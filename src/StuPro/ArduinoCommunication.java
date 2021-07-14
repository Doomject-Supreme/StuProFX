package StuPro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import hdm.shared.StopWatch;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class ArduinoCommunication implements SerialPortEventListener {

	SerialPort serialPort;
	private static final String PORT_NAMES[] = { "/dev/cu.usbmodem14101", "/dev/ttyACM0" };
	private BufferedReader input;
	@SuppressWarnings("unused")
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	public static String inputLine = "default";
	public static String tempLine = "";

	public static String unhashedID = "default";
	public static String tempID = "default";

	public static String binTemp = "default";
	public static String binOpen = "default";

	public static String shootTemp = "default";
	public static String shootPic = "default";

	public static PowerSaver powerSaver;
	public static boolean startPowerSavingTimeout = false;

	public void initialize() {
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			Log.log("Could not find COM port.", "error");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent port
	 * locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	@SuppressWarnings("deprecation")
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				tempLine = input.readLine();
				// System.out.println("tempLine: " + tempLine);
				Scanner scanner = new Scanner(tempLine);

				String patternID = "(RFID=\\S*)";
				Pattern patternOfID = Pattern.compile(patternID);

				Matcher matcheOfID = patternOfID.matcher(tempLine);

				String patternHall = "(opened\\S*)";
				Pattern patternOfHall = Pattern.compile(patternHall);

				Matcher matcherOfHall = patternOfHall.matcher(tempLine);

				String patternMove = "(MOVE)";
				Pattern patternOfMove = Pattern.compile(patternMove);

				Matcher matcherOfMove = patternOfMove.matcher(tempLine);

				String patternButton = "(BUTTON)";
				Pattern patternOfButton = Pattern.compile(patternButton);

				Matcher matcherOfButton = patternOfButton.matcher(ArduinoCommunication.tempLine);

				if (App.readyToTakePicture == true) {
					if (matcherOfButton.find()) {
						shootTemp = "takingPicture";
						App.readyToTakePicture = false;
						// System.out.println("MATCHED BUTTON");
					}
				}

				if (!shootTemp.contentEquals(shootPic)) {
					shootPic = shootTemp;
				}

				if (matcheOfID.find()) {
					tempID = matcheOfID.group(1).substring(5);
					// System.out.println("tempID: " + tempID);
				}

				if (App.readyToAcceptPoints == true) {
					if (matcherOfHall.find()) {
						// System.out.println(matcherOfHall.group(1));
						binTemp = "opened";

						if (matcherOfHall.group(1).contentEquals("openedLEFT")) {
							App.readyToAcceptPoints = false;
							File outputfile = new File("/home/pi/Pictures/left/LEFT" + App.left + ".jpg");
							ImageIO.write(App.image, "jpg", outputfile);
							App.left++;
							// System.out.println("LEFT");
						}

						if (matcherOfHall.group(1).contentEquals("openedMID")) {
							App.readyToAcceptPoints = false;
							File outputfile = new File("/home/pi/Pictures/mid/MID" + App.mid + ".jpg");
							ImageIO.write(App.image, "jpg", outputfile);
							App.mid++;
							// System.out.println("MID");
						}

						if (matcherOfHall.group(1).contentEquals("openedRIGHT")) {
							App.readyToAcceptPoints = false;
							File outputfile = new File("/home/pi/Pictures/right/RIGHT" + App.right + ".jpg");
							ImageIO.write(App.image, "jpg", outputfile);
							App.right++;
							// System.out.println("RIGHT");
						}
					}
				}

				if (App.activateMoveSensor == true) {
					if (!App.videoPlaying) {
						if (matcherOfMove.find()) {
							//TimeUnit.MILLISECONDS.sleep(500);
							App.activateMoveSensor = false;

							/*
							 * if (VideoPlayer.powerSaverRunning == true) {
							 * Log.log("TV StandBy mode timer reset", "progress");
							 * PowerSaver.stopWatch.reset(); }
							 * 
							 */ 
							  StopWatch timeout = new StopWatch(); 
							  do {
								  
							  } while (timeout.getElapsedTime() / 1_000_000_000 < 1);
							 
							Log.log(matcherOfMove.group(1), "progress");
							
							
							
							App.videoPlayer.playStopmotion();
						}
					}
				}

				if (App.activateHallSensor == true) {
					if (matcherOfHall.find()) {
						App.activateMoveSensor = false;
						TimeUnit.SECONDS.sleep(2);
						//App.videoPlayer.stopVideo();
						App.activateHallSensor = false;
						App.videoPlaying = true;
						Log.log("User disposed of trash without registering!", "error");
						App.startPlaybackOnDemand();
					}
				}

				if (!tempID.contentEquals(unhashedID)) {
					unhashedID = tempID;
					// System.out.println("unhashedID: " + unhashedID);
				}

				if (!binTemp.contentEquals(binOpen)) {
					binOpen = binTemp;
					App.binOpened = true;
					// System.out.println("Trashcan opened!");
				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

}