package com.pxjy.elog.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxjy.common.controller.BaseController;
import com.pxjy.common.lang.StringUtil;
import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.AppInfoBo;
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
	@RequestMapping(value="/onEdit", method=RequestMethod.POST)
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