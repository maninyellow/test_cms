package com.znsx.test.cms;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Timer;
import java.util.TimerTask;

import com.znsx.util.http.SocketHttpUtil;

/**
 * HttpTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 下午1:36:19
 */
public class HttpTest {
	public static void main(String[] args) throws Exception {
		// URL url = new URL("http://192.168.1.2:8080/cms/list_organ.json");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setDoOutput(true);
		// conn.setRequestProperty("User-Agent", "Java/1.6.0_45");
		// conn.setRequestProperty("Host", "192.168.1.2:8080");
		// conn.setRequestProperty("Accept",
		// "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
		// conn.setRequestProperty("Connection", "keep-alive");
		// conn.setRequestProperty("Content-type",
		// "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length", "59");
		// String params =
		// "sessionId=10050000000000000100&organId=10010000000000000000";
		// OutputStream out = conn.getOutputStream();
		// out.write(params.getBytes("utf8"));
		// out.flush();
		// out.close();
		//
		// conn.getResponseCode();
		// conn.disconnect();
		// System.out.println("done");

		// String url = "http://192.168.1.2:8080/cms/list_organ.json";
		// String params =
		// "sessionId=10050000000000000100&organId=10010000000000000000";
		// URLConnectionUtil.sendPost(url, params, null);

		final ByteBuffer buffer = ByteBuffer.allocate(8192);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				long begin = System.currentTimeMillis();
				String params = "";
//				String params = "sessionId=10050000000000000100&organId=10010000000000000000&recursion=1";
//				String params = "sessionId=10050000000000000100&id=10020000000000000000";
//				String params = "<Request Method=\"Get_Resource_Route_Info\" Cmd=\"3007\"><StandardNumber>251010100001000001</StandardNumber></Request>";
				for (int i = 0; i < 400; i++) {
//					String params = "sessionId=10050000000000000000&subType=01&name=DVR-test-"+i+"&transport=TCP&mode=compatible&maxConnect=16&channelAmount=32&organId=10010000000000000001&lanIp=192.168.1."+i+"&port=5060&userName=admin&password=123456&heartCycle=120&ptsId=10060000000000000004";
					SocketChannel channel = SocketHttpUtil.sendPost(
							"192.168.1.2", 8080, 
//							"/cms/create_dvr.json",
//							"/cms/get_user.json",
//							"/cms/get_resource_route_info.xml",
//							"/cms/list_organ_device.xml",
							"/cms/test_interface.json",
							params, "utf8");

					
					int count = 0;
					try {
						while ((count = channel.read(buffer)) >= 0) {
							// donothing
//							buffer.flip();
//							byte[] dst = new byte[count];
//							buffer.get(dst, 0, count);
//							System.out.print(new String(dst));
							buffer.clear();
						}
						if (channel != null && channel.isOpen()) {
//							channel.socket().close();
							channel.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				long end = System.currentTimeMillis();
				System.out.println();
				System.out.println("use " + (end - begin) + " ms");

			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
}
