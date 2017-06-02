package com.pxjy.elog.controller.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pxjy.common.constant.Property;
import com.pxjy.common.controller.BaseController;
import com.pxjy.elog.domain.bo.Manager;
import com.pxjy.thrift.client.ThriftRPCClient;
import com.pxjy.thrift.service.CheckLoginRequest;
import com.pxjy.thrift.service.CheckLoginResponse;
import com.pxjy.thrift.service.UCManager;
@Controller
@RequestMapping("login")
public class LoginController extends BaseController{
	private String loginName;
	private String schoolCode;
	private String subId;
	private String s;
	private int reTryNum = 0;
	
	ThriftRPCClient thriftRPCClient;
	@RequestMapping("login")
	public String login(HttpServletRequest httpRequest) throws IOException{
		if (StringUtils.isNotEmpty(loginName) && StringUtils.isNotEmpty(schoolCode) && StringUtils.isNotEmpty(subId)) {
			CheckLoginRequest request = new CheckLoginRequest();
			request.setLoginName(loginName);
			request.setSchoolCode(schoolCode);
			request.setSubId(subId);
			request.setS(s);
			CheckLoginResponse response = thriftRPCClient.checkUserAuthority(request);
			if(response == null){
				httpRequest.setAttribute("message", "系统繁忙");
				return "errorInfo/loginError";
			}else if("1".equals(response.getStatus())){
				UCManager manager = response.getManager();
				Manager user = new Manager();
				user.setId(manager.getManagerId());
				user.setRoleId(manager.getRoleId());
				user.setSchoolCode(schoolCode);
				user.setName(manager.getManagerName());
				user.setLogo(manager.getLogo());
				user.setEmail(manager.getManagerCode());
				user.setLoginName(manager.getLoginName());
		        
		        if(!user.getLoginName().equals(loginName)){
		        	if(reTryNum < 1){
			        	reTryNum++;
			        	System.out.println("服务器访问参数：");
					    System.out.println("loginName：" + this.loginName);
					    System.out.println("schoolCode：" + this.schoolCode);
					    System.out.println("subId：" + this.subId);
				        System.out.println("服务器返回参数：");
				        System.out.println("user.loginName：" + user.getLoginName());
				        System.out.println("user.name：" + user.getName());
				        System.out.println("获取用户失败，正在重试：第" + reTryNum + "次");
		        		return login(httpRequest);
		        	}else {
		        		httpRequest.setAttribute("message", "系统繁忙");
						return "errorInfo/loginError";
		        	}
		        }
		        httpRequest.getSession().setAttribute("manager", user);
				return "redirect:/login/gotoMain";
			}else {
				httpRequest.setAttribute("message", response.getMessage());
				return "errorInfo/loginError";
			}
		} else if(Property.BASE.indexOf("localhost") != -1 || Property.BASE.indexOf("192.168.1") != -1){
			return debugLogin(httpRequest);
		}else {
			return "redirect:"+Property.PASSPORT_URL;
		}
	}
	
	private String debugLogin(HttpServletRequest httpRequest){
		Manager user = new Manager();
		user.setId("8175");
		user.setSchoolCode("02");
		user.setName("zj_tj");
		user.setLogo("http://a.pxjy.com/resources/uc/attachment/images/default.png");
		user.setRoleId("2");
		user.setLoginName("zj_tj");
		user.setEmail("zj_tj@pxjy.com");
		httpRequest.getSession().setAttribute("manager", user);
		@SuppressWarnings("unused")
		Manager manager = user;
		return "redirect:/login/gotoMain";
	}
	@RequestMapping("gotoMain" )
	public String gotoMian(HttpServletRequest request){
		Manager manager = (Manager) ((HttpServletRequest)request).getSession().getAttribute("manager");
		if (manager == null) {
			return "redirect:"+Property.PASSPORT_URL;
		}
		return "main";
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public ThriftRPCClient getThriftRPCClient() {
		return thriftRPCClient;
	}

	public void setThriftRPCClient(ThriftRPCClient thriftRPCClient) {
		this.thriftRPCClient = thriftRPCClient;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
}
