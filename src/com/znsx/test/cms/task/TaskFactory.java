package com.znsx.test.cms.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.lang.StringUtils;

import com.znsx.test.cms.Application;
import com.znsx.util.http.SocketHttpUtil;

/**
 * 接口任务生成工厂
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 上午11:35:52
 */
public class TaskFactory {

	private static TaskFactory instance = new TaskFactory();
	/**
	 * 创建的任务数量
	 */
	public volatile static int count = 0;
	/**
	 * 日志记录
	 */
	private File file = new File("D:/request_log.txt");
	private FileWriter writer;

	private TaskFactory() {
	}

	public static TaskFactory getInstance() {
		return instance;
	}

	public Runnable createTask(final String uri, final String params) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
//					sendRequest(uri, params);
					sendRequestBySocket(uri, params);
				} catch (Exception e) {
					e.printStackTrace();
				}
				count++;
				if ((count + 100) >= (Application.REQUEST_PER_SECOND
						* Application.THREAD_NUM * (Application.SEND_TIMES))) {
					writeLog("use : "
							+ (System.currentTimeMillis() - Application.BEGIN)
							+ "ms\n");
					writeLog("total post " + count + " requests !\n");
					try {
						writer.flush();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(-1);
				}
			}
		};
		return task;
	}

	public void writeLog(String log) {
		try {
			if (null == writer) {
				writer = new FileWriter(file);
			}
			writer.append(log);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HttpURLConnection sendRequest(String uri, String params)
			throws Exception {
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(false);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		// conn.setRequestProperty("connection", "Keep-Alive");
		// conn.setRequestProperty("user-agent",
		// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// conn.setRequestProperty("Charsert", "UTF-8");
		// conn.setRequestProperty("Content-Type", "application/xml");
		OutputStream out = conn.getOutputStream();
		if (StringUtils.isNotBlank(params)) {
			try {
				long begin = System.currentTimeMillis();
				out.write(params.getBytes("utf8"));
				out.flush();
				int code = conn.getResponseCode();
				long end = System.currentTimeMillis();
				long time = end - begin;
				writeLog("Task " + count + " use " + time + "ms\n");
				if (code != HttpURLConnection.HTTP_OK) {
					System.out.println("Request failed !");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
					conn.disconnect();
					conn = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	public void sendRequestBySocket(String uri, String params)
			throws Exception {
		String[] address = uri.split(":");
		String ip = address[1].substring(2, address[1].length());
		String port = address[2].substring(0, address[2].indexOf("/"));
		String url = address[2].substring(address[2].indexOf("/"),
				address[2].length());
		
		long begin = System.currentTimeMillis();
		SocketChannel channel = SocketHttpUtil.sendPost(ip,
				Integer.parseInt(port), url, params, "utf8");

		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int temp = 0;
		try {
			while ((temp = channel.read(buffer)) >= 0) {
				// donothing
//				buffer.clear();
//				buffer.flip();
//				byte[] dst = new byte[count];
//				buffer.get(dst, 0, count);
//				System.out.print(new String(dst));
				buffer.clear();
			}
			long end = System.currentTimeMillis();
			long time = end - begin;
			writeLog("Task " + count + " use " + time + "ms\n");
			if (channel != null && channel.isOpen()) {
				channel.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
