package com.znsx.test.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.znsx.util.database.MemcachedTestCallable;

/**
 * 缓存测试
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-12-27 上午11:24:26
 */
public class CacheTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @throws Exception
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-5-12 下午2:49:27
	 */
	public static void main(String[] args) throws Exception {
		// String pathname = CacheTest.class.getClass().getClassLoader()
		// .getResource("memcached.properties").getPath();
		// String pathname =
		// ClassLoader.getSystemResource("memcached.properties").getPath();
		//
		// Properties properties = new Properties();
		// properties.load(new FileInputStream(new File(pathname)));
		//
		// MemcachedCacheProvider provider = new MemcachedCacheProvider();
		// provider.start(properties);
		// Cache cache = provider.buildCache("TaskRegion", properties);
		// cache.put("CheckSessionTask.lock", "100");
		//
		// Object lock = new Object();
		// synchronized (lock) {
		// Thread.currentThread().sleep(10000);
		// }
		//
		// Object value = cache.get("CheckSessionTask.lock");
		// System.out.println(value);
		
		int count = 0;
		while (true) {

			List<FutureTask<Long>> list = new LinkedList<FutureTask<Long>>();

			for (int i = 0; i < 8; i++) {
				Callable<Long> c = new MemcachedTestCallable();
				FutureTask<Long> task = new FutureTask<Long>(c);
				list.add(task);
			}

			LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
			ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 8, 300,
					TimeUnit.SECONDS, workQueue);

			for (FutureTask<Long> task : list) {
				pool.execute(task);
			}

			boolean breakFlag = false;

			while (breakFlag) {
				Thread.currentThread().wait(1000);
				for (FutureTask<Long> task : list) {
					breakFlag = true;
					if (!task.isDone()) {
						breakFlag = false;
						continue;
					}
				}
			}
			pool.shutdown();

			long cost = 0;
			for (FutureTask<Long> task : list) {
				cost += task.get().longValue();
			}

			System.out.println(count++ + " - use " + cost + "ms");
			
			Thread.currentThread().sleep(10000);
		}
	}

}
