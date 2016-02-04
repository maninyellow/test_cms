package com.znsx.test.hash;

import java.util.Arrays;
import java.util.List;

/**
 * HashTest
 * @author huangbuji <p />
 * Create at 2015-10-30 上午11:15:55
 */
public class HashTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","2","3","11111");
		System.out.println(list.hashCode());
		
		List<String> list1 = Arrays.asList("1","2","3","11111");
		System.out.println(list1.hashCode());
		
		
	}
}
