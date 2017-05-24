package com.pxjy.elog.domain.param;

import com.pxjy.common.paginator.PageParam;


/**
 * APP信息查询对象
 * @author cg
 * @date 2017-05-24
 */
 @SuppressWarnings("serial")
public class AppInfoParam extends PageParam {
	/**
	 * APP_ID
	 */
	private String appId;

	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
