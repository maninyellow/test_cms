package com.znsx.test.exception;

import com.znsx.cms.service.exception.BusinessException;

/**
 * TestException
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-2-13 下午3:41:59
 */
public class TestException {

	public static void method1() {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if (i > 0) {
				continue;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("method1 use " + (end - begin) + "ms");
	}

	public static void method2() {
		long begin = System.currentTimeMillis();

		for (int i = 0; i < 10000000; i++) {
			try {
				if (i > 0) {
					continue;
				}
			} catch (Exception e) {
				continue;
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("method2 use " + (end - begin) + "ms");
	}

	public static void method3() {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			try {
				if (i > 0) {
					throw new Exception();
				}
			} catch (IllegalArgumentException e) {
				continue;
			} catch (BusinessException e) {
				continue;
			} catch (NullPointerException e) {
				continue;
			} catch (Exception e) {
				continue;
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("method3 use " + (end - begin) + "ms");
	}

	public static void level6() throws Exception {
		throw new Exception();
	}
	
	public static void level5() throws Exception {
		//throw new Exception();
		level6();
	}
	
	public static void level4() throws Exception {
		//throw new Exception();
		level5();
	}
	
	public static void level3() throws Exception {
		//throw new Exception();
		level4();
	}

	public static void level2() throws Exception {
		//throw new Exception();
		level3();
	}

	public static void level1() throws Exception {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			try {
				level2();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("use " + (end - begin) + "ms");
	}

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-2-13 下午3:41:59
	 */
	public static void main(String[] args) throws Exception {
		// long begin = System.currentTimeMillis();
		//
		// for (int j = 0; j < 10000; j++) {
		// String s1 = "a = 123\\r\\nb = 得到撒道可道\\r\\nc = 789";
		// s1 = StringUtils.replace(s1, "\\r", "\r");
		// s1 = StringUtils.replace(s1, "\\n", "\n");
		// }
		// System.out.println("utils use : "
		// + (System.currentTimeMillis() - begin) + "ms");
		//
		// // System.out.println(sb.toString());
		//
		// begin = System.currentTimeMillis();
		// StringBuffer sb = new StringBuffer();
		//
		// String s = "a = 123\\r\\nb = 得到撒道可道\\r\\nc = 789";
		// System.out.println(s);
		//
		// char[] c = s.toCharArray();
		// int length = c.length;
		// for (int i = 0; i < length; i++) {
		// if (i < length - 1 && c[i] == '\\') {
		// if (c[i + 1] == 'r') {
		// sb.append('\r');
		// i++;
		// } else if (c[i + 1] == 'n') {
		// sb.append('\n');
		// i++;
		// } else {
		// sb.append(c[i]);
		// }
		// } else {
		// sb.append(c[i]);
		// }
		// }
		// System.out.println(sb.toString());
		//
		//
		// int[] arr = new int[0];
		// for (int i = 0; i < arr.length; i ++) {
		//
		// }
		//
		// Integer io = null;
		// User user = new User();
		// user.setId(io);
		// user.setAge(io);
		// user.setName("hh");

		// String s = null;
		// if ("true".equals(s)) {
		// System.out.println(1);
		// } else {
		// System.out.println(0);
		// }

		level1();
	}
}
