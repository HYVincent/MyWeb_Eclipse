package com.vincent.julie.netty.msg;

/**
 * @Project: schoolmallapi
 * @ClassName: PingMsg
 * @Description: å¿ƒè·³æ£?æµ‹æ¶ˆæ¯ç±»å?
 * @author:	chenpy
 * @date:	2016å¹?11æœ?1æ—?
 * @version 1.0.0
 */
public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
