package com.pxjy.thrift;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class GenericConnectionProvider implements ConnectionProvider, InitializingBean, DisposableBean{
	
	public static final Logger logger = LoggerFactory.getLogger(GenericConnectionProvider.class);
	// thrift服务IP地址
	private String serviceIP;
	// 服务端口
	private int servicePort;
	// 链接超时时间
	private int conTimeOut;
	// 可以从缓存池中分配对象的最大数量
	private int maxActive;
	// 缓存池中最大空闲对象数量
	private int maxIdle;
	// 缓存池中最小空闲对象数量
	private int minIdle;
	// 提取对象时最大等待时间，超时会抛出异常
	private long maxWait;
	
	private ObjectPool<TSocket> objectPool = null;

	@Override
	public TSocket getConnection() {
		try {
			TSocket socket = (TSocket) objectPool.borrowObject();
			return socket;
		} catch (Exception e) {
			logger.error("error getConnection()", e);
			throw new RuntimeException("error getConnection()", e);
		}
	}

	@Override
	public void returnConnection(TSocket socket) {
		try {
			objectPool.returnObject(socket);
		} catch(Exception e) {
			logger.error("error returnConnection()", e);
			throw new RuntimeException("error returnConnection()", e);
		}
		
	}
	
	@Override
	public void destroy() throws Exception {
		try {
			objectPool.close();
		} catch (Exception e) {
			throw new RuntimeException("error destroy()", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ThriftPoolableObjectFactory thriftPoolableObjectFactory = new ThriftPoolableObjectFactory(this.serviceIP, this.servicePort, this.conTimeOut);
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		
		config.setTestOnBorrow(true);
		config.setTestOnCreate(true);
		config.setTestOnReturn(true);
		
		// 最大空闲
		config.setMaxIdle(this.maxIdle);
		// 连接池最大数
		config.setMaxTotal(this.maxActive);
		// 最小空闲
		config.setMinIdle(this.minIdle);
		// 在从pool中去对象时进行有效性检查，会调用工厂中的validateObject
		config.setTestOnBorrow(true);
		// 提取对象时最大等待时间，超时会抛出异常
		config.setMaxWaitMillis(this.maxWait);
		// 最小的空闲对象驱除时间间隔，空闲超过指定的时间的对象，会被清除掉
		config.setMinEvictableIdleTimeMillis(-1);
		// 后台驱逐线程休眠时间
		config.setTimeBetweenEvictionRunsMillis(10000);
		// //设置驱逐线程每次检测对象的数量
		config.setNumTestsPerEvictionRun(this.maxIdle);
		// 设置空闲时对象使用validateObject校验
		config.setTestWhileIdle(true);
		objectPool = new GenericObjectPool<TSocket>(thriftPoolableObjectFactory, config);
	}

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public int getServicePort() {
		return servicePort;
	}

	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	public int getConTimeOut() {
		return conTimeOut;
	}

	public void setConTimeOut(int conTimeOut) {
		this.conTimeOut = conTimeOut;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public ObjectPool<TSocket> getObjectPool() {
		return objectPool;
	}

	public void setObjectPool(ObjectPool<TSocket> objectPool) {
		this.objectPool = objectPool;
	}

	
}
