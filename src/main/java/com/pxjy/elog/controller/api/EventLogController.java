package com.pxjy.elog.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pxjy.common.lang.DateUtil;
import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.service.IEventLogService;

@Controller
@RequestMapping("/admin/eventLog")
public class EventLogController {
	private static String RETURN_SUCCESS_STATUS="1";
	private static String RETURN_DEFAULT_STATUS="0";
	@Autowired
	private IEventLogService eventLogService;
	@RequestMapping(value="/addEventLogs",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addEventLogs(EventLogBo eventLogBo,String appId,String data){
		String status=RETURN_DEFAULT_STATUS;
		String errorMessage="";
		Map<String,String> resultMap = new HashMap<String,String>();
		if(StringUtils.isBlank(appId)){
			errorMessage="缺少参数appId";
		}else{
			//数据解析
			
			if(!StringUtils.isBlank(data)){
				List<EventLogBo> eventLogs = new ArrayList<>();
				JSONArray parseArray = JSON.parseArray(data);
				//解析数据
				Object[] array = parseArray.toArray();
				//写入数据
				if(array.length>0){
					for (Object object : array) {
						EventLogBo elb = new EventLogBo();
						@SuppressWarnings( "rawtypes")
						Map map = (Map) object;
						try{
							Long createTime = (Long) map.get("createTime");
							elb.setCreateTime(DateUtil.getYearMonthDay(new Date(createTime),DateUtil.FULL_FORMATER));
						}catch (Exception e) {
							elb.setCreateTime(DateUtil.getYearMonthDay(new Date(),DateUtil.FULL_FORMATER));
							System.out.println(e.getMessage());
						}
						elb.setUserId(map.get("userId").toString());
						
						elb.setEventKey(eventLogBo.getEventKey());
						elb.setAppBuild(eventLogBo.getAppBuild());
						elb.setOsVersion(eventLogBo.getOsVersion());
						//数据组合
						eventLogs.add(elb);
					}
					eventLogService.mongoAddEventLog(eventLogs, appId);
					status=RETURN_SUCCESS_STATUS;
				}
			}else{
				status=RETURN_SUCCESS_STATUS;
			}
		}
		resultMap.put("errorMessage",errorMessage);
		resultMap.put("status",status);
		return resultMap;
	}

	public IEventLogService getEventLogService() {
		return eventLogService;
	}

	public void setEventLogService(IEventLogService eventLogService) {
		this.eventLogService = eventLogService;
	}
	
}
