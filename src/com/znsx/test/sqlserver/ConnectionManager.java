package com.znsx.test.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ConnectionManager
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-17 下午3:23:11
 */
public class ConnectionManager {
	private static ConnectionManager instance;
	private ComboPooledDataSource ds;

	private ConnectionManager() {
		// singleton
		init();
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}

		return instance;
	}

	private void init() {
		ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl("jdbc:sqlserver://192.168.1.111:1433");
		ds.setUser("test");
		ds.setPassword("123456");
		// 连接关闭时默认将所有未提交的操作回滚。Default: false autoCommitOnClose
		ds.setAutoCommitOnClose(true);

		// 定义所有连接测试都执行的测试语句。在使用连接测试的情况下这个一显著提高测试速度。注意：
		// 测试的表必须在初始数据源的时候就存在。Default: null preferredTestQuery
		ds.setPreferredTestQuery("select 1");
		// 因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的
		// 时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
		// 等方法来提升连接测试的性能。Default: false testConnectionOnCheckout
		ds.setTestConnectionOnCheckout(false);
		// 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false testConnectionOnCheckin
		ds.setTestConnectionOnCheckin(false);
		// 获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效
		// 保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试
		// 获取连接失败后该数据源将申明已断开并永久关闭。Default: false breakAfterAcquireFailure
		ds.setBreakAfterAcquireFailure(false);

		try {
			// 初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间。Default: 3
			ds.setInitialPoolSize(3);
			// 连接池中保留的最大连接数。Default: 15 maxPoolSize
			ds.setMaxPoolSize(20);
			// 连接池中保留的最小连接数。
			ds.setMinPoolSize(1);
			// 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 acquireIncrement
			ds.setAcquireIncrement(1);
			// 每60秒检查所有连接池中的空闲连接。Default: 0 idleConnectionTestPeriod
			ds.setIdleConnectionTestPeriod(600);
			// 最大空闲时间,25000秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 maxIdleTime
			ds.setMaxIdleTime(25000);
			// 定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 acquireRetryAttempts
			ds.setAcquireRetryAttempts(30);
			// 两次连接中间隔时间，单位毫秒。Default: 1000 acquireRetryDelay
			ds.setAcquireRetryDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Connection connection = ds.getConnection();
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		ConnectionManager.getInstance().closeConnection(conn);
	}
}

