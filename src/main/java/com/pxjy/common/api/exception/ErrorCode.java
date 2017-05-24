package com.pxjy.common.api.exception;

public interface ErrorCode extends SystemErrorCode {
	/**
	 * 常用名词
	 * 
	 * ILLEGAL : 错误、非法（可预料）
	 * ERROR : 错误、异常（不可预料）
	 * UNABLE : 不可用（无能力的；不能胜任的）
	 * FAILED : 失败了的，不成功的
	 * LIMIT : 限制
	 */
	
	/** 验证码错误 */
	public static final ErrorInfo ILLEGAL_AUTH_VERIFYCODE 		= ErrorInfo.getInstance(30001, "ILLEGAL_AUTH_VERIFYCODE", "验证码错误");
}
