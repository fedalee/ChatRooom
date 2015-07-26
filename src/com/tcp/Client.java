package com.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		System.out.println("«Î ‰»Î√˚≥∆£∫");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userName = br.readLine();
		if("".equals(userName)){
			return ;
		}
		Socket client = new Socket("localhost" , 8888);
		new Thread(new Send(client , userName)).start();
		new Thread(new Receive(client)).start();	
	}
}
