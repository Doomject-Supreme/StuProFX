package StuPro;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import hdm.shared.MyBasicFrame;
import hdm.shared.StopWatch;
import hdm.shared.Toolbox;
import javaFXProject.Interface;
import javaFXProject.InterfaceThread;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;


public class App implements SerialPortEventListener {

	// Arduino related
	SerialPort serialPort;
	static ArduinoCommunication arduinoRFID;

	// Students database related
	static int id;
	static String nickname;
	static String clan;
	static int points;
	static Student[] school;
	static int counter = 0;
	static boolean makeNewStudent = true;
	static String[] substantive;
	static String[] adjective;

	// Ranking related
	static JPanel panel;
	static JPanel subPanel;
	static StandingsPanel standingsPanel;
	static InfoPanel infoPanel;
	static JPanel nicknamePanel;
	static JLabel nicknameLabel;
	static MyBasicFrame frame;
	public static int widthOfScreen;
	public static int heightOfScreen;
	static String dataCO2;

	// Videoplayback related
	static VideoPlayer videoPlayer;
	static final String VIDEO_PATH = "assets/Videos/";
	static boolean videoPlaying = false;
	static int videoCounter = 0;
	static boolean standByMode = true;
	static boolean outOfTime = false;

	// Points counting related
	static boolean binOpened = false;
	static boolean readyToAcceptPoints = false;
	static int timeoutTime = 20;

	// Movement related
	static boolean activateMoveSensor = false;
	static boolean activateHallSensor = false;

	// Camera related
	static RPiCamera piCamera;
	static BufferedImage image;
	static JLabel iconLabel;
	static boolean labelAdded = false;
	static int left;
	static int mid;
	static int right;
	static boolean photoTaken = true;
	static boolean readyToTakePicture = false;
	static boolean cameraOnBoard = true;
	
	static boolean updaterUpdated = false;

	public static void main(String[] args) throws IOException, InterruptedException, FailedToRunRaspistillException {
		school = new Student[10000]; // Array with all Students

		substantive = new String[100]; // Array for nicknames
		adjective = new String[100]; // Array for nicknames

		arduinoRFID = new ArduinoCommunication(); // Communicates with ARDUINO

		try {
			piCamera = new RPiCamera("/home/pi/Pictures"); // Communicates with PiCam
		} catch (Exception e) {
			Log.log("Camera not found.", "error");
			cameraOnBoard = false;
		}
		
		input(); // Reads saved students into the school array

		createPanel(); // Creates the window with standings

		UpdateInformation updater = new UpdateInformation();
		updater.start();

		while (!updaterUpdated) {
			TimeUnit.SECONDS.sleep(1);
		}
		
		loadVideo(); // Loads the window with video

		loadNicknames(); // Loads arrays of available nicknames

		arduinoRFID.initialize(); // opens the communication with the ARDUINO

		interactiveTrashcan(); // Repeating code
	}

	private static void interactiveTrashcan() throws IOException, InterruptedException {

		standByMode();

		do {
			Log.log("Scan your card!", "prompt");
			do { // waits until a Card-ID can be read
				standByMode();
				// InfoPanel.updateData();
				TimeUnit.SECONDS.sleep(1);
			} while (ArduinoCommunication.unhashedID.contentEquals("default"));

			standByMode = false;
			activateMoveSensor = false;
			activateHallSensor = false;
			readyToAcceptPoints = true;

			if (videoPlaying != true) {
				videoPlayer.stopVideo();
				videoPlayer.setInvisible();
				VideoPlayer.isFullscreen = false;
			}

			if (counter == 0) { // only for the first run
				newStudent(id, nickname, clan, points);
			} else { // for every other Student
				makeNewStudent = true;

				int hackedID = id(); // saves the ID of the card

				setNicknameLabel(hackedID);

				existingStudent(hackedID); // checks if the student already exists

				if (makeNewStudent == true) {
					newStudent(hackedID, nickname, clan, points); // in case a new ID was presented
				}
			}
			onlySort(); // Sorts the school array

			// setLabels(); // Updates the UI

			output(); // Saves the array in out.txt as text
			
			//Rankings.recentRanking();
			//Rankings.weeklyRanking();

			ArduinoCommunication.unhashedID = "default"; // Resets inputLine so the first do{}while() resets
			TimeUnit.SECONDS.sleep(1); // Waits because otherwise Bugs happen
			Log.log("Ready for the next one!", "progress"); // prints line to slow the program down

		} while (counter < school.length); // runs until the array gets 10.000 Students.

		arduinoRFID.close(); // closes communication with the ARDUINO
	}
	
	public static void onlySort() {
		Arrays.sort(school, new StudentPointsComparator());

	}

	public static void sortByPoints() throws InterruptedException { // Sorts the students array
		Arrays.sort(school, new StudentPointsComparator());
		
		if (school[0]!=null) {
			if (Integer.toString(school[0].points).length() == 3) {
				Interface.weeklyUser6points.setText(school[0].points + " ");
			} else if(Integer.toString(school[0].points).length() == 2) {
				Interface.weeklyUser6points.setText("  " + school[0].points + " ");
			} else if(Integer.toString(school[0].points).length() == 1) {
				Interface.weeklyUser6points.setText("    " + school[0].points + " ");
			}
			
			TimeUnit.SECONDS.sleep(2);
			Interface.weeklyUser6.setText(school[0].nickname + "\n");
			TimeUnit.SECONDS.sleep(2);
		} else {
			Interface.weeklyUser6points.setText("     ");
			Interface.weeklyUser6.setText("      \n");
		}
	
		if (school[1]!=null) {
			if (Integer.toString(school[1].points).length() == 3) {
				Interface.weeklyUser7points.setText(school[1].points + " ");
			} else if(Integer.toString(school[1].points).length() == 2) {
				Interface.weeklyUser7points.setText("  " + school[1].points + " ");
			} else if(Integer.toString(school[1].points).length() == 1) {
				Interface.weeklyUser7points.setText("    " + school[1].points + " ");
			}
			
			TimeUnit.SECONDS.sleep(2);
			Interface.weeklyUser7.setText(school[1].nickname + "\n");
			TimeUnit.SECONDS.sleep(2);
		}else {
			Interface.weeklyUser7points.setText("     ");
			Interface.weeklyUser7.setText("      \n");
		}
	
		if (school[2]!=null) {
			if (Integer.toString(school[2].points).length() == 3) {
				Interface.weeklyUser8points.setText(school[2].points + " ");
			} else if(Integer.toString(school[2].points).length() == 2) {
				Interface.weeklyUser8points.setText("  " + school[2].points + " ");
			} else if(Integer.toString(school[2].points).length() == 1) {
				Interface.weeklyUser8points.setText("    " + school[2].points + " ");
			}
			
			TimeUnit.SECONDS.sleep(2);
			Interface.weeklyUser8.setText(school[2].nickname + "\n");
			TimeUnit.SECONDS.sleep(2);
		}else {
			Interface.weeklyUser8points.setText("     ");
			Interface.weeklyUser8.setText("      \n");
		}
		
		if (school[3]!=null) {
			if (Integer.toString(school[3].points).length() == 3) {
				Interface.weeklyUser9points.setText(school[3].points + " ");
			} else if(Integer.toString(school[3].points).length() == 2) {
				Interface.weeklyUser9points.setText("  " + school[3].points + " ");
			} else if(Integer.toString(school[3].points).length() == 1) {
				Interface.weeklyUser9points.setText("    " + school[3].points + " ");
			}
			
			TimeUnit.SECONDS.sleep(2);
			Interface.weeklyUser9.setText(school[3].nickname + "\n");
			TimeUnit.SECONDS.sleep(2);
		}else {
			Interface.weeklyUser9points.setText("     ");
			Interface.weeklyUser9.setText("      \n");
		}
		
		if (school[4]!=null) {
			if (Integer.toString(school[4].points).length() == 3) {
				Interface.weeklyUser10points.setText(school[4].points + " ");
			} else if(Integer.toString(school[4].points).length() == 2) {
				Interface.weeklyUser10points.setText("  " + school[4].points + " ");
			} else if(Integer.toString(school[4].points).length() == 1) {
				Interface.weeklyUser10points.setText("    " + school[4].points + " ");
			}
			
			TimeUnit.SECONDS.sleep(2);
			Interface.weeklyUser10.setText(school[4].nickname + "\n");
			TimeUnit.SECONDS.sleep(2);
		}else {
			Interface.weeklyUser10points.setText("     ");
			Interface.weeklyUser10.setText("      \n");
		}
		
	}

	public static void existingStudent(int hackedID) throws InterruptedException, IOException { // checks if the ID is
																								// already in the
		// database
				
		for (int d = 0; d < school.length; d++) {
			if (school[d] != null) {
				if (hackedID == school[d].id) {
										
					if (cameraOnBoard == true) {
						takePicture();
					}
					StopWatch timeout = new StopWatch();

					do {
						Log.log(Long.toString(timeout.getElapsedTime() / 1_000_000_000), "timeout");
						TimeUnit.SECONDS.sleep(1);
						if ((timeout.getElapsedTime() / 1_000_000_000) >= timeoutTime) {
							System.out.println("Trash not thrown out!");
							outOfTime = true;
							break;
						}
					} while (binOpened == false);

					if (outOfTime == false) {
						school[d].points++;

						Log.usersLog(school[d].nickname);

						if (videoPlaying == false) { // if video was started --> runs till the end
							videoPlaying = true;
							startPlaybackOnDemand(); // starts the actually chosen video
						}
					}

					if (outOfTime == true) {
						standByMode = true;
					}

					makeNewStudent = false; // doesn't create a new student
					binOpened = false;
					readyToAcceptPoints = false;
					outOfTime = false;
					ArduinoCommunication.tempID = "default";
					ArduinoCommunication.binOpen = "default";
					ArduinoCommunication.binTemp = "default";
				}
			}
		}
	}

	public static void newStudent(int id, String nickname, String clan, int points)
			throws InterruptedException, IOException { // creates a new student
		
		Interface.groupNewUser.setVisible(true);
		Interface.groupKnownUser.setVisible(false);
		Interface.groupCyclists.setVisible(false);
		Interface.groupFoodsharing.setVisible(false);
		Interface.groupTrashPhoto.setVisible(false);

		id = id();

		nickname = nickname(); // this has been automated

		// newStudentUI(nickname);

		clan = clan();

		if (cameraOnBoard == true) {
			takePicture();
		}

		StopWatch timeout = new StopWatch();
		do {
			Log.log(Long.toString(timeout.getElapsedTime() / 1_000_000_000), "timeout");
			TimeUnit.SECONDS.sleep(1);
			if ((timeout.getElapsedTime() / 1_000_000_000) >= timeoutTime) {
				outOfTime = true;
				System.out.println("Trash not thrown out");
				break;
			}
		} while (binOpened == false);

		points = 1;

		if (outOfTime == false) {

			Log.usersLog(nickname);

			if (videoPlaying == false) { // if video was started --> runs till the end
				videoPlaying = true;
				startPlaybackOnDemand(); // starts the actually chosen video

			}
			school[counter] = new Student(id, nickname, clan, points); // puts new student into the array and keeps
																		// track of the amount
			counter++;
		}

		if (outOfTime == true) {
			standByMode = true;
		}

		binOpened = false;
		readyToAcceptPoints = false;
		outOfTime = false;
		ArduinoCommunication.tempID = "default";
		ArduinoCommunication.binOpen = "default";
		ArduinoCommunication.binTemp = "default";

	}

	public static String clan() { // this is going to be replaced because its shit!!!!!!!!!!!!
		String clanName = "Trashboi";

		/*
		 * if (chooseClan.contentEquals("P") || chooseClan.contentEquals("p")) {
		 * clanName = "Paper_Hands"; } else if (chooseClan.contentEquals("F") ||
		 * chooseClan.contentEquals("f")) { clanName = "Fossil_Bitch"; } else if
		 * (chooseClan.contentEquals("R") || chooseClan.contentEquals("r")) { clanName =
		 * "Unrecyclable"; } else { clanName =
		 * clan(Toolbox.readString("Your Choice: ")); }
		 */

		return clanName;
	}

	public static int id() { // gets ID from the Serial Monitor to

		return ArduinoCommunication.unhashedID.hashCode();
	}

	public static String nickname() throws FileNotFoundException { // generates random nickname from the assets

		String finalNickname = adjective[Toolbox.randomInt(1, 100)] + "_" + substantive[Toolbox.randomInt(1, 100)];

		for (int o = 0; o <= school.length - 1; o++) {

			if (school[o] != null) {
				if (school[o].nickname.contentEquals(finalNickname)) {
					Log.log("Nickname besetzt!", "error");
					finalNickname = nickname();
				} else {
					finalNickname = finalNickname.replaceAll("\\s", "_");
				}
			}
		}
		Interface.nickname.setText(finalNickname);
		return finalNickname;
	}

	public static void loadNicknames() throws FileNotFoundException {
		Reader readerSubstantive = new FileReader("assets/Substantive.txt");
		@SuppressWarnings("resource")
		Scanner scannerSubstantive = new Scanner(readerSubstantive);

		for (int i = 0; i < 100; i++) {
			String substantiveWord = scannerSubstantive.nextLine();
			substantive[i] = capitalize(substantiveWord);
		}

		Reader readerAjectives = new FileReader("assets/Adjektive.txt");
		@SuppressWarnings("resource")
		Scanner scannerAdjectives = new Scanner(readerAjectives);

		for (int i = 0; i < 100; i++) {
			String adjectiveWord = scannerAdjectives.nextLine();
			adjective[i] = capitalize(adjectiveWord);
		}
	}

	public static String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public static void input() throws IOException, InterruptedException { // reads an inputs the text file into the array at the beginning of
													// the program
		Reader reader = new FileReader("assets/out.txt");
		Scanner scanner = new Scanner(reader);
		String id = "";
		String nickname = "";
		String clan = "";
		String points = "";

		if (scanner.hasNext()) {

			do {
				String student = scanner.nextLine();

				String patternID = "(id=\\S*)";
				Pattern patternOfID = Pattern.compile(patternID);
				Matcher matcheOfID = patternOfID.matcher(student);

				while (matcheOfID.find()) {
					id = matcheOfID.group(1).substring(3);
					// System.out.println(id);
				}

				String patternNickname = "(nickname=\\S*)";
				Pattern patternOfNickname = Pattern.compile(patternNickname);
				Matcher matcherOfNickname = patternOfNickname.matcher(student);

				while (matcherOfNickname.find()) {
					nickname = matcherOfNickname.group(1).substring(9);
					// System.out.println(nickname);
				}

				String patternClan = "(clan=\\w*)";
				Pattern patternOfClan = Pattern.compile(patternClan);
				Matcher matcherOfClan = patternOfClan.matcher(student);

				while (matcherOfClan.find()) {
					clan = matcherOfClan.group(1).substring(5);
					// System.out.println(clan);
				}

				String patternPoints = "(points=\\d*)";
				Pattern patternOfPoints = Pattern.compile(patternPoints);
				Matcher matcherOfPoints = patternOfPoints.matcher(student);

				while (matcherOfPoints.find()) {
					points = matcherOfPoints.group(1).substring(7);
					// System.out.println(points);
				}

				school[counter] = new Student(Integer.parseInt(id), nickname, clan, Integer.parseInt(points));
				counter++;

			} while (scanner.hasNext());

			//sortByPoints();
		}

		try {
			left = getFileCount(new File("/home/pi/Pictures/left"));
			mid = getFileCount(new File("/home/pi/Pictures/mid"));
			right = getFileCount(new File("/home/pi/Pictures/right"));
		} catch (Exception e) {
			Log.log("You are not on RasPi.", "error");
		}
		scanner.close();

		Log.log(counter + " Students read.", "counter");
		Log.log(left + " pictures in left.", "counter");
		Log.log(mid + " pictures in mid.", "counter");
		Log.log(right + " pictures in right.", "counter");
	}

	public static int getFileCount(File parent) {
		int fileCount = 0;
		for (File file : parent.listFiles()) {
			if (file.isFile()) {
				fileCount++;
			}
			if (file.isDirectory()) {
				fileCount += getFileCount(file);
			}
		}
		return fileCount;
	}

	public static void output() throws IOException { // outputs the sorted array into text
		Writer out = new FileWriter(new File("assets/out.txt"));
		for (int i = 0; i < counter; i++) {
			out.write(school[i].toString() + "\n");
		}
		out.close();
	}

	public static void createPanel() throws InterruptedException, IOException { // used to create the entire ranking
																				// screen
		screenSize();
		
		InterfaceThread thread = new InterfaceThread();
		thread.start();
	}

	public static void screenSize() {

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen
		// in pixels
		widthOfScreen = (int) size.getWidth();
		heightOfScreen = (int) size.getHeight();
	}

	public static void setNicknameLabel(int hackedID) { // shows our user his nickname

		for (int o = 0; o <= school.length; o++) {
			if (school[o] != null) {
				if (school[o].id == hackedID) {
					//System.out.println(o);
					Log.log("ID found at position " + o + ", printing nickname to Label!", "progress");
					Interface.textDarkBold.setText("Welcome back trainer " + school[o].nickname + "\nKeep it up!");
					Interface.textDarkBold.setX((App.widthOfScreen - Interface.textDarkBold.getBoundsInLocal().getWidth()) / 2);
					Interface.score.setText(Integer.toString(school[o].points));
					Interface.groupKnownUser.setVisible(true);
					Interface.groupCyclists.setVisible(false);
					Interface.groupFoodsharing.setVisible(false);
					Interface.groupNewUser.setVisible(false);
					Interface.groupTrashPhoto.setVisible(false);
					break;
				}
			}
		}
	}

	public static void loadVideo() { // preloads the video file
		videoPlayer = new VideoPlayer("Video Player");
		videoPlayer.initialize();
	}

	public static void startPlayback() throws InterruptedException { // starts the playback of video while in StandBy
																		// mode
		//videoPlayer.loadVideo(VIDEO_PATH);
		videoPlayer.playVideo();
	}

	public static void startPlaybackOnDemand() throws InterruptedException { // starts the playback on user interaction
		videoPlayer.loadVideo(VIDEO_PATH);
		videoPlayer.fullscreen();
		videoPlayer.playOnDemand();
	}

	public static void standByMode() throws InterruptedException { // makes sure the switching in StandBy mode works
		
		if (standByMode == true) {
			activateMoveSensor = true;
			activateHallSensor = true;

			if (videoCounter % 2 != 0) {
				videoPlayer.setVisible(true);
			}
			standByMode = false;
			videoCounter++;
			videoPlayer.playVideo();
		}
	}

	public static void updateCO2Data() {
		String ppm = "";

		String html = Toolbox.downloadIntoString("https://www.esrl.noaa.gov/gmd/ccgg/trends/gl_trend.html");

		Pattern regex = Pattern.compile("( <\\/td><td>)( )(.{10})(<\\/td><\\/tr>)");
		Matcher matcher = regex.matcher(html);
		if (matcher.find()) {
			ppm = matcher.group(3);
		}
		dataCO2 = ppm;
	}

	public static void takePicture() throws InterruptedException {
		// System.out.println("Bitte taste Drücken!");

		readyToTakePicture = true;
		Interface.rectanglePhoto.setFill(Color.TRANSPARENT);


		StopWatch timeouting = new StopWatch();

		while (ArduinoCommunication.shootPic.contentEquals("default")) {
			Log.log(Long.toString(timeouting.getElapsedTime() / 1_000_000_000), "timeout");
			SECONDS.sleep(1);
			if ((timeouting.getElapsedTime() / 1_000_000_000) >= timeoutTime) {
				photoTaken = false;
				System.out.println("Photo not taken!");
				break;
			}
		}

		if (photoTaken == true) {
			
			Interface.groupKnownUser.setVisible(false);
			Interface.groupCyclists.setVisible(false);
			Interface.groupFoodsharing.setVisible(false);
			Interface.groupNewUser.setVisible(false);
			Interface.groupTrashPhoto.setVisible(true);

			Log.log(ArduinoCommunication.shootPic, "progress");

			piCamera.setWidth(2000).setHeight(2000);
			piCamera.setBrightness(50);
			piCamera.turnOffPreview();
			piCamera.setTimeout(1);
			piCamera.setExposure(Exposure.SPORTS);
			piCamera.setAddRawBayer(true);

			try {
				image = piCamera.takeBufferedStill();
			} catch (IOException e) {
				Log.log("Camera not initiated", "error");
			}


			BufferedImage resized = null;
			try {
				resized = resizeImage(image, 500, 500);
			} catch (IOException e) {
				Log.log("Image couldn't be resized, var: image=null", "error");
			}
			
			Image bullshit = SwingFXUtils.toFXImage(resized,null);

			Interface.rectanglePhoto.setFill(new ImagePattern(bullshit));

		}

		photoTaken = true;
		readyToTakePicture = false;
		ArduinoCommunication.shootTemp = "default";
		ArduinoCommunication.shootPic = "default";

	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
			throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// I have no fucking idea but it has to be here...
	}

}