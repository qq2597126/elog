package com.pxjy.elog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxjy.common.api.ApiResult;
import com.pxjy.common.api.ApiResultWapper;
import com.pxjy.common.api.exception.ErrorCode;
import com.pxjy.common.controller.BaseController;
import com.pxjy.common.paginator.IPage;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.domain.param.AppInfoParam;
import com.pxjy.elog.service.IAppInfoService;

/**
 * APP信息 API控制器
 * @author cg
 * @date 2017-05-24
 */
@Controller
@RequestMapping("/api/appInfo")
public class AppInfoApiController extends BaseController {
	
	@Autowired
	private IAppInfoService appInfoService;

	/**
	 * 查询APP信息列表
	 * @param param
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findList(AppInfoParam param) {
		IPage<AppInfoBo> pageList = appInfoService.findListByPage(param);
		return ApiResultWapper.getInstance(pageList);
	}

	/**
	 * 查询APP信息详情
	 * @param id
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findInfo(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}
		AppInfoBo appInfoBo = appInfoService.findAppInfoById(id);
		if (appInfoBo == null) {
			ErrorCode.PARAMS_ERROR.issue("id");
		}
		return ApiResultWapper.getInstance(appInfoBo);
	}
	
	/**
	 * 保存APP信息信息
	 * @param appInfoBo
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doAddAppInfo(AppInfoBo appInfoBo) {
		appInfoService.doAddAppInfo(appInfoBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 编辑APP信息信息
	 * @param appInfoBo
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doEditAppInfo(AppInfoBo appInfoBo) {
		appInfoService.doEditAppInfo(appInfoBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 删除APP信息信息
	 * @param Integer
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-24
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doDelAppInfo(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}	
		appInfoService.doDelAppInfo(id);
		return ApiResultWapper.getVoidInstance();
	}

}