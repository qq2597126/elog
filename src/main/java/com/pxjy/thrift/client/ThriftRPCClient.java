package com.pxjy.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pxjy.thrift.ConnectionManager;
import com.pxjy.thrift.service.CheckLoginRequest;
import com.pxjy.thrift.service.CheckLoginResponse;
import com.pxjy.thrift.service.UserAuthorityService;

public class ThriftRPCClient {
	
	public static Logger logger = LoggerFactory.getLogger(ThriftRPCClient.class);
	
	private ConnectionManager connectionManager;
	
	/**
	 * 第一个测试用的thrift接口
	 * @param userName
	 * @return
	 */
	public String getUserAuthority(String userName) {
		TProtocol protocol = new TBinaryProtocol(connectionManager.getSocket());
		UserAuthorityService.Client client = new UserAuthorityService.Client(protocol);
		try {
			Integer result = client.live(1, 2);
			return result.toString();
		} catch (TException e) {
			connectionManager.closeConnection();
			logger.error("error ThriftRPCClient.getUserAuthority()", e);
		} finally {
			connectionManager.returnConnection();
		}
		return null;
	}
	
	/**
	 * 调用用户权限验证，获取用户详细信息
	 * @param request
	 * @return
	 */
	public CheckLoginResponse checkUserAuthority(CheckLoginRequest request) {
		logger.info("Thrift Client request : " + request);
		TSocket socket = connectionManager.getSocket();
		TProtocol protocol = new TBinaryProtocol(socket);
		UserAuthorityService.Client client = new UserAuthorityService.Client(protocol);
		try {
			CheckLoginResponse response = client.checkLoginSubSystem(request);
			logger.info("Thrift Client response : " + response);
			return response;
		} catch (TException e) {
			connectionManager.closeConnection();
			logger.error("error ThriftRPCClient.checkUserAuthority()", e);
		} finally {
			connectionManager.returnConnection();
		}
		return null;
	}
	
	/**
	 * 销毁socket，仅用于测试时使用
	 */
	public void closeSocket() {
		TSocket socket = connectionManager.getSocket();
		socket.close();
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	
}
