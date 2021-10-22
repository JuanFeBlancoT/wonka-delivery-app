package view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Communication extends Thread{

	public final int M_SIZE = 20;
	
	private DatagramSocket socket;
	private String[] connectedIp;
	private MainServer serverPeer;
	
	public void run() {
		try {
			socket = new DatagramSocket(8000);
			
			while(true) {
				
				byte[] buffer = new byte[M_SIZE];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				
				String message = new String(packet.getData()).trim();
				String senderInfo = packet.getSocketAddress().toString();
				serverPeer.onMessage(message, senderInfo);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message, String ipS, String port) {
		new Thread(
			()->{
				
				try {

					InetAddress ip = InetAddress.getByName(ipS);
					DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, Integer.parseInt(port));
					socket.send(packet);
				} catch (UnknownHostException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}).start();
	}

	public void setServerPeer(MainServer serverPeer) {
		this.serverPeer = serverPeer;
	}

	
}
