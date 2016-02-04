package com.znsx.test.icmp;

import java.net.InetAddress;

/**
 * IcmpTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-7-30 上午11:35:56
 */
public class IcmpTest {
	
	public static int success = 0;
	public static int fail = 0;
	
	public static void main(String[] args) throws Exception {
//		InetAddress ip = InetAddress.getByName("192.168.2.1");
//		System.out.println(ip.isReachable(4000));
		
		for (int j = 1; j < 101; j++) {
			for (int i = 1; i < 256; i++) {
				Thread t = new Thread(new PingThread("192.168." + j + "." + i));
				t.start();
			}
		}
		
		Object lock = new Object();
		synchronized (lock) {
			lock.wait(8000);
		}
		System.out.println(success);
		System.err.println(fail);
	}
}
