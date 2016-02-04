package com.znsx.util.socket;

/**
 * 字节类型转换工具类
 * 
 * @author HBJ <br />
 *         create at 2011-1-7 上午10:20:00
 */
public class ByteUtil {

	/**
	 * int转4字节byte数组
	 * 
	 * @param value
	 *            数据
	 * @return
	 */
	public static byte[] intToByteArray(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			// b[i] = (byte) ((value >>> offset) & 0xFF);
			b[3 - i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

	/**
	 * 4字节byte数组(小字节序)转int
	 * 
	 * @param b
	 *            4字节数组
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
		// return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
		// + (b[3] & 0xFF);
		return ((b[3] < 0) ? (b[3] + 256) << 24 : b[3] << 24)
				+ ((b[2] < 0) ? (b[2] + 256) << 16 : b[2] << 16)
				+ ((b[1] < 0) ? (b[1] + 256) << 8 : b[1] << 8)
				+ ((b[0] < 0) ? (b[0] + 256) : b[0]);
	}

	/**
	 * 截取指定byte数组的某段内容
	 * 
	 * @param src
	 *            源byte数组
	 * @param startPos
	 *            截取的起始位置
	 * @param length
	 *            要截取的长度
	 * @return 一个截取出来的全新byte数组
	 */
	public static byte[] subByteArray(byte[] src, int startPos, int length) {
		byte[] desc = new byte[length];
		System.arraycopy(src, startPos, desc, 0, length);
		return desc;
	}

	/**
	 * 连接多个byte数组
	 * 
	 * @param array
	 *            多个byte数组
	 * @return 连接生成的新byte数组
	 */
	public static byte[] combineByteArray(byte[]... array) {
		byte[] buffer = new byte[4096];
		int length = 0;
		for (int i = 0; i < array.length; i++) {
			if (null != array[i]) {
				System.arraycopy(array[i], 0, buffer, length, array[i].length);
				length += array[i].length;
			}
		}
		return subByteArray(buffer, 0, length);
	}

	public static void main(String[] args) {
		// byte[] a = intToByteArray(70001);
		//
		// byte[] b = new byte[] { 113, 17, 1, 0 };
		// int out = byteArrayToInt(b);
		// System.out.println(out);

		int version = 257;
		int length = 0;
		int number = 11144111;
		int seq = 53342;
		int flag = 500;

		byte[] num = intToByteArray(number);

		byte[] out = combineByteArray(intToByteArray(version),
				intToByteArray(length), intToByteArray(number),
				intToByteArray(seq), intToByteArray(flag));

		int reverse = byteArrayToInt(num);
		System.out.println(reverse);
	}
}
