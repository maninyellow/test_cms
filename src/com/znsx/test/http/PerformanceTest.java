package com.znsx.test.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * PerformanceTest
 * 
 * @author MIKE
 *         <p />
 *         Create at 2015 下午5:36:20
 */
public class PerformanceTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author MIKE
	 *         <p />
	 *         Create at 2015 下午5:36:20
	 */
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();

		for (int i = 0; i < 1; i++) {
//			nagleSend();
			noDelaySend();
		}

		long end = System.currentTimeMillis();
		System.out.println("Use " + (end - begin) + "ms");
	}

	public static void nagleSend() throws Exception {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient client = new HttpClient(new HttpClientParams(),
							new SimpleHttpConnectionManager(true));
					PostMethod method = new PostMethod(
							"http://192.168.1.5:8080/cms/heartbeat.xml");

					StringBuffer body = new StringBuffer(
							"<Request Method=\"Heartbeat\" Cmd=\"3010\"><SessionId>10050000000000000000</SessionId>");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");

					body.append("</Request>");

					RequestEntity entity = new StringRequestEntity(body.toString(),
							"application/xml", "utf8");
					method.setRequestEntity(entity);
					long begin = System.currentTimeMillis();
					client.executeMethod(method);

					method.releaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		};
		
		Thread[] t = new Thread[100];
		for (int i = 0; i < 100; i ++) {
			t[i] = new Thread(run);
			t[i].start();
		}
		for (Thread th : t) {
			th.join();
		}
	}

	public static void noDelaySend() throws Exception {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					SimpleHttpConnectionManager connectionManager = new SimpleHttpConnectionManager(
							true);
					connectionManager.getParams().setTcpNoDelay(true);
					HttpClient client = new HttpClient(new HttpClientParams(),
							connectionManager);
					PostMethod method = new PostMethod(
							"http://192.168.1.5:8080/cms/heartbeat.xml");

					StringBuffer body = new StringBuffer(
							"<Request Method=\"Heartbeat\" Cmd=\"3010\"><SessionId>10050000000000000000</SessionId>");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");
					body.append("<Element Attribute=\"得看看手机待机四度空间上岛咖啡京东方就是空的风纪扣士大夫是空党风建设看见大发上岛咖啡极度恐惧发上岛咖啡金库大劫发上岛咖啡教科书搭街坊\" />");

					body.append("</Request>");

					RequestEntity entity = new StringRequestEntity(body.toString(),
							"application/xml", "utf8");
					method.setRequestEntity(entity);
					long begin = System.currentTimeMillis();
					client.executeMethod(method);

					method.releaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		};
		
		Thread[] t = new Thread[100];
		for (int i = 0; i < 100; i ++) {
			t[i] = new Thread(run);
			t[i].start();
		}
		for (Thread th : t) {
			th.join();
		}
	}
}
