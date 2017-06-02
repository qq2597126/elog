package com.pxjy.thrift;

import java.net.Socket;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thrift连接工厂
 * 
 * @author jiaweiwang
 *
 */
public class ThriftPoolableObjectFactory extends BasePooledObjectFactory<TSocket> {
	
	public static final Logger logger = LoggerFactory.getLogger(ThriftPoolableObjectFactory.class);
	// Thrift服务的IP地址
	private String serviceIP;
	// 服务端口号
	private int servicePort;
	// 超时时间
	private int timeOut;
	
	public ThriftPoolableObjectFactory(String serviceIP, int servicePort, int timeOut) {
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeOut = timeOut;
	}

	@Override
	public TSocket create() throws Exception {
		try {
			TSocket transport = new TSocket(this.serviceIP, this.servicePort, this.timeOut);
			return transport;
		} catch (Exception e) {
			logger.error("ThriftPoolableObjectFactory.create()", e);
		}
		return null;
	}
	
	@Override
	public void activateObject(PooledObject<TSocket> p) throws Exception {
		try {
			if (!p.getObject().isOpen()) {
				p.getObject().open();
			}
			p.getObject().flush();
		} catch(Exception e) {
			logger.error("ThriftPoolableObjectFactory.activateObject()", e);
		}
		super.activateObject(p);
	}

	@Override
	public PooledObject<TSocket> wrap(TSocket obj) {
		return new DefaultPooledObject<TSocket>(obj);
	}
	
	@Override
	public boolean validateObject(PooledObject<TSocket> p) {
		TSocket transport = p.getObject();
		if (transport.isOpen()) {
			boolean isAlive = this.sendAlive(transport.getSocket());
			logger.info("Socket isAlive : " + isAlive);
			if (!isAlive) {
				try {
					transport.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return isAlive;
		} else {
			return false;
		}
	}

	@Override
	public void destroyObject(PooledObject<TSocket> p) throws Exception  {
		if (p.getObject().isOpen()) {
			p.getObject().close();
		}
	}
	
	/**
	 * 心跳检测，socket连接状态，如果失败需要销毁链接并尝试重新建立连接
	 * @param csocket
	 * @return
	 */
	public Boolean sendAlive(Socket csocket) {
		try {
			csocket.sendUrgentData(0xff);
			return true;
		} catch (Exception se) {
			logger.error("Thrift Socket Not Alive : ", se);
			return false;
		}
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

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	
}
