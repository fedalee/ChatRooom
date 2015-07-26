package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.util.ConvertUtils;

public class MyServer {

	public static void main(String[] args) throws IOException {

		DatagramSocket server = new DatagramSocket(8888);
		
		byte[] container = new byte[1024];
		
		DatagramPacket packet = new DatagramPacket(container, container.length);
		
		server.receive(packet);
		
		
		//·ÖÎöÊý¾Ý
		byte[] data = packet.getData();
		double num = ConvertUtils.convert2Double(data);
//		int len = packet.getLength();
//		System.out.println(new String(data , 0 , len));
		System.out.println(num);
		server.close();
	}

}
