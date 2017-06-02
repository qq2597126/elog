package com.pxjy.thrift;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Thrift连接管理，通过AOP切面到ThriftClient类中，用于连接socket
 * 
 * @author jiaweiwang
 *
 */
public class ConnectionManager implements MethodInterceptor{
	
	public Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	ThreadLocal<TSocket> threadLocal = new ThreadLocal<TSocket>();
	
	public ConnectionProvider connectionProvider;

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		try {
			TSocket socket = connectionProvider.getConnection();
			threadLocal.set(socket);
			return arg0.proceed();
		} catch(Exception e) {
			logger.error("error ConnectionManager.invoke()" + e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取socket
	 * @return
	 */
	public TSocket getSocket() {
		return threadLocal.get();
	}
	
	/**
	 * 将socket归还连接池
	 */
	public void returnConnection() {
		connectionProvider.returnConnection(threadLocal.get());
		threadLocal.remove();
	}
	
	/**
	 * 关闭socket，连接异常时调用
	 */
	public void closeConnection() {
		threadLocal.get().close();
	}

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public void setConnectionProvider(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

}
