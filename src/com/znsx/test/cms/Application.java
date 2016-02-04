package com.znsx.test.cms;

import java.util.Timer;

/**
 * 测试应用主线程
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 上午11:15:54
 */
public class Application {

	/**
	 * 定时器
	 */
	public static Timer timer = new Timer("myTimer");
	/**
	 * 每个线程每秒发送的请求数量
	 */
	public static final int REQUEST_PER_SECOND = 100;
	/**
	 * 服务器IP和端口
	 */
	public static final String SERVER_IP = "http://192.168.1.2:8080/cms/";
	/**
	 * timer任务次数
	 */
	public static final int SEND_TIMES = 20;
	/**
	 * 起始时间
	 */
	public static final long BEGIN = System.currentTimeMillis();
	/**
	 * 任务调度线程数量
	 */
	public static final int THREAD_NUM = 1;

	/**
	 * 入口main方法
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013 上午11:15:54
	 */
	public static void main(String[] args) {
		ScheduleTask task = new ScheduleTask();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
