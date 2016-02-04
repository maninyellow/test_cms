package com.znsx.test.point;

import com.znsx.test.bo.User;

/**
 * PointTest
 * @author huangbuji <p />
 * Create at 2014-10-16 上午9:47:40
 */
public class PointTest {

	/**
	 * main方法说明
	 * @param args
	 * @author huangbuji <p />
	 * Create at 2014-10-16 上午9:47:40
	 */
	public static void main(String[] args) {
		User u1 = new User();
		u1.setId(Integer.valueOf(1));
		u1.setName("u1");
		
		User u2 = new User();
		u2.setId(Integer.valueOf(2));
		u2.setName("u2");

		User u3 = new User();
		u3.setId(Integer.valueOf(3));
		u3.setName("u3");
		
		User a = u1;
		User b = a;
		a = u2;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}

}
