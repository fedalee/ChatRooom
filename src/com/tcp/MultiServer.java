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
			
			System.out.println("��"+count+"���ͻ��˽�������");
			
			String msg="����˷��͵�����";
			
			
			/*
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream())
					);
			bw.write(msg);
			bw.newLine();
			bw.flush();
			 */
			
			//�������������˵����Ҫ���ⷢ�����ݣ������������
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
			dos.close();
		}
		
	}
}
