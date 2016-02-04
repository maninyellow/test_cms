package com.znsx.test.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TcpDemo
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2015-5-14 下午4:51:32
 */
public class TcpServer {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-5-14 下午4:51:32
	 */
	public static void main(String[] args) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerSocket server = new ServerSocket(12345);
					while (true) {
						server.accept();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Socket client = new Socket("127.0.0.1", 12345);
						OutputStream out = client.getOutputStream();
						out.write("hello".getBytes("utf8"));
						out.flush();
						out.close();
						client.close();
						Thread.currentThread().sleep(5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		Thread.currentThread().sleep(1000);
		t2.start();
		t1.join();
	}

}
