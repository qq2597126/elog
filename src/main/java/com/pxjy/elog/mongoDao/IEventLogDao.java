package com.pxjy.elog.mongoDao;

import java.util.List;

import com.pxjy.elog.domain.bo.EventLogBo;

public interface IEventLogDao {
	//添加日志
	public void addEventLogs(List<EventLogBo> eventLogBo,String appId);
	//查询日志
	public List<EventLogBo> selectEventLog();
	
}
