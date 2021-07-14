package javaFXProject;

import java.io.IOException;

import StuPro.Log;

public class InterfaceThread extends Thread {
	
	public void run() {
		Log.log("Thread UI started", "progress");
		Interface.launch(Interface.class);
	}

}
