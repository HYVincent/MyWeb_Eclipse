package com.vincent.julie.bean;

/**
 * @Title: AccessToken.java
 * @Package com.vincent.julie.bean
 * @Description: ���token���ؽ����AccessToken.java
 * @author: Vinent QQ:1032006226
 * @date: 2018��3��16�� ����12:10:04
 * @version V1.0
 * @Copyright: 2018 ע�⣺�����ݽ���������д��
 */

public class AccessToken {

	private String access_token;
	private String token_type;
	private long expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

}
