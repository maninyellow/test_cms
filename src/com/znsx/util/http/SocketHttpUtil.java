package com.znsx.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketHttpUtil
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 上午11:04:47
 */
public class SocketHttpUtil {

	/**
	 * socket操作发送http协议
	 * 
	 * @param ip
	 *            服务端IP
	 * @param port
	 *            服务端端口
	 * @param uri
	 *            接口相对路径
	 * @param params
	 *            参数
	 * @param encode
	 *            编码
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013 上午11:04:47
	 */
	public static SocketChannel sendPost(String ip, int port, String uri,
			String params, String encode) {
		// 换行
		byte[] newLine = new byte[2];
		newLine[0] = 0x0d;
		newLine[1] = 0x0a;

		SocketChannel channel = null;
		try {
			InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
			channel = SocketChannel.open(socketAddress);
			String interfaceUri = "TETD " + uri + " HTTP/1.1";
			String contentType = "Content-type: application/x-www-form-urlencoded";
			String host = "Host: " + ip + ":" + port;
			String connection = "Connection: close";
			String contentLength = "Content-Length: "
					+ params.getBytes(encode).length;
			// 计算写入长度
			StringBuffer sb = new StringBuffer();
			sb.append(interfaceUri);
			sb.append(contentType);
			sb.append(host);
			sb.append(connection);
			sb.append(contentLength);
			sb.append(params);
			int length = sb.toString().getBytes(encode).length + 12; // +12是因为存在6个换行

			ByteBuffer out = ByteBuffer.allocate(length);
			out.put(interfaceUri.getBytes(encode));
			out.put(newLine);
			out.put(contentType.getBytes(encode));
			out.put(newLine);
			out.put(host.getBytes(encode));
			out.put(newLine);
			out.put(connection.getBytes(encode));
			out.put(newLine);
			out.put(contentLength.getBytes(encode));
			out.put(newLine);
			out.put(newLine);
			out.put(params.getBytes(encode));
			out.flip();
			channel.configureBlocking(false);
			channel.write(out);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channel;
	}
}
