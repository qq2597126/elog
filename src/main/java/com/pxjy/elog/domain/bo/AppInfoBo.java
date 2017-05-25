package com.pxjy.elog.domain.bo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.pxjy.common.domain.BaseModel;

/**
 * APP信息
 * @author cg
 * @date 2017-05-25
 */
@SuppressWarnings("serial")
public class AppInfoBo extends BaseModel{
	
	/**
	 * APP标示
	 */
	private String appId;
	
	/**
	 * APP验签加密字符串
	 */
	private String appKey;
	
	/**
	 * APP显示名称
	 */
	private String appName;
	
	/**
	 * 发送间隔
	 */
	private Long sentTime;
	
	/**
	 * 发送类型：0启动时发送，1按照SEND_TIME设置的时间间隔发送
	 */
	private Integer sendType;
	
	/**
	 * 创建人
	 */
	private Integer createUser;
	
	/**
	 * 创建时间
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

	

	public Long getSentTime() {
		return sentTime;
	}

	public void setSentTime(Long sentTime) {
		this.sentTime = sentTime;
	}

	public Integer getSendType() {
		return sendType;
	}
	
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
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
