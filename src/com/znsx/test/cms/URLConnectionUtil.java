package com.znsx.test.cms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Http工具类
 * 
 * @author zhuanqi@megaeyes.com
 * 
 *         Oct 26, 2010
 */
public class URLConnectionUtil {

	public static String sendGet(String url, String param,
			Map<String, String> header) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			// 超时时间
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("content-type", "text/html");
			conn.setRequestProperty("connection", "Keep-Alive");

			// 设置头参数
			if (header != null)
				for (String key : header.keySet()) {
					conn.setRequestProperty(key, header.get(key));
				}
			conn.connect();

			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String param,
			Map<String, String> header) throws UnsupportedEncodingException,
			IOException {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			// 超时时间
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("content-type", "text/html");
			conn.setRequestProperty("connection", "Keep-Alive");
			// 设置头参数
			if (header != null)
				for (String key : header.keySet()) {
					conn.setRequestProperty(key, header.get(key));
				}

			conn.setDoOutput(true);
			conn.setDoInput(true);

			out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
			if (StringUtils.isNotBlank(param))
				out.write(param);
			out.flush();
			// 读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
