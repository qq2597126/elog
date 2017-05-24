package com.pxjy.common.domain;

import java.io.Serializable;

/**
 * 与数据库表有对应关系的Model类的基类
 * 
 * @author Bobbie.Qi
 *
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 6437114463749744698L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
