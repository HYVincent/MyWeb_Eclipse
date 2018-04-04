package com.vincent.julie.utils;

import java.util.HashMap;
import java.util.Map;

import com.vincent.julie.bean.User;

/**
 * @Title: UserUtils.java
 * @Package com.vincent.julie.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: Vinent QQ:1032006226
 * @date: 2018年3月14日 下午11:01:39
 * @version V1.0
 * @Copyright: 2018 注意：本内容仅限于是我写的
 */

public class UserUtils {
	
	private static UserUtils instance;
	
	public static UserUtils getInstance() {
		if(instance ==  null) {
			instance = new UserUtils();
		}
		return instance;
	}

	/**
	 * 设置用户退出登录的时间
	 * @param time
	 */
	public void setLoginOutTime(String phoneNumber,long time) {
		String sql = "com.vincent.julie.dao.UserMapping.setLoginOutTime";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userLoginOutTime", time);
		params.put("user_phone", phoneNumber);
		MybatisUtil.getSqlSession().update(sql, params);
		MybatisUtil.getSqlSession().commit();
		MybatisUtil.closeSqlSession();
	}

	/**
	 * 设置用户登录的时间
	 * @param time
	 */
	public void setLoginTime(String phoneNumber,long time) {
		String sql = "com.vincent.julie.dao.UserMapping.setLoginTime";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userLoginTime", time);
		params.put("user_phone", phoneNumber);
		MybatisUtil.getSqlSession().update(sql, params);
		MybatisUtil.getSqlSession().commit();
		MybatisUtil.closeSqlSession();
	}

	/**
	 * true 存在 false 不存在
	 * 
	 * @param phone
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean queryUserIsExist(String user_phone) {
		User user = getUserForPhone(user_phone);
		if(user != null) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 根据手机号码查询用户
	 * 
	 * @param user_phone
	 * @return
	 */
	public User getUserForPhone(String user_phone) {
		try {
			String sql = "com.vincent.julie.dao.UserMapping.selectUser";
			User user = MybatisUtil.getSqlSession().selectOne(sql, user_phone);
			MybatisUtil.closeSqlSession();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
