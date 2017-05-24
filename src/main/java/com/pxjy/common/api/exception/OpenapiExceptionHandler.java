package com.pxjy.common.api.exception;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.pxjy.common.api.ApiError;
import com.pxjy.common.lang.WebUtil;

public class OpenapiExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof OpenapiException) {
			OpenapiException exception = (OpenapiException) ex;
			ApiError error = new ApiError(0, exception.getDesc(), String.valueOf(exception.getNum()));
			try {
				WebUtil.outPutJsonResult(response, Charset.defaultCharset(), error);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	} 

}
