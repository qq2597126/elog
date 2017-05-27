package com.pxjy.common.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pxjy.common.interceptor.SignInterceptor;
import com.pxjy.elog.domain.bo.AppInfoBo;
import com.pxjy.elog.service.IAppInfoService;

/**
 * 初始化监听器，可以初始化一些全局性的对象或数据
 * 
 * @author wjw
 *
 */
public class WebInitListener implements ServletContextListener {
	
	/**
	 *  全局springContext
	 */
	private static WebApplicationContext springContext = null;
	
	/**
	 *  获取当前web运行于容器下的根目录
	 * @return
	 */
	public static String getRootRealPath() {
		String rootRealPath ="";
		try {
			rootRealPath = springContext.getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			System.out.println("获取系统根目录失败");
		}
		return rootRealPath;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent
				.getServletContext());
		
		// 初始化AppInfoMap
		IAppInfoService appInfoService = (IAppInfoService)springContext.getBean("appInfoService");
		List<AppInfoBo> findAll = appInfoService.findAll();
		
		for (AppInfoBo appInfoBo : findAll) {
			//初始化印签
			SignInterceptor.appKey.put(appInfoBo.getAppId(),appInfoBo.getAppKey());
		}
		System.out.println(getRootRealPath());
	}

	public static WebApplicationContext getSpringContext() {
		return springContext;
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}
