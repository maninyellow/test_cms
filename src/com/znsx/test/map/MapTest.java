package com.znsx.test.map;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

import com.znsx.test.bo.User;

//import com.znsx.cms.service.model.AuthDeviceVO;

/**
 * MapTest
 * @author huangbuji <p />
 * Create at 2013-11-20 上午9:55:49
 */
public class MapTest {

	/**
	 * main方法说明
	 * @param args
	 * @author huangbuji <p />
	 * Create at 2013-11-20 上午9:55:49
	 */
	public static void main(String[] args) throws Exception {
//		User[] users = new User[5];
//		User[] temp = new User[5];
//		for (int i =0;i<users.length;i++) {
//			User u = new User();
//			u.setId(i);
//			users[i] = u;
//		}
//		
//		for (int i =0;i<temp.length;i++) {
//			User u = new User();
//			u.setId(10+i);
//			temp[i] = u;
//		}
//		
//		for (int i=0;i<temp.length;i++) {
//			users[i] = temp[i];
//		}
//		
//		for (int i =0;i<temp.length;i++) {
//			temp[i] = null;
//		}
//		
//		for (int i=0;i<users.length;i++) {
//			System.out.println(users[i]);
//		}
//		for (int i=0;i<temp.length;i++) {
//			System.out.println(temp[i]);
//		}
		
//		String s = "2014-02-01 00:00:00.000";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		Date d = sdf.parse(s);
//		System.out.println(d.getTime());
//		
//		System.out.println(new Date(1429257960034l));
		
		
//		List<String> list = Arrays.asList("a","d","c","f","w");
//		Collections.sort(list, new Comparator<String>() {
//			public int compare(String o1, String o2) {
//				return o1.compareToIgnoreCase(o2);
//			};
//		});
//		
//		for (String s: list) {
//			System.out.println(s);
//		}
		
//		String[] s = new String[] {"1","2"};
//		Object o = s;
//		if (o instanceof String[]) {
//			String[] os = (String[]) o;
//			System.out.println(os[0]);
//			System.out.println(os[1]);
//		}
//		
//		Object obj = new int[]{3,5};
//		if (obj instanceof int[]) {
//			int[] obji = (int[]) obj;
//			System.out.println(obji[0]);
//		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		
		System.out.println(map.get(null));
		
	}
	
	public static void myprint(int i) {
		if (i > 10) {
//			return;
		} else {
			System.out.println(i);
			myprint(++i);
		}
	} 

}
