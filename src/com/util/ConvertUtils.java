package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConvertUtils {

	public static byte[] convert2Bytes(double num) throws IOException {
		byte[] data = null;

		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		DataOutputStream dout = new DataOutputStream(bout);

		dout.writeDouble(num);
		dout.flush();

		// 获取数据
		data = bout.toByteArray();

		dout.close();
		return data;
	}

	public static double convert2Double(byte[] data) throws IOException {

		DataInputStream dis = new DataInputStream(
				new ByteArrayInputStream(data));
		double num = dis.readDouble();
		dis.close();
		return num;
	}
}
