package com.pxjy.elog.service;

import java.util.List;

import com.pxjy.elog.domain.bo.EventLogBo;

public interface IEventLogService {
	//添加日志
	public void mongoAddEventLog(List<EventLogBo> enentLogs,String appId);
}
