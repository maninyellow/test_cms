package com.znsx.test.thread;

/**
 * MyThread
 * @author huangbuji <p />
 * Create at 2014-4-24 下午2:45:14
 */
public class MyThread implements Runnable {
	@Override
	public void run() {
		int count = 10;
		for (int i = 1; i < count; i ++) {
			System.out.println(Thread.currentThread().getName() + i);
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
