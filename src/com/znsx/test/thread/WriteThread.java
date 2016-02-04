package com.znsx.test.thread;

import org.apache.commons.lang.math.RandomUtils;

import com.znsx.test.bo.User;

/**
 * WriteThread
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-9 上午10:46:41
 */
public class WriteThread implements Runnable {

	public static int SEQ = 1;

	@Override
	public void run() {
		while (true) {
			if (SEQ <= 10) {
				User user = new User();
				user.setId(SEQ++);
				user.setAge(RandomUtils.nextInt(100));
				user.setName("user" + SEQ);
				ThreadTest.map.put(user.getId(), user);
			} else {
				for (int i = 1; i <= 10; i++) {
					User user = ThreadTest.map.get(Integer.valueOf(i));
					user.setAge(RandomUtils.nextInt(100));
				}
			}
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(RandomUtils.nextFloat());
	}
}
