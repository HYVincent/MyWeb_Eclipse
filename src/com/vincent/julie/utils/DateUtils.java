package com.vincent.julie.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * @Title:  DateUtils.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年2月6日 上午12:10:16   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */

public class DateUtils {

	
	private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    //时间格式
    public final static String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public final static String DATA_FORMAT_YEAR_MONTH_DAY2 = "yyyy年MM月dd日";
    public final static String DATA_FORMAT_YEAR = "yyyy";
    public final static String DATA_FORMAT_MONTH = "MM月";
    public final static String DATA_FORMAT_HOUR = "HH";
    public final static String DATA_FORMAT_MIN = "mm";
    public final static String DATA_FORMAT_S = "ss";

    /**
     * 格式化时间
     * @param format 传入的格式
     * @param time 时间戳
     * @return
     */
    public static String getDateString(String format,long time)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date d = new Date(time);
        return sf.format(d);
    }



    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText2(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0;
        if (diff > month) {
            r = (diff / month);
            return getDateString(DATE_FORMAT_ALL,date.getTime());
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 获取当前小时
     * @return
     */
    public static int getCurrentHour(){
        return Integer.valueOf(getDateString(DATA_FORMAT_HOUR,System.currentTimeMillis()));
    }

    /**
     * 获取当前分钟
     * @return
     */
    public static int getCurrentMin(){
        return Integer.valueOf(getDateString(DATA_FORMAT_MIN,System.currentTimeMillis()));
    }

    /**
     * 获取当前s
     * @return
     */
    public static int getCurrentS(){
        return Integer.valueOf(getDateString(DATA_FORMAT_S,System.currentTimeMillis()));
    }

    /**
     * date转为时间戳
     * @param date
     * @return
     */
    public static long dateToTime(Date date){
        return date.getTime();
    }

    /**
     * 把时间格式转为时间戳
     * @param time 2018-02-27 15:25:56
     * @return
     */
    public static long strTolong(String time){
        try {
            DateFormat dateFormat = DateFormat.getDateInstance();
            /* Date date = new Date(time);
            return date.getTime();*/
        	return dateFormat.parse(time).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个时间之间的时间差
     *
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return String时间描述
     */
    public static String getTimeInterval(long startTime, long endTime) {
        return getTimeInterval(String.valueOf(startTime), String.valueOf(endTime));
    }

    /**
     * 计算两个时间之间的时间差
     *
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return String时间描述
     */
    public static String getTimeInterval(String startTime, String endTime) {
        if(StringUtils.isEntity(startTime) || StringUtils.isEntity(endTime)){
            throw new NullPointerException("startTime or endTime is null.");
        }
        long start = Long.valueOf(startTime);
        long end = Long.valueOf(endTime);
        long time = end - start;
        // 分别表示天，小时，分钟，秒
        long d, h, m, s;
        StringBuffer sb = new StringBuffer();
        d = time / day;
        if (d != 0) {
            sb.append(String.valueOf(d));
            sb.append("天");
        }
        long t1 = time % day;
        h = t1 / hour;
        if (h != 0) {
            sb.append(String.valueOf(h));
            sb.append("小时");
        }
        long t2 = t1 % hour;
        m = t2 / minute;
        if (m != 0) {
            sb.append(String.valueOf(m));
            sb.append("分钟");
        }
        long t3 = t2 % minute;
        s = t3 / 1000;
        if (s != 0) {
            sb.append(String.valueOf(s));
            sb.append("秒");
        }
        return sb.toString();
    }

	
	/** 
	 * 获取指定时间对应的毫秒数 
	 * @param time "HH:mm:ss" 
	 * @return 
	 */  
	public static long getTimeMillis(String time) {  
	    try {  
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	        return curDate.getTime();  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	    }  
	    return 0;  
	}  
	
}
