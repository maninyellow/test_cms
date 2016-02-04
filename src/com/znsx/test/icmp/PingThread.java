package com.znsx.test.icmp;

import java.net.InetAddress;

/**
 * PingThread
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-7-30 上午11:57:09
 */
public class PingThread implements Runnable {

	private InetAddress ipAddress;

	public PingThread(String ip) throws Exception {
		ipAddress = InetAddress.getByName(ip);
	}

	@Override
	public void run() {
		try {
			if (ipAddress.isReachable(5000)) {
				System.out.println(ipAddress.getHostAddress());
				synchronized (IcmpTest.class) {
					IcmpTest.success++;
				}
			} else {
				synchronized (IcmpTest.class) {
					IcmpTest.fail++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
