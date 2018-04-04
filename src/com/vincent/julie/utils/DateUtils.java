package com.vincent.julie.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * @Title:  DateUtils.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��2��6�� ����12:10:16   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class DateUtils {

	
	private final static long minute = 60 * 1000;// 1����
    private final static long hour = 60 * minute;// 1Сʱ
    private final static long day = 24 * hour;// 1��
    private final static long month = 31 * day;// ��
    private final static long year = 12 * month;// ��

    //ʱ���ʽ
    public final static String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public final static String DATA_FORMAT_YEAR_MONTH_DAY2 = "yyyy��MM��dd��";
    public final static String DATA_FORMAT_YEAR = "yyyy";
    public final static String DATA_FORMAT_MONTH = "MM��";
    public final static String DATA_FORMAT_HOUR = "HH";
    public final static String DATA_FORMAT_MIN = "mm";
    public final static String DATA_FORMAT_S = "ss";

    /**
     * ��ʽ��ʱ��
     * @param format ����ĸ�ʽ
     * @param time ʱ���
     * @return
     */
    public static String getDateString(String format,long time)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date d = new Date(time);
        return sf.format(d);
    }



    /**
     * ������������������
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
            return r + "��ǰ";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "����ǰ";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "��ǰ";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "��Сʱǰ";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "����ǰ";
        }
        return "�ո�";
    }

    /**
     * ������������������
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
            return r + "��ǰ";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "��Сʱǰ";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "����ǰ";
        }
        return "�ո�";
    }

    /**
     * ��ȡ��ǰСʱ
     * @return
     */
    public static int getCurrentHour(){
        return Integer.valueOf(getDateString(DATA_FORMAT_HOUR,System.currentTimeMillis()));
    }

    /**
     * ��ȡ��ǰ����
     * @return
     */
    public static int getCurrentMin(){
        return Integer.valueOf(getDateString(DATA_FORMAT_MIN,System.currentTimeMillis()));
    }

    /**
     * ��ȡ��ǰs
     * @return
     */
    public static int getCurrentS(){
        return Integer.valueOf(getDateString(DATA_FORMAT_S,System.currentTimeMillis()));
    }

    /**
     * dateתΪʱ���
     * @param date
     * @return
     */
    public static long dateToTime(Date date){
        return date.getTime();
    }

    /**
     * ��ʱ���ʽתΪʱ���
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
     * ��������ʱ��֮���ʱ���
     *
     * @param startTime
     *            ��ʼʱ��
     * @param endTime
     *            ����ʱ��
     * @return Stringʱ������
     */
    public static String getTimeInterval(long startTime, long endTime) {
        return getTimeInterval(String.valueOf(startTime), String.valueOf(endTime));
    }

    /**
     * ��������ʱ��֮���ʱ���
     *
     * @param startTime
     *            ��ʼʱ��
     * @param endTime
     *            ����ʱ��
     * @return Stringʱ������
     */
    public static String getTimeInterval(String startTime, String endTime) {
        if(StringUtils.isEntity(startTime) || StringUtils.isEntity(endTime)){
            throw new NullPointerException("startTime or endTime is null.");
        }
        long start = Long.valueOf(startTime);
        long end = Long.valueOf(endTime);
        long time = end - start;
        // �ֱ��ʾ�죬Сʱ�����ӣ���
        long d, h, m, s;
        StringBuffer sb = new StringBuffer();
        d = time / day;
        if (d != 0) {
            sb.append(String.valueOf(d));
            sb.append("��");
        }
        long t1 = time % day;
        h = t1 / hour;
        if (h != 0) {
            sb.append(String.valueOf(h));
            sb.append("Сʱ");
        }
        long t2 = t1 % hour;
        m = t2 / minute;
        if (m != 0) {
            sb.append(String.valueOf(m));
            sb.append("����");
        }
        long t3 = t2 % minute;
        s = t3 / 1000;
        if (s != 0) {
            sb.append(String.valueOf(s));
            sb.append("��");
        }
        return sb.toString();
    }

	
	/** 
	 * ��ȡָ��ʱ���Ӧ�ĺ����� 
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
