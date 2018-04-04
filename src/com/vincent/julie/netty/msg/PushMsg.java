package com.vincent.julie.netty.msg;

/**
 * @Project: schoolmallapi
 * @ClassName: PushMsg
 * @Description: 推�?�消息类�?
 * @author:	chenpy
 * @date:	2016�?11�?1�?
 * @version 1.0.0
 */
public class PushMsg<T> extends BaseMsg {

    
    /**
	 * ���л�ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * �������� �������͸��ͻ��˵���������
     */
    private int dataType;
    
    /**
     * ���͵�����
     */
    private T data;
    

    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public PushMsg() {
        super();
        setType(MsgType.PUSH);
    }
    

}
