package view;
import java.time.LocalTime;
import java.util.ArrayList;

import model.Order;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
		
	}
	
	public final int iMG_SIZE = 150;
	public final int MARGIN = 10;
	
	private int orderNum;
	private boolean deleting;
	
	PImage img1;	//gas
	PImage img2;	//hamburger
	PImage img3;	//juice
	PImage img4;	//fries	
	
	private Communication com;
	private ArrayList<Order> orders;
	
	public void settings() {
		size(400,700);
	}
		
	public void setup() {
		
		orderNum = 0;
		deleting = false;
		//load images
		img1 = loadImage("imgs/gaseosa.jpg");
		img2 = loadImage("imgs/ham1.jpg");
		img3 = loadImage("imgs/jugo-de-naranja.png");
		img4 = loadImage("imgs/papas.jpeg");
		
		orders = new ArrayList<>();
		com = new Communication();
		com.setServerPeer(this);
		com.start();
		
	}
	
	public void draw() {
		background(255);
		for (int i = 0; i < orders.size(); i++) {
			
			fill(60,30,60,20);
			rect(orders.get(i).getPosX(), orders.get(i).getPosY(), 300, iMG_SIZE);
			
			switch(orders.get(i).getType()) {
			case 1:
				image(img1, orders.get(i).getPosX(), orders.get(i).getPosY(), iMG_SIZE,iMG_SIZE);
				break;
			case 2:
				image(img2, orders.get(i).getPosX(), orders.get(i).getPosY(), iMG_SIZE,iMG_SIZE);
				break;
			case 3:
				image(img3, orders.get(i).getPosX(), orders.get(i).getPosY(), iMG_SIZE,iMG_SIZE);
				break;
			case 4:
				image(img4, orders.get(i).getPosX(), orders.get(i).getPosY(), iMG_SIZE,iMG_SIZE);
				break;
			}
			fill(50,30,50);
			text("Pedido#" + orders.get(i).getNumOrder(), 190+orders.get(i).getPosX(), orders.get(i).getPosY()+50);
			String timeFormat = orders.get(i).getTime().toString().substring(0, 8);
			text("Hora: " + timeFormat, 190+orders.get(i).getPosX(), orders.get(i).getPosY()+70);
			
		}
	}

	public void mousePressed() {
		for (int i = 0; i < orders.size() && !deleting; i++) {
			if(mouseX > MARGIN && mouseX < 300+MARGIN && mouseY > orders.get(i).getPosY() && mouseY < orders.get(i).getPosY()+iMG_SIZE) {
				if(!deleting) {
					com.sendMessage(orders.get(i).getNumOrder()+"", orders.get(i).getSenderIp(), orders.get(i).getPortSender());
					runAnimation(i);
				}
			}
		}
	}

	private void runAnimation(int i) {
		deleting = true;
		new Thread(
				()->{
					int moveFactor = 1;
					while(orders.get(i).getPosX() > -300) {
						try {
							orders.get(i).setPosX(orders.get(i).getPosX()-moveFactor);
							moveFactor*=2;
							Thread.sleep(60);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					moveFactor = 0;
					orders.remove(i);
					deleting = false;
				}).start();
		
	
	}

	public void mouseWheel(MouseEvent event) {
		//Scroll function
		  float e = event.getCount();
		  for (int i = 0; i < orders.size(); i++) {
			orders.get(i).setPosY((int) (orders.get(i).getPosY()+e*5));
		}
	}

	public void onMessage(String message, String senderInfo) {
		orderNum++;
		String[] infos = senderInfo.split(":");
		
		//get rid of the slash character
		String ip = infos[0].substring(1);
		Order orderX = new Order(Integer.parseInt(message), orderNum, LocalTime.now(), ip, infos[1], MARGIN, MARGIN+((orderNum-1)*iMG_SIZE));	
		orders.add(orderX);
	}
}

