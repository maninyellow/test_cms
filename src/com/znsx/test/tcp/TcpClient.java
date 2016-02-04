package com.znsx.test.tcp;

import java.io.OutputStream;
import java.net.Socket;

/**
 * TcpClient
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2015-5-14 下午5:04:59
 */
public class TcpClient {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-5-14 下午5:04:59
	 */
	public static void main(String[] args) throws Exception {
		String host = "127.0.0.1";
		int port = 12345;
		while (true) {
			Socket client = new Socket(host, port);
			OutputStream out = client.getOutputStream();
			out.write("hello".getBytes("utf8"));
			out.flush();
			out.close();
			client.close();
			Thread.currentThread().sleep(5);
		}

	}

}
