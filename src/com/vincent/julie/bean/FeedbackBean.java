package com.vincent.julie.bean;


/**  
 * @Title:  FeedbackBean.java   
 * @Package com.vincent.julie.bean   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年4月4日 下午9:31:25   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class FeedbackBean {

	private int uuid;
	private String feedback_title;
	// 1 建议  2 bug
	private int feedback_type;
	private String feedback_content;
	//反馈时间
	private long feedback_time;
	
	
	public long getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(long feedback_time) {
		this.feedback_time = feedback_time;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getFeedback_title() {
		return feedback_title;
	}
	public void setFeedback_title(String feedback_title) {
		this.feedback_title = feedback_title;
	}
	public int getFeedback_type() {
		return feedback_type;
	}
	public void setFeedback_type(int feedback_type) {
		this.feedback_type = feedback_type;
	}
	public String getFeedback_content() {
		return feedback_content;
	}
	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}


	
}
