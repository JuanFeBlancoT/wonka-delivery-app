package model;

import java.time.LocalTime;

public class Order {

	private int numOrder;
	private int type;
	private LocalTime time;
	private String senderIp;
	private String portSender;
	
	//extra info for better visual manipulation
	private int posX, posY;
	
	public Order(int type, int numOrder, LocalTime time, String senderIp, String portSender, int posX, int posY) {
		this.numOrder = numOrder;
		this.type = type;
		this.time = time;
		this.senderIp = senderIp;
		this.portSender = portSender;
		this.posX = posX;
		this.posY = posY;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public String getSenderIp() {
		return senderIp;
	}

	public String getPortSender() {
		return portSender;
	}

	public int getType() {
		return type;
	}

	public LocalTime getTime() {
		return time;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	
	
	
}
