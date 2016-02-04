package com.znsx.util.database;

import java.util.concurrent.Callable;

import com.znsx.test.bo.VideoDeviceProperty;

/**
 * MemcachedTestCallable
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-1-27 下午3:37:18
 */
public class MemcachedTestCallable implements Callable<Long> {
	@Override
	public Long call() throws Exception {
		int count = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			CacheUtil.putCache("check_session_task_lock",
					new VideoDeviceProperty(), "sessionCache");
		}
		long l = System.currentTimeMillis() - begin;
		return Long.valueOf(l);
	}
}
