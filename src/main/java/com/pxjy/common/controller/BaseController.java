package com.pxjy.common.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pxjy.common.paginator.IPage;

/**
 * 基础控制器类
 * @author Bobbie.Qi
 *
 */
@Controller
@RequestMapping("/base/controller")
public class BaseController {
	/**
	 * 获取客户端IP
	 * @param request
	 * @return String
	 * @author: Bobbie.Qi
	 * @time:2017年5月1日
	 */
	public String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
		
	/**
	 * out put for dtgrid
	 * @param pageList
	 * @param response void
	 * @author: Bobbie.Qi
	 * @time:2017年5月3日
	 */
	public void writeResponse4Ajax(IPage<?> pageList, HttpServletResponse response) {
		try {
	        response.setContentType("text/html; charset=utf-8");
	        OutputStream stream = response.getOutputStream();
	        
	        DtGridResponse pager = new DtGridResponse();
			pager.setExhibitDatas(pageList.getResult());
			pager.setNowPage(pageList.getPageNumber());
			pager.setPageCount(pageList.getTotalPages());
			pager.setRecordCount(Integer.valueOf(String.valueOf(pageList.getTotalElements())));
			pager.setPageSize(pageList.getPageSize());
			pager.setIsSuccess(true);
	        
	        stream.write(JSON.toJSONString(pager).getBytes("utf-8"));
	        stream.flush();
	        stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
