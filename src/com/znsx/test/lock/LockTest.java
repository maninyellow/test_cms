package com.znsx.test.lock;

/**
 * LockTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2015-6-8 下午2:39:02
 */
public class LockTest {
	
	public static LockTest instance = new LockTest();

	public synchronized String test() {

		try {
			while (true) {
				if (isDone()) {
					System.out.println(Thread.currentThread().getName());
					wait();
				} else {
					System.out.println("Thread-1 notifyAll()");
					notifyAll();
					wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public boolean isDone() throws Exception {
		Thread.sleep(1000);
		if (Thread.currentThread().getName().equals("Thread-1")) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				LockTest.instance.test();
			}
		};
		
		for (int i = 0; i<11; i++) {
			Thread t = new Thread(runnable, "Thread-" + i+"");
			t.start();
		}
		
	}
}
