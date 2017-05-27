package com.pxjy.elog.domain.bo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.pxjy.common.domain.BaseModel;

/**
 * 事件信息
 * @author cg
 * @date 2017-05-25
 */
@SuppressWarnings("serial")
public class EventInfoBo extends BaseModel{
	
	/**
	 * APP标识
	 */
	private String appId;
	
	/**
	 * EVENT_KEY
	 */
	private String eventKey;
	
	/**
	 * 事件名称
	 */
	private String eventName;
	
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
