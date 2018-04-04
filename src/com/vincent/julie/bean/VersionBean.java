package com.vincent.julie.bean;

import java.io.Serializable;

/**  
 * @Title:  VersionBean.java   
 * @Package com.vincent.julie.bean   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��18�� ����11:06:48   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class VersionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int version_id;
	private int version_code;
	private String version_codes;
	private String version_desc;
	private String version_file_path;
	private long version_time;
	
	  //��ʾ�����Ի����ʱ����ı�
    private String version_dialog_title;
    //�Ƿ�ǿ���û�����
    private boolean is_must_upgrade;
    
    
	

	public String getVersion_dialog_title() {
		return version_dialog_title;
	}
	public void setVersion_dialog_title(String version_dialog_title) {
		this.version_dialog_title = version_dialog_title;
	}
	public boolean isIs_must_upgrade() {
		return is_must_upgrade;
	}
	public void setIs_must_upgrade(boolean is_must_upgrade) {
		this.is_must_upgrade = is_must_upgrade;
	}
	public int getVersion_id() {
		return version_id;
	}
	public void setVersion_id(int version_id) {
		this.version_id = version_id;
	}
	public int getVersion_code() {
		return version_code;
	}
	public void setVersion_code(int version_code) {
		this.version_code = version_code;
	}
	public String getVersion_codes() {
		return version_codes;
	}
	public void setVersion_codes(String version_codes) {
		this.version_codes = version_codes;
	}
	public String getVersion_desc() {
		return version_desc;
	}
	public void setVersion_desc(String version_desc) {
		this.version_desc = version_desc;
	}
	public String getVersion_file_path() {
		return version_file_path;
	}
	public void setVersion_file_path(String version_file_path) {
		this.version_file_path = version_file_path;
	}
	public long getVersion_time() {
		return version_time;
	}
	public void setVersion_time(long version_time) {
		this.version_time = version_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
