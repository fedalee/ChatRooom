package com.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.util.CloseUtil;

public class Receive implements Runnable{

	private DataInputStream dis;

	private boolean isRunning = true;
	public Receive() {
	}
	public Receive(Socket client){
		this();
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isRunning = false;
			CloseUtil.closeAll(dis);
		}
	}
	
	public String receive(){
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isRunning = false;
			CloseUtil.closeAll(dis);
		}
		return msg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning){
			System.out.println(receive());
		}
	}

}
