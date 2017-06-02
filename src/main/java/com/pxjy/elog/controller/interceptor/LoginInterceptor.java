package com.pxjy.elog.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pxjy.common.constant.Property;
import com.pxjy.elog.domain.bo.Manager;


public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//不拦截登录相关
		String nameSpaceStr = getRequestActionNameSpace(request);
		if (nameSpaceStr.equalsIgnoreCase("") 
				|| nameSpaceStr.equalsIgnoreCase("/") 
				|| nameSpaceStr.contains("/login")) {
			return true;
		}else{
			Manager manager = (Manager) ((HttpServletRequest)request).getSession().getAttribute("manager");
			if (manager == null) {
				response.sendRedirect(Property.PASSPORT_URL);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	private String getRequestActionNameSpace(HttpServletRequest request) {
		String absoluteUrl = request.getRequestURI().toString();
		String nameSpaceStr = absoluteUrl.substring(request.getContextPath().length(), absoluteUrl.lastIndexOf("/"));
		return nameSpaceStr;
	}
}
