package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Communication extends Thread{

	public final int M_SIZE = 200;
	
	private DatagramSocket socket;
	private String[] connectedIp;
	
	public void run() {
		try {
			socket = new DatagramSocket(8000);
			
			while(true) {
				
				byte[] buffer = new byte[M_SIZE];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				
				String message = new String(packet.getData()).trim();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		new Thread(
			()->{
				
				try {
					String[] ipData = getIpData();
					InetAddress ip = InetAddress.getByName(ipData[0]);
					DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, Integer.parseInt(ipData[1]));
					socket.send(packet);
				} catch (UnknownHostException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}).start();
	}

	private String[] getIpData() {
		// TODO find the ip of the orders client; [0] = ip, [1] = puerto
		return null;
	}
			
}
