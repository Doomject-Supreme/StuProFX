package StuPro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import hdm.shared.Toolbox;
import javaFXProject.Interface;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

public class VideoPlayer extends JFrame {
	private static final long serialVersionUID = 1L;
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	static boolean isFullscreen = false;
	static boolean powerSaverRunning = false;
	static boolean invisibleVideo = false;
	static int rng = 1;
	static int lastVideo;

	@SuppressWarnings("serial")
	public VideoPlayer(String title) {
		super(title);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				super.playing(mediaPlayer);
				Log.log("Media Playback finished.", "progress");

				if (invisibleVideo == false) {

					if (rng == 1) {
						Interface.groupCyclists.setVisible(true);
						Interface.groupFoodsharing.setVisible(false);
						Interface.groupKnownUser.setVisible(false);
						Interface.groupFoodsharing.setVisible(false);
						Interface.groupNewUser.setVisible(false);
						Interface.groupTrashPhoto.setVisible(false);
						rng++;
					} else {
						
						int o = Toolbox.randomInt(0, App.counter);
						
						Interface.textDarkBold.setText("Welcome back trainer " + App.school[o].nickname + "\nKeep it up!");
						Interface.textDarkBold.setX((App.widthOfScreen - Interface.textDarkBold.getBoundsInLocal().getWidth()) / 2);
						Interface.score.setText(Integer.toString(App.school[o].points));
						
						Interface.groupCyclists.setVisible(false);
						Interface.groupFoodsharing.setVisible(false);
						Interface.groupKnownUser.setVisible(true);
						Interface.groupCyclists.setVisible(false);
						Interface.groupNewUser.setVisible(false);
						Interface.groupTrashPhoto.setVisible(false);
						rng = 1;
					}
				}

				invisibleVideo = false;
				App.videoPlaying = false;
				App.standByMode = true;
				App.activateMoveSensor = true;
			}
		};
		mediaPlayerComponent.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(this));
	}

	public void initialize() {
		this.setBounds(0, 0, App.widthOfScreen, App.heightOfScreen);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.release();
				System.exit(0);
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public void loadVideo(String path) {
		int randomGeneratedNumber = Toolbox.randomInt(1, 17);
		
		while (lastVideo == randomGeneratedNumber) {
			randomGeneratedNumber = Toolbox.randomInt(1, 17);
		}
		Log.log("Playing video number " + randomGeneratedNumber, "progress");
		mediaPlayerComponent.mediaPlayer().media().startPaused(path + randomGeneratedNumber + ".mp4");
	}

	public void playVideo() throws InterruptedException {
		if (App.videoCounter % 2 == 0) {
			mediaPlayerComponent.mediaPlayer().media().start("assets/Videos/silence.mp4");
			this.setVisible(false);
			invisibleVideo = true;
			isFullscreen = false;
			Log.log("You should now see ranking for 15sec", "progress");
		} else {
			this.setVisible(true);
			Log.log("A video is playing!", "progress");
			if (isFullscreen == false) {
				fullscreen();
				isFullscreen = true;
			}
			
			int randomGeneratedNumber = Toolbox.randomInt(1, 4);
			
			while (lastVideo == randomGeneratedNumber) {
				randomGeneratedNumber = Toolbox.randomInt(1, 4);
			}
			Log.log("Playing video number " + randomGeneratedNumber, "progress");
			mediaPlayerComponent.mediaPlayer().media().startPaused(App.VIDEO_PATH + "Werbevideos/" + randomGeneratedNumber + ".mov");
			mediaPlayerComponent.mediaPlayer().controls().play();
		}
	}

	public void playOnDemand() {
		this.setVisible(true);
		if (App.videoCounter % 2 == 0) {
			fullscreen();
			isFullscreen = true;
		}
		int randomGeneratedNumber = Toolbox.randomInt(1, 4);
		
		while (lastVideo == randomGeneratedNumber) {
			randomGeneratedNumber = Toolbox.randomInt(1, 4);
		}		
		mediaPlayerComponent.mediaPlayer().controls().play();
	}

	public void playStopmotion() {
		Log.log("A stopmotion is playing!", "progress");
		if (isFullscreen == false) {
			fullscreen();
			isFullscreen = true;
		}
		mediaPlayerComponent.mediaPlayer().media().start("assets/Videos/stopmotion.mp4");
		this.setVisible(true);

		
		/*
		if (powerSaverRunning == false) {
			PowerSaver powerSaver = new PowerSaver();
			powerSaver.start();
			powerSaverRunning = true;
		}
		*/
	}

	public void fullscreen() {
		mediaPlayerComponent.mediaPlayer().fullScreen().set(true);
	}

	public void setInvisible() {
		this.setVisible(false);
	}

	public void stopVideo() {
		mediaPlayerComponent.mediaPlayer().controls().stop();
	}
}
