package javaFXProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import StuPro.App;
import StuPro.Rankings;
import hdm.shared.Toolbox;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class TryingOut {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		App.screenSize();
		InterfaceThread threadOne = new InterfaceThread();
		threadOne.start();

		TimeUnit.SECONDS.sleep(20);
		
		System.out.println("end");
		
		Interface.groupFoodsharing.setVisible(false);
		
		Rankings.weeklyRanking();	
		
		Toolbox.readString("ENTER");
		

	}
}


