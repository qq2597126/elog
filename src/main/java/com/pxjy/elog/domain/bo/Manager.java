package com.pxjy.elog.domain.bo;


/**
 * 管理员
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class Manager{
	
	private String id;
	/**
	 * 教师姓名
	 */
	private String name;
	
	/**
	 * 学校编号
	 */
	private String schoolCode;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * LOGO
	 */
	private String logo;
	
	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 角色ID
	 */
	private String roleId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getCode() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
