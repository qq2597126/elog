package com.pxjy.elog.domain.bo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.pxjy.common.lang.DateUtil;

public class EventLogCondition{
	private String userId;
	//yyyy-mm-dd HH:mm:ss
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	//yyyymm
	private String dbNameTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private String appId;
	//分页相关
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalCount;
	private List<EventLogBo> result;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<EventLogBo> getResult() {
		return result;
	}

	public void setResult(List<EventLogBo> result) {
		this.result = result;
	}
	
	public String getDbNameTime() {
		return dbNameTime;
	}

	public void setDbNameTime(String dbNameTime) {
		this.dbNameTime = dbNameTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBeginTimeStr(){
		if(beginTime!=null){
			return DateUtil.getYearMonthDay(beginTime,DateUtil.FULL_FORMATER);
		}
		return null;
	}
	public String getEndTimeStr(){
		if(endTime!=null){
			return DateUtil.getYearMonthDay(endTime,DateUtil.FULL_FORMATER);
		}
		return null;
	}
	/**
	 * 总页数
	 */
	public int getTotalPages(){
		if(totalCount!=null&&pageSize!=null){
			return totalCount%pageSize==0?(int)(totalCount/pageSize):(int)(totalCount/pageSize+1);
		}
		return 1;
	}
}
