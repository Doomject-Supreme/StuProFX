package StuPro;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import hdm.shared.StopWatch;

public class PowerSaver extends Thread {

	public static StopWatch stopWatch;
	private static boolean exit;
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void run() {
		stopWatch = new StopWatch();
		Log.log("Starting TV-StandBy mode timeout", "timeout");
		while(stopWatch.getElapsedTime() / 1_000_000_000 < 900) {
			
		}
		Log.log("Turning TV off", "timeout");
		VideoPlayer.powerSaverRunning = false;
	}

	public static String timestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf3.format(timestamp);
	}

	public void kill() {
		exit = true;
	}
}
