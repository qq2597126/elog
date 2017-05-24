package com.pxjy.common.constant;

public class Property {
	/**
	 * 系统 web 路径 (eg: http://www.mysystem.com )
	 */
	public static String BASE;

	/** request字符集 */
	public static String CHARSET;

	/** 公司名称 */
	public static String COMP_NAME;

	/**
	 * 系统名称
	 */
	public static String SYSTEM_NAME;
	
	/**
	 * 文件上传物理根目录
	 */
	public static String FILE_UPLOAD_ROOTPATH;
	
	/**
	 * 文件访问地址
	 */
	public static String FILE_UPLOAD_ROOTURL;
	
	/**
	 * 图片存放的子目录
	 */
	public static String FILE_IMAG_UPLOADPATH;
	
	/**
	 * 允许图片上传的最大占用空间，单位：兆（M）
	 */
	public static String FILE_IMAG_MAXSIZE;
	
	/**
	 * 允许图片上传的文件格式 多种格式之间逗号分隔
	 */
	public static String FILE_IMAG_TYPES;
		
	/**
	 * 默认头像地址
	 */
	public static String DEFAULT_MANAGER_LOGO;
	
	/**
	 * 子系统默认图标
	 */
	public static String DEFAULT_SYSTEM_ICON;

	public static String getBASE() {
		return BASE;
	}

	public static void setBASE(String bASE) {
		BASE = bASE;
	}

	public static String getCHARSET() {
		return CHARSET;
	}

	public static void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}

	public static String getCOMP_NAME() {
		return COMP_NAME;
	}

	public static void setCOMP_NAME(String cOMP_NAME) {
		COMP_NAME = cOMP_NAME;
	}

	public static String getSYSTEM_NAME() {
		return SYSTEM_NAME;
	}

	public static void setSYSTEM_NAME(String sYSTEM_NAME) {
		SYSTEM_NAME = sYSTEM_NAME;
	}

	public static String getFILE_UPLOAD_ROOTPATH() {
		return FILE_UPLOAD_ROOTPATH;
	}

	public static void setFILE_UPLOAD_ROOTPATH(String fILE_UPLOAD_ROOTPATH) {
		FILE_UPLOAD_ROOTPATH = fILE_UPLOAD_ROOTPATH;
	}

	public static String getFILE_UPLOAD_ROOTURL() {
		return FILE_UPLOAD_ROOTURL;
	}

	public static void setFILE_UPLOAD_ROOTURL(String fILE_UPLOAD_ROOTURL) {
		FILE_UPLOAD_ROOTURL = fILE_UPLOAD_ROOTURL;
	}

	public static String getFILE_IMAG_UPLOADPATH() {
		return FILE_IMAG_UPLOADPATH;
	}

	public static void setFILE_IMAG_UPLOADPATH(String fILE_IMAG_UPLOADPATH) {
		FILE_IMAG_UPLOADPATH = fILE_IMAG_UPLOADPATH;
	}

	public static String getFILE_IMAG_MAXSIZE() {
		return FILE_IMAG_MAXSIZE;
	}

	public static void setFILE_IMAG_MAXSIZE(String fILE_IMAG_MAXSIZE) {
		FILE_IMAG_MAXSIZE = fILE_IMAG_MAXSIZE;
	}

	public static String getFILE_IMAG_TYPES() {
		return FILE_IMAG_TYPES;
	}

	public static void setFILE_IMAG_TYPES(String fILE_IMAG_TYPES) {
		FILE_IMAG_TYPES = fILE_IMAG_TYPES;
	}

	public static String getDEFAULT_MANAGER_LOGO() {
		return DEFAULT_MANAGER_LOGO;
	}

	public static void setDEFAULT_MANAGER_LOGO(String dEFAULT_MANAGER_LOGO) {
		DEFAULT_MANAGER_LOGO = dEFAULT_MANAGER_LOGO;
	}

	public static String getDEFAULT_SYSTEM_ICON() {
		return DEFAULT_SYSTEM_ICON;
	}

	public static void setDEFAULT_SYSTEM_ICON(String dEFAULT_SYSTEM_ICON) {
		DEFAULT_SYSTEM_ICON = dEFAULT_SYSTEM_ICON;
	}
}
