package com.pxjy.elog.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class WebInitListener implements ServletContextListener {
	private Logger logger = LoggerFactory.getLogger(WebInitListener.class);
	
	public static WebApplicationContext springContext = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("====================================");
		logger.info("==      Puxin Education Group     ==");
		logger.info("==     	elog System	       ==");
		logger.info("====================================");
		
		springContext = WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
