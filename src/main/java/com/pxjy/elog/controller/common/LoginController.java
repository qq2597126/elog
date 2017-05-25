package com.pxjy.elog.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pxjy.common.controller.BaseController;
@Controller
@RequestMapping("login")
public class LoginController extends BaseController{
	@RequestMapping("login")
	public String login(){
		return "/appInfo/list";
	}
}
