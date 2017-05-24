package com.pxjy.elog.domain.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.pxjy.common.domain.BaseModel;

/**
 * APP信息
 * @author cg
 * @date 2017-05-24
 */
@SuppressWarnings("serial")
public class AppInfoBo extends BaseModel{
	
	/**
	 * APP_ID
	 */
	private String appId;
	
	/**
	 * APP_KEY
	 */
	private String appKey;
	
	/**
	 * APP_NAME
	 */
	private String appName;
	
	/**
	 * CREATE_USER
	 */
	private Integer createUser;
	
	/**
	 * CREATE_TIME
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 1有效，0无效
	 */
	private Integer status;

	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}
	
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getCreateUser() {
		return createUser;
	}
	
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
