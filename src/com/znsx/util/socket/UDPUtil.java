package com.znsx.util.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPUtil
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-7-10 下午3:36:58
 */
public class UDPUtil {

	/**
	 * 发送UDP包
	 * 
	 * @param ip
	 *            目标IP地址
	 * @param port
	 *            目标端口
	 * @param buffer
	 *            数据包内容
	 * @throws Exception
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013-7-10 下午4:38:54
	 */
	public static void send(String ip, int port, byte[] buffer)
			throws Exception {
		InetAddress address = InetAddress.getByName(ip);

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
				address, port);
		DatagramSocket socketClent = new DatagramSocket();
		socketClent.send(packet);
	}

	/**
	 * 接受指定端口的UDP数据包
	 * 
	 * @param port
	 *            接受端口
	 * @throws Exception
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013-7-10 下午5:27:32
	 */
	public static void receive(int port) throws Exception {
		DatagramSocket socketServer = new DatagramSocket(port);
		byte[] buffer = new byte[4096];

		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, 4096);
			socketServer.receive(packet);
			String message = new String(packet.getData(), "utf8");
			System.out.println(message);
		}
	}

	public static void main(String[] args) throws Exception {
		receive(12345);
	}
}
