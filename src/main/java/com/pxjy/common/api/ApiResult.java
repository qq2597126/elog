package com.pxjy.common.api;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.pxjy.common.paginator.IPage;
import com.pxjy.common.paginator.Page;

public class ApiResult extends ApiError implements Serializable {
	
	private static final long serialVersionUID = 7370805650212491762L;
	
	private Object data;
	
	private Boolean isVoid;
	
	public ApiResult() {
	}

	public ApiResult(Object data) {
		this.data = data;
	}
	
	public <T> T getResultObject(Class<T> clazz){
		return JSON.parseObject(data.toString(), clazz);
	}
	
	public <T> List<T> getResultArray(Class<T> clazz){
		return JSON.parseArray(data.toString(), clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable> IPage<T> getResultPage(Class<T> clazz){
		Page<T> page = JSON.parseObject(data.toString(), Page.class);
		List<T> list = JSON.parseArray(page.getResult().toString(), clazz);
		page.setResult(list);
		return page;
	}
	
	public ApiResult(Boolean isVoid) {
		this.isVoid = isVoid;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getIsVoid() {
		return isVoid;
	}

	public void setIsVoid(Boolean isVoid) {
		this.isVoid = isVoid;
	}
}
