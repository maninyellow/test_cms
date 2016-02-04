package com.znsx.test.cms.task;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.znsx.test.cms.Application;

/**
 * 获取机构树列表
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 下午3:08:10
 */
public class ListOrganDeviceTask implements Runnable {
	/**
	 * 创建的任务数量
	 */
	private static int count = 1;

	@Override
	public void run() {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Application.SERVER_IP
				+ "create_favorite.xml");
		String body = "<>";
		try {
			RequestEntity entity = new StringRequestEntity(body, null, "utf8");
			method.setRequestEntity(entity);

			long begin = System.currentTimeMillis();
			client.executeMethod(method);
			long end = System.currentTimeMillis();
			long time = end - begin;
			if (time > 1000) {
				System.out.println("Task " + count + " use " + time + "ms");
			}
			count++;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
			client = null;
		}

	}
}
