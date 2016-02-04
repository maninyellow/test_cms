package com.znsx.test.thread;

import java.util.Map.Entry;

import com.znsx.test.bo.User;

/**
 * ReadThread
 * @author huangbuji <p />
 * Create at 2014-9-9 上午11:00:03
 */
public class ReadThread implements Runnable {
	@Override
	public void run() {
		while (true) {
			for (Entry<Integer, User> entry : ThreadTest.map.entrySet()) {
				if (entry != null) {
					System.out.println(entry.getValue().toString());
				}
			}
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
