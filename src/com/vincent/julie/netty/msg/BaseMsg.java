package com.vincent.julie.netty.msg;

import java.io.Serializable;

/**
 * @Project: schoolmallapi
 * @ClassName: BaseMsg
 * @Description: 
 * 消息基类
 * 必须实现序列，serialVersionUID �?定要�?
 * @author:	chenpy
 * @date:	2016�?11�?1�?
 * @version 1.0.0
 */
public abstract class BaseMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType type;
    private String phoneNum;

    public BaseMsg() {
        this.phoneNum = Constants.getPhoneNum();
    }

    public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }
}
