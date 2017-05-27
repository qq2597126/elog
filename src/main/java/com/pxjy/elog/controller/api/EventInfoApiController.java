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
import com.pxjy.elog.domain.bo.EventInfoBo;
import com.pxjy.elog.domain.param.EventInfoParam;
import com.pxjy.elog.service.IEventInfoService;

/**
 * 事件信息 API控制器
 * @author cg
 * @date 2017-05-25
 */
@Controller
@RequestMapping("/api/eventInfo")
public class EventInfoApiController extends BaseController {
	
	@Autowired
	private IEventInfoService eventInfoService;

	/**
	 * 查询事件信息列表
	 * @param param
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findList(EventInfoParam param) {
		IPage<EventInfoBo> pageList = eventInfoService.findListByPage(param);
		return ApiResultWapper.getInstance(pageList);
	}

	/**
	 * 查询事件信息详情
	 * @param id
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findInfo(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}
		EventInfoBo eventInfoBo = eventInfoService.findEventInfoById(id);
		if (eventInfoBo == null) {
			ErrorCode.PARAMS_ERROR.issue("id");
		}
		return ApiResultWapper.getInstance(eventInfoBo);
	}
	
	/**
	 * 保存事件信息信息
	 * @param eventInfoBo
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doAddEventInfo(EventInfoBo eventInfoBo) {
		eventInfoService.doAddEventInfo(eventInfoBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 编辑事件信息信息
	 * @param eventInfoBo
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doEditEventInfo(EventInfoBo eventInfoBo) {
		eventInfoService.doEditEventInfo(eventInfoBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 删除事件信息信息
	 * @param Integer
	 * @return ApiResult
	 * @author: cg
	 * @time: 2017-05-25
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doDelEventInfo(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}	
		eventInfoService.doDelEventInfo(id);
		return ApiResultWapper.getVoidInstance();
	}

}