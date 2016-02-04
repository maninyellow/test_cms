package com.znsx.test.thread;

import java.util.HashMap;
import java.util.Map;

import com.znsx.test.bo.User;

public class ThreadTest {

	public static User[] users = new User[10];
	public static Map<Integer, User> map = new HashMap<Integer, User>();

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-4-24 下午2:44:41
	 */
	public static void main(String[] args) throws Exception {
		// Thread t = new Thread(new MyThread());
		// t.start();
		// t.join();
		//
		// System.out.println("i am finish");

		// for (int i = 0;i<10;i++) {
		// User user = new User();
		// user.setId(1);
		// user.setAge(1);
		// user.setName("1");
		// users[i] = user;
		// }

		// Thread w = new Thread(new WriteThread());
		// w.start();
		// Thread r = new Thread(new ReadThread());
		// r.start();

		
		// 线程wait()时，只会释放线程对象锁，但是不会释放它获取的其他锁
		final Object lock1 = new Object();
		final Object lock2 = new Object();

		Runnable run = new Runnable() {

			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println(Thread.currentThread().getName()
							+ " acquired lock1");
					synchronized (lock2) {
						System.out.println(Thread.currentThread().getName()
								+ " acquired lock2");
						try {
//							lock1.wait();
							 synchronized (this) {
								 wait();
							 }
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}
		};

		Thread t1 = new Thread(run, "t1");
		Thread t2 = new Thread(run, "t2");
		Thread t3 = new Thread(run, "t3");

		t1.start();
		t2.start();
		t3.start();
	}

}
