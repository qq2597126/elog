package com.pxjy.elog.service;

import java.util.List;

import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.domain.bo.EventLogCondition;
import com.pxjy.elog.domain.bo.EventLogStatistics;

public interface IEventLogService {
	//添加日志
	public void mongoAddEventLog(List<EventLogBo> enentLogs,String appId);
	//统计查询
	public List<EventLogStatistics> getEventLogStatistics();
	//条件查询
	public EventLogCondition getEventLogList(EventLogCondition eventLogCondition);
}
