package com.pxjy.elog.mongoDao.impl;

import java.util.List;


import com.pxjy.common.lang.DateUtil;
import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.mongoDao.AbstractBaseMongoTemplete;
import com.pxjy.elog.mongoDao.IEventLogDao;

public class IEventLogDaoImpl extends AbstractBaseMongoTemplete implements IEventLogDao{
	private static final String  DB_PREFIX="EVENT_LOG_";
	
	@Override
	public void addEventLogs(List<EventLogBo> eventLogBos,String appId) {
		String dbName = getDbName(appId);
		mongoTemplate.insert(eventLogBos, dbName); 
	}
	@Override
	public List<EventLogBo> selectEventLog() {
		return null;
	}
	/**
	 * 获取数据库的名称
	 */
	private String getDbName(String appId){
		return DB_PREFIX+appId+"_"+DateUtil.getYearMonth();
	}
}
