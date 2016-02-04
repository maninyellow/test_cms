package com.znsx.test.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.znsx.test.cms.task.TaskFactory;

/**
 * 每秒中的调度任务
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 上午10:40:17
 */
public class ScheduleTask extends TimerTask {

	public static String params = "";

	/**
	 * 执行次数
	 */
	public static int times = 0;

	public List<ThreadPoolExecutor> pools = new ArrayList<ThreadPoolExecutor>();

	public ScheduleTask() {
		params = "organId=10010000000000000000&sessionId=10050000000000000100";
		// 初始化任务调度线程
		for (int i = 0; i < Application.THREAD_NUM; i++) {
			LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
			ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 16, 300,
					TimeUnit.SECONDS, workQueue);
			pools.add(pool);
		}
	}

	@Override
	public void run() {
		System.out.println("finish post " + TaskFactory.count + " requests !");
		times++;
		if (times <= Application.SEND_TIMES) {
			for (ThreadPoolExecutor executor : pools) {
				for (int i = 0; i < Application.REQUEST_PER_SECOND; i++) {
					Runnable task = TaskFactory.getInstance().createTask(
							Application.SERVER_IP + "get_license.json", params);
					executor.execute(task);
				}
			}
		}

	}
}
