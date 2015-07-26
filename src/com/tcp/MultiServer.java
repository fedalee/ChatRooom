package com.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private static int count = 0;
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(8888);
		
		while(true){
			Socket client = server.accept();
			
			count++;
			
			System.out.println("第"+count+"个客户端建立连接");
			
			String msg="服务端发送的数据";
			
			
			/*
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream())
					);
			bw.write(msg);
			bw.newLine();
			bw.flush();
			 */
			
			//对于这个程序来说，它要向外发送数据，所以用输出流
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
			dos.close();
		}
		
	}
}
