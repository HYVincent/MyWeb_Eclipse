package com.vincent.julie.bean;

import java.io.Serializable;

/**
 * @Title: ResponseResult.java
 * @Package com.vincent.julie.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: Vinent QQ:1032006226
 * @date: 2018年3月17日 下午4:24:14
 * @version V1.0
 * @Copyright: 2018 注意：本内容仅限于是我写的
 */

public class ResponseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String msg;
	private boolean isSuccess;
	private Object data;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
