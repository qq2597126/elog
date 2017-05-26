package com.pxjy.elog.controller.action;

import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pxjy.common.controller.BaseController;
import com.pxjy.common.lang.StringUtil;
import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.bo.EventLogBo;
import com.pxjy.elog.domain.param.AppInfoParam;
import com.pxjy.elog.service.IAppInfoService;

/**
 * APP信息控制器
 * @author cg
 * @date 2017-05-24
 */
@Controller
@RequestMapping("/admin/appInfo")
public class AppInfoController extends BaseController {
	private static String RETURN_SUCCESS_STATUS="1";
	private static String RETURN_DEFAULT_STATUS="0";
	@Autowired
	private IAppInfoService appInfoService;

	/**
	 * 进入APP信息列表
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	 @RequestMapping(value="/onList", method=RequestMethod.GET)
	 public String onAppInfoList() {
		return "appInfo/list";
	}

	/**
	 * 查询APP信息列表
	 * @param request
	 * @param response void
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/list4ajax")
	public void findAppInfoList4Ajax(HttpServletRequest request, HttpServletResponse response) {
		AppInfoParam param = new AppInfoParam();
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
		
		IPage<AppInfoBo> pageList = appInfoService.findListByPage(param);
				
		writeResponse4Ajax(pageList, response);
	}
	/**
	 * 根据APPID进行查询
	 */
	@RequestMapping("/getAppinfoByAppId")
	@ResponseBody
	public Map<String,Object> getAppInfoByAppId(String appId){
		String status=RETURN_DEFAULT_STATUS;
		String errorMessage="";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isBlank(appId)){
			errorMessage="缺少参数appId";
		}else{
			//数据解析
			AppInfoBo appInfoBo = new AppInfoBo();
			appInfoBo.setAppId(appId);
			AppInfoBo appinfo = appInfoService.findAppinfoByAppId(appInfoBo);
			resultMap.put("data",appinfo);
			status=RETURN_SUCCESS_STATUS;
		}
		resultMap.put("errorMessage",errorMessage);
		resultMap.put("status",status);
		return resultMap;
	}
	/**
	 * 进入添加APP信息页面
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddAppInfo() {
		return "appInfo/add";
	}
	
	/**
	 * 保存APP信息信息
	 * @param schoolBo
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddAppInfo(AppInfoBo appInfoBo) {
		//设置创建人的信息
		appInfoBo.setCreateUser(8175);
		if(appInfoBo.getSendType().equals(0)){
			appInfoBo.setSendTime(0L);
		}
		if(appInfoBo.getSendTime()<0){
			appInfoBo.setSendTime(0L);
		}
		appInfoService.doAddAppInfo(appInfoBo);
		return "redirect:/admin/appInfo/onList";
	}

	/**
	 * 进入APP信息编辑页面
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/onEdit")
	public String onEditAppInfo(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

		int id = StringUtil.toInt(idStr);
		
		AppInfoBo appInfoBo = appInfoService.findAppInfoById(id);
		request.setAttribute("appInfo", appInfoBo);
		
		return "appInfo/edit";
	}
	
	/**
	 * 编辑APP信息信息
	 * @param appInfoBo
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditAppInfo(AppInfoBo appInfoBo, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		if(appInfoBo.getSendType().equals(0)){
			appInfoBo.setSendTime(0L);
		}
		if(appInfoBo.getSendTime()<0){
			appInfoBo.setSendTime(0L);
		}
		appInfoService.doEditAppInfo(appInfoBo);
		
		return "appInfo/list";
	}
	
	/**
	 * 删除APP信息信息
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/doDel", method=RequestMethod.POST)
	public String doDelAppInfo(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

        String appId = request.getParameter("appId");
        request.setAttribute("appId", appId);
		
		int id = StringUtil.toInt(idStr);
		
		appInfoService.doDelAppInfo(id);
		
		return "appInfo/list";
	}

}