package com.pxjy.elog.mongoDao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pxjy.common.lang.DateUtil;
import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.domain.bo.EventLogCondition;
import com.pxjy.elog.domain.bo.EventLogStatistics;
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
	public List<EventLogStatistics> findStatisticsEventLog() {
		List<EventLogStatistics> eventLogStatistics = new ArrayList<>();
		Set<String> collectionNames = mongoTemplate.getCollectionNames();
		GroupBy groupBy = new GroupBy("eventKey");
		//分组函数
		groupBy.initialDocument("{num:0}");
		//groupBy.finalizeFunction("");
		groupBy.reduceFunction("function(doc,prev){prev.num++}");
		for (String dbName : collectionNames) {
			GroupByResults<EventLogBo> group = mongoTemplate.group(dbName,groupBy,EventLogBo.class);
			//统计
			DBObject rawResults = group.getRawResults();
			if(rawResults!=null){
				//数据添加
				BasicDBList dbList = (BasicDBList) rawResults.get("retval");
				String[] dbNameSplit = dbName.split("_");
				String toYearMonth = DateUtil.getToYearMonth(dbNameSplit[3]);
				String appId = dbNameSplit[2];
				Iterator i = dbList.keySet().iterator();
				BasicDBObject basicDBObject = new BasicDBObject();
			    while (i.hasNext()) {
			            Object s = i.next();
			            EventLogStatistics els = new EventLogStatistics();
			            els.setAppId(appId);
			            basicDBObject = (BasicDBObject) dbList.get(String.valueOf(s));
			            els.setEventKey(basicDBObject.get("eventKey")+"");
						els.setCount((long)(double)(basicDBObject.get("num"))+"");
						els.setCreateTime(toYearMonth);
						eventLogStatistics.add(els);
			    }
			}
		}
		
		return eventLogStatistics;
	}
	/**
	 * 根据操作的名称进行查询
	 */
	public EventLogCondition selectEventLogByLoginName(EventLogCondition eventLogCondition){
		Query query = new Query();
		//获取所有的数据库
		Set<String> names = mongoTemplate.getCollectionNames();
		//获取数据库的名称
		String dbName=DB_PREFIX+eventLogCondition.getAppId()+"_"+eventLogCondition.getDbNameTime();
		String dbTempName="";
		boolean isDbExit = false;
		for (String name : names) {
			dbTempName=name;
			if(name.equals(dbName)){
				isDbExit=true;
				break;
			}
		}
		if(StringUtils.isBlank(eventLogCondition.getAppId())){
			dbName=dbTempName;
		}else if(!isDbExit&&!StringUtils.isBlank(eventLogCondition.getAppId())){
			List<EventLogBo> find = new ArrayList<>();
			eventLogCondition.setResult(find);
			eventLogCondition.setTotalCount(0L);
			return eventLogCondition;
		}
		Criteria criteria = new Criteria();
		if(!StringUtils.isBlank(eventLogCondition.getUserId())){
			criteria=Criteria.where("userId").in(eventLogCondition.getUserId());
		}
		if(eventLogCondition.getBeginTime()!=null||eventLogCondition.getEndTime()!=null){
			if(eventLogCondition.getBeginTime()!=null&&eventLogCondition.getEndTime()!=null){
				//联合查询时间
				criteria.andOperator(Criteria.where("createTime").gte(eventLogCondition.getBeginTimeStr()),
						Criteria.where("createTime").lte(eventLogCondition.getEndTimeStr())		
						);
			}else if(eventLogCondition.getBeginTime()!=null){
				//开始时间
				criteria.andOperator(Criteria.where("createTime").gte(eventLogCondition.getBeginTimeStr()));
			}else if(eventLogCondition.getEndTime()!=null){
				//结束时间
				criteria.andOperator(Criteria.where("createTime").lte(eventLogCondition.getEndTimeStr()));
			}
		}
		//是否分页.
		if(eventLogCondition.getPageNumber()!=null&&eventLogCondition.getPageSize()>0){
			query.skip(eventLogCondition.getPageNumber()*(eventLogCondition.getPageNumber()-1));
			query.limit(eventLogCondition.getPageSize());
		}
		query.addCriteria(criteria);
		long count = mongoTemplate.count(query,EventLogBo.class,dbName);
		List<EventLogBo> find = mongoTemplate.find(query,EventLogBo.class,dbName);
		eventLogCondition.setResult(find);
		eventLogCondition.setTotalCount(count);
		return eventLogCondition;
	}
	/**
	 * 获取数据库的名称
	 */
	private String getDbName(String appId){
		return DB_PREFIX+appId+"_"+DateUtil.getYearMonth();
	}
}
