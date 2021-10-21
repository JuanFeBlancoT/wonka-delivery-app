package view;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
		
	}
	
	private Communication com;
	
	public void settings() {
		size(500,500);
	}
		
	public void setup() {
		com = new Communication();
		com.start();
	}
	
	public void draw() {
		background(40,80,220);
	}
}

