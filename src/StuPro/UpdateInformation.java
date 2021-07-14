package StuPro;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hdm.shared.StopWatch;

public class UpdateInformation extends Thread {

	public void run() {
		StopWatch stopwatch = new StopWatch();
		
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (true) {
			if (stopwatch.getElapsedTime() / 1_000_000_000 % 30 == 0) {

				try {
					InfoPanel.cyclists();
					InfoPanel.foodsharing();
					Rankings.recentRanking();					
					Rankings.weeklyRanking();
					App.sortByPoints();
					App.updaterUpdated = true;
					Log.log("Info updated!", "progress");
					//App.updaterUpdated = true;
				} catch (IOException | InterruptedException e) {
					Log.log("Info not updated", "error");
				}

			}
		}

	}

}
