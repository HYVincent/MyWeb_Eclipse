package com.vincent.julie.bean;

/**  
 * @Title:  Memo.java   
 * @Package com.vincent.julie.bean   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��2��6�� ����12:18:03   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class MemoBean {
	//�û�id
	private long user_id;
	//�û�title
	private String memo_title;
	//����ʱ��
	private String memo_create_time;
	//����¼����
	private String memo_content;
	//�Ƿ����ѹ��� ʵ�������ݿ���tinyint���� 0Ϊfalse 1Ϊtrue
	private boolean memo_action;
	//����ʱ��
	private String memo_target_time;
	
	public String getMemo_target_time() {
		return memo_target_time;
	}
	public void setMemo_target_time(String memo_target_time) {
		this.memo_target_time = memo_target_time;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getMemo_title() {
		return memo_title;
	}
	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}
	public String getMemo_create_time() {
		return memo_create_time;
	}
	public void setMemo_create_time(String memo_create_time) {
		this.memo_create_time = memo_create_time;
	}
	public String getMemo_content() {
		return memo_content;
	}
	public void setMemo_content(String memo_content) {
		this.memo_content = memo_content;
	}
	public boolean isMemo_action() {
		return memo_action;
	}
	public void setMemo_action(boolean memo_action) {
		this.memo_action = memo_action;
	}
	
	
}
