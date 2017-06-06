package com.pxjy.elog.controller.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxjy.common.controller.BaseController;
import com.pxjy.common.controller.DtGridResponse;
import com.pxjy.common.lang.DateUtil;
import com.pxjy.common.lang.StringUtil;
import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.bo.EventLogCondition;
import com.pxjy.elog.domain.bo.EventLogStatistics;
import com.pxjy.elog.domain.param.EventInfoParam;
import com.pxjy.elog.service.IEventLogService;

@Controller
@RequestMapping("/admin/eventlog")
public class EventLogController extends BaseController{
	@Autowired
	private IEventLogService eventLogService;
	
	//查询
	@RequestMapping("/getStatisticsLog")
	public void getEventLog(HttpServletRequest request,HttpServletResponse response){
		List<EventLogStatistics> eventLogStatistics = eventLogService.getEventLogStatistics();
		/*DtGridResponse pager = new DtGridResponse();
		pager.setExhibitDatas(eventLogStatistics);
		pager.setNowPage(1);
		pager.setPageCount(eventLogStatistics.size());
		pager.setRecordCount(Integer.valueOf(String.valueOf(pageList.getTotalElements())));
		pager.setPageSize(eventLogStatistics.size());
		pager.setIsSuccess(true);
		
		Map<String,Object> hashMap =  new HashMap<String,Object>();
		hashMap.put("exhibitDatas",eventLogStatistics);
		hashMap.put("","");*/
		
		try {
	        response.setContentType("text/html; charset=utf-8");
	        OutputStream stream = response.getOutputStream();
	        
	        DtGridResponse pager = new DtGridResponse();
	        pager.setExhibitDatas(eventLogStatistics);
			pager.setNowPage(1);
			pager.setPageCount(eventLogStatistics.size());
			pager.setRecordCount(eventLogStatistics.size());
			pager.setPageSize(eventLogStatistics.size());
			pager.setIsSuccess(true);
	        
	        stream.write(JSON.toJSONString(pager).getBytes("utf-8"));
	        stream.flush();
	        stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/onStatistics")
	public  String onStatistics(){
		return "eventLog/statistics";
	}
	@RequestMapping("/onElogList")
	public String elog(){
		return "eventLog/elogList";
	}
	@RequestMapping("/getLog")
	public void eventLog(EventLogCondition eventLogCondition,HttpServletRequest request,HttpServletResponse response){
		String pageNumber 	= null;
		String pageSize 	= null;
		
		String dtGridPager = request.getParameter("dtGridPager");
		if(StringUtil.isNotEmpty(dtGridPager)) {
			String pageSizeStr 	= JSON.parseObject(dtGridPager).getString("pageSize");
			String pageNoStr 	= JSON.parseObject(dtGridPager).getString("nowPage");
			String parameters 	= JSON.parseObject(dtGridPager).getString("parameters");

            // 用于保持当前页
			pageNumber 		= JSONObject.parseObject(parameters).getString("nowPage");
			pageNumber 		= StringUtil.isEmpty(pageNumber) ? pageNoStr : pageNumber;
			pageSize 		= JSONObject.parseObject(parameters).getString("pageSize");
			pageSize 		= StringUtil.isEmpty(pageSize) ? pageSizeStr : pageSize;
			//对象数据的初始化
			
			String userId  = JSON.parseObject(parameters).getString("userId");
			String appId  = JSON.parseObject(parameters).getString("appId");
			if(StringUtils.isBlank(userId)){
				userId=null;
			}
			if(StringUtils.isBlank(appId)){
				appId=null;
			}
			Date beginTime = JSON.parseObject(parameters).getDate("beginTime");
			Date endTime   = JSON.parseObject(parameters).getDate("endTime");
			
			eventLogCondition.setAppId(appId);
			eventLogCondition.setUserId(userId);
			eventLogCondition.setBeginTime(beginTime);
			eventLogCondition.setEndTime(endTime);
		}
		
		eventLogCondition.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		eventLogCondition.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		if(eventLogCondition.getBeginTime()!=null){
			eventLogCondition.setDbNameTime(DateUtil.getYearMonth(eventLogCondition.getBeginTime()));
		}else{
			eventLogCondition.setDbNameTime(DateUtil.getYearMonth());
		}
		EventLogCondition eventLogList = eventLogService.getEventLogList(eventLogCondition);
		
		try {
	        response.setContentType("text/html; charset=utf-8");
	        OutputStream stream = response.getOutputStream();
	        
	        DtGridResponse pager = new DtGridResponse();
			pager.setExhibitDatas(eventLogList.getResult());
			pager.setNowPage(eventLogList.getPageNumber());
			pager.setPageCount(eventLogList.getTotalPages());
			pager.setRecordCount(Integer.valueOf(String.valueOf(eventLogList.getResult().size())));
			pager.setPageSize(eventLogList.getPageSize());
			pager.setIsSuccess(true);
	        
	        stream.write(JSON.toJSONString(pager).getBytes("utf-8"));
	        stream.flush();
	        stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
