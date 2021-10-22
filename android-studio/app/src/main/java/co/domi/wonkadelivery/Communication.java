package co.domi.wonkadelivery;


import android.widget.Toast;

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
    private MainActivity mainA;

    public void run() {
        try {
            socket = new DatagramSocket(8001);

            while(true) {

                byte[] buffer = new byte[M_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData()).trim();
                mainA.launchIt(message, mainA);
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

                        InetAddress ip = InetAddress.getByName("10.0.2.2");
                        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, 8000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
    }

    public void setMainA(MainActivity mainA) {
        this.mainA = mainA;
    }
}
