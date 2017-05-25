package com.pxjy.elog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.mongoDao.IEventLogDao;
import com.pxjy.elog.service.IEventLogService;
public class EventLogServiceImpl implements IEventLogService{
	@Autowired
	private IEventLogDao eventLogDao;
	@Override
	public void mongoAddEventLog(List<EventLogBo> enentLogs,String appId) {
		eventLogDao.addEventLogs(enentLogs, appId);
	}
	
	
	public IEventLogDao getEventLogDao() {
		return eventLogDao;
	}
	public void setEventLogDao(IEventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}
	
}
