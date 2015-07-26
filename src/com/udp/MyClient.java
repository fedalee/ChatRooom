package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.util.ConvertUtils;

public class MyClient {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket client = new DatagramSocket(6666);
		
//		String msg = "haha , woshi lihuafeng";
//		byte[] data = msg.getBytes();
		double msg = 89.37;
		byte[] data = ConvertUtils.convert2Bytes(msg);
		
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost" , 8888));
		
		client.send(packet);
		
		client.close();
	}

}
