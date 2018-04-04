package com.vincent.julie.netty.msg;

/**
 * @Project: schoolmallapi
 * @ClassName: PingMsg
 * @Description: 心跳�?测消息类�?
 * @author:	chenpy
 * @date:	2016�?11�?1�?
 * @version 1.0.0
 */
public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
