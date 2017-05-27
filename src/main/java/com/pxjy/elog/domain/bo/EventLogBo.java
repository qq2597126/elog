package com.pxjy.elog.domain.bo;

import org.springframework.data.annotation.Id;

public class EventLogBo {
	@Id
	private String id;
	
	private String eventKey;
	
	private String osVersion;
	
	private String appBuild;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	private String userId;

	public EventLogBo() {
	}
	public EventLogBo(String id, String eventKey, String osVersion, String appBuild, String createTime, String userId) {
		super();
		this.id = id;
		this.eventKey = eventKey;
		this.osVersion = osVersion;
		this.appBuild = appBuild;
		this.createTime = createTime;
		this.userId = userId;
	}


	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppBuild() {
		return appBuild;
	}

	public void setAppBuild(String appBuild) {
		this.appBuild = appBuild;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
