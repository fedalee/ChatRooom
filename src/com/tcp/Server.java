package com.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.util.CloseUtil;

public class Server {

	private List<MyChannel> all = new ArrayList<MyChannel>();
	
	public static void main(String[] args) throws IOException {
		
		new Server().start();
	}
	
	public void start() throws IOException{
		ServerSocket server = new ServerSocket(8888);
		
		while(true){
			Socket client = server.accept();
			
			MyChannel channel = new MyChannel(client);
			all.add(channel);
			new Thread(channel).start();
		}
	}
	
	
	private class MyChannel implements Runnable{

		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		private String userName;
		public MyChannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				
				this.userName = dis.readUTF();
				this.send("欢迎进入聊天室");
				sendOthers(this.userName + " 进入了聊天室");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRunning = false;
				CloseUtil.closeAll(dis,dos);
				all.remove(this);
			}
		}
		
		private String receive(){
			String msg ="";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRunning = false;
				CloseUtil.closeAll(dis);
				all.remove(this);
			}
			return msg;
		}
		
		private void send(String msg){
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRunning = false;
				CloseUtil.closeAll(dos);
				all.remove(this);
			}
		}
		
		private void sendOthers(String msg){
			
			if(msg.startsWith("@") && msg.indexOf(":") > -1){
				
				String userName = msg.substring(1, msg.indexOf(":"));
				String content = msg.substring(msg.indexOf(":") + 1);
				
				for(MyChannel temp : all){
					if(temp.userName.equals(userName)){
						temp.send(this.userName+"对你悄悄地说："+content);
					}
				}
			}else{
				for(MyChannel temp : all){
					if(temp == this){
						continue;
					}
					temp.send(this.userName +"对所有人说： "+msg);
					
				}
			}
		}
		@Override
		public void run() {
			
			while(isRunning){
				sendOthers(receive());
			}
		}
		
	}
}
