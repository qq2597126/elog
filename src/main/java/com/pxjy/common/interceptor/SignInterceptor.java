package com.pxjy.common.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxjy.common.constant.Property;
import com.pxjy.common.lang.StringUtil;
import com.pxjy.elog.service.IAppInfoService;

public class SignInterceptor implements HandlerInterceptor {

	private static Log log = LogFactory.getLog(SignInterceptor.class);
	public static final String SIGN = "sign";
	public static final String APP_ID = "appid";

	@Autowired
	private IAppInfoService appInfoService;
	
	
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;
		//验签逻辑
		if(Property.IS_VERIFY_SIGN) {
			if (!checkSign(request)) {
				response.setContentType("text/html; charset=utf-8");  
	            PrintWriter out = response.getWriter();
	            Map<String, String> result = new HashMap<String, String>();
	            result.put("state", "0");
	            result.put("data", "");
	            result.put("msg", "验签失败");
	            out.write(JSON.toJSONString(result));
	            out.flush();
	            out.close();
	            flag = false;
			}
		}
		return flag;
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
	 * 它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
	 * ，也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用
	 * ，这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法
	 * ，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前
	 * ，要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkSign(ServletRequest request) {
		writeLog(request);
		List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
		if(!keys.contains(SIGN) || !keys.contains(APP_ID)) {
			return false;
		}
		Collections.sort(keys, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
			}
		});
		StringBuffer sb = new StringBuffer();
		String paramSign = request.getParameter(SIGN);
		for (String key : keys){
			if(SIGN.equals(key)){
				continue;
			}
			if(request.getParameter(key) instanceof String && !(key.equals("file"))){
				sb.append(key.toLowerCase() + "=");
				sb.append(request.getParameter(key) + "&");
			}
		}
		sb.append("appkey="+getAppKey(request.getParameter(APP_ID)));
		String mySign = StringUtil.getMD5String(sb.toString());
		return mySign.equals(paramSign);
	}
	
	private String getAppKey(String appId) {
		
		return null;
	}

	/**
	 * 写入日志（接口与请求参数）
	 * @param request void
	 * @author: Bobbie.Qi
	 * @time:2017年4月17日
	 */
	private static void writeLog(ServletRequest request) {
		HttpServletRequest hsRequest = (HttpServletRequest)request;
		log.info(String.format("### ip [%s] contextPath [%s] uri [%s] params [%s] ###", hsRequest.getRemoteAddr(), hsRequest.getContextPath(), hsRequest.getRequestURI(), JSONObject.toJSONString(hsRequest.getParameterMap())));
	}
}
