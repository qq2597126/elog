package com.pxjy.thrift;

import org.apache.thrift.transport.TSocket;

/**
 * Thrift连接池Provider
 * 
 * @author jiaweiwang
 *
 */
public interface ConnectionProvider {
	
	/**
	 * 获取连接池中的一个链接
	 * @return
	 */
	public TSocket getConnection();
	
	/**
	 * 释放一个连接回连接池
	 */
	public void returnConnection(TSocket socket);

}
