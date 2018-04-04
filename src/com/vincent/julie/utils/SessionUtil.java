package com.vincent.julie.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name julie
 * @page com.vincent.julie.utils
 * @class describe
 * @date 2018/3/21 21:48
 */

public class SessionUtil  {

    private static Map<String,HttpSession> sessionMap = new HashMap<>();

    public static void addSession(String sessionId,HttpSession httpSession){
        sessionMap.put(sessionId,httpSession);
    }

    /**
     * 获取sessionId
     * @param sessionId
     * @return
     */
    public static HttpSession getSession(String sessionId){
        HttpSession session = null;
        /*for (Map.Entry entry : sessionMap.entrySet()) {
            if (sessionId == entry.getKey()) {
                session = sessionMap.get(sessionId);
            }
        }*/
        Iterator<String> iter = sessionMap.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            if(sessionId.equals(key)){
                session = sessionMap.get(key);
            }
        }
        return session;
    }

    /**
     * 移除某个key和值
     * @param sessionId
     */
    public static void removeSession(String sessionId){
        Iterator iterator = sessionMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (sessionId.equals(key)) {
                iterator.remove();        //添加该行代码
                sessionMap.remove(key);
            }
        }
    }

}
