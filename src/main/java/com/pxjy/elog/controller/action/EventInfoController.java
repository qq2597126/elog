package com.pxjy.elog.controller.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxjy.common.controller.BaseController;
import com.pxjy.common.lang.StringUtil;
import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.EventInfoParam;
import com.pxjy.elog.service.IEventInfoService;

/**
 * 事件信息控制器
 * @author cg
 * @date 2017-05-25
 */
@Controller
@RequestMapping("/admin/eventInfo")
public class EventInfoController extends BaseController {
	private static String RETURN_SUCCESS_STATUS="1";
	private static String RETURN_DEFAULT_STATUS="0";
	@Autowired
	private IEventInfoService eventInfoService;

	/**
	 * 进入事件信息列表
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	 @RequestMapping(value="/onList", method=RequestMethod.GET)
	 public String onEventInfoList() {
		return "eventInfo/list";
	}

	/**
	 * 查询事件信息列表
	 * @param request
	 * @param response void
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/list4ajax")
	public void findEventInfoList4Ajax(HttpServletRequest request, HttpServletResponse response) {
		EventInfoParam param = new EventInfoParam();
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

            String appId = JSON.parseObject(parameters).getString("appId");

            if(StringUtil.isNotEmpty(appId)) {
				param.setAppId(appId);
			}
		}
		
		param.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		param.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		
		IPage<EventInfoBo> pageList = eventInfoService.findListByPage(param);
				
		writeResponse4Ajax(pageList, response);
	}

	/**
	 * 进入添加事件信息页面
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddEventInfo() {
		return "eventInfo/add";
	}
	
	/**
	 * 保存事件信息信息
	 * @param schoolBo
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddEventInfo(EventInfoBo eventInfoBo) {
		eventInfoService.doAddEventInfo(eventInfoBo);
		return "redirect:/admin/eventInfo/onList";
	}

	/**
	 * 进入事件信息编辑页面
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/onEdit")
	public String onEditEventInfo(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

		int id = StringUtil.toInt(idStr);
		
		EventInfoBo eventInfoBo = eventInfoService.findEventInfoById(id);
		request.setAttribute("eventInfo", eventInfoBo);
		
		return "eventInfo/edit";
	}
	
	/**
	 * 编辑事件信息信息
	 * @param eventInfoBo
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditEventInfo(EventInfoBo eventInfoBo, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		
		eventInfoService.doEditEventInfo(eventInfoBo);
		
		return "eventInfo/list";
	}
	
	/**
	 * 删除事件信息信息
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/doDel", method=RequestMethod.POST)
	public String doDelEventInfo(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

        String appId = request.getParameter("appId");
        request.setAttribute("appId", appId);
		
		int id = StringUtil.toInt(idStr);
		
		eventInfoService.doDelEventInfo(id);
		
		return "eventInfo/list";
	}
	/**
	 * 根据APPID进行获取事件信息
	 */
	@RequestMapping("/getEventInfoByAppId")
	@ResponseBody
	public Map<String,Object> getEventInfoByAppId(String appId){
		String status=RETURN_DEFAULT_STATUS;
		String errorMessage="";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//查询所有的
		if(StringUtils.isBlank(appId)){
			errorMessage="缺少参数appId";
		}else{
			EventInfoBo eventInfoBo = new EventInfoBo();
			eventInfoBo.setAppId(appId);
			
			List<EventInfoBo> byAppId = eventInfoService.findEventInfoByAppId(eventInfoBo);
			status=RETURN_SUCCESS_STATUS;
			resultMap.put("data",byAppId);
		}
		resultMap.put("errorMessage",errorMessage);
		resultMap.put("status",status);
		return resultMap;
	}

}