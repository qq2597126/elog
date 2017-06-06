package com.pxjy.elog.mongoDao;

import java.util.List;

import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.domain.bo.EventLogCondition;
import com.pxjy.elog.domain.bo.EventLogStatistics;

public interface IEventLogDao {
	//添加日志
	public void addEventLogs(List<EventLogBo> eventLogBo,String appId);
	//分组查询
	public EventLogCondition selectEventLogByLoginName(EventLogCondition eventLogCondition);
	//查询日志
	public List<EventLogStatistics> findStatisticsEventLog();
}
