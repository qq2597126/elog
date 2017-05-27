package com.pxjy.elog.domain.param;

import com.pxjy.common.paginator.PageParam;


/**
 * 事件信息查询对象
 * @author cg
 * @date 2017-05-25
 */
 @SuppressWarnings("serial")
public class EventInfoParam extends PageParam {
	/**
	 * APP标识
	 */
	private String appId;

	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
