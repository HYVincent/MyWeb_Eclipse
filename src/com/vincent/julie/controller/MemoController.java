package com.vincent.julie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vincent.julie.CodeConfig;
import com.vincent.julie.MsgConfig;
import com.vincent.julie.bean.MemoBean;
import com.vincent.julie.utils.MybatisUtil;
import com.vincent.julie.utils.ResponseUtils;
import com.vincent.julie.utils.StringUtils;

/**  
 * @Title:  MemoController.java   
 * @Package com.vincent.julie.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年2月6日 上午12:23:38   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */
@Controller
@RequestMapping("/user/memo")
public class MemoController {

	@RequestMapping(value = "addMemo",method = RequestMethod.POST)
	public void addMemo(@RequestParam("user_id")Integer user_id,@RequestParam("memo_title")String memo_title,@RequestParam("memo_content")String memo_content,@RequestParam("memo_target_time")String memoTargetTime,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(user_id == 0) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_USER_ID_NULL);
				return;
			}
			if(StringUtils.isEntity(memo_title)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_IS_NULL);
				return;
			}
			if(StringUtils.isEntity(memo_title)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_IS_NULL);
				return;
			}
			if(StringUtils.isEntity(memoTargetTime)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_TARGET_TIME_NULL);
				return;
			}
			if(StringUtils.isEntity(memo_content)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_CONTENT_NULL);
				return;
			}
			MemoBean memo = getMemoFromMemoTitle(user_id, memo_title);
			if(memo != null) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_EXIST);
				return;
			}
			String sql = "com.vincent.julie.dao.MemoMapping.addMemo";
			memo = new MemoBean();
			memo.setUser_id(user_id);
			memo.setMemo_content(memo_content);
			memo.setMemo_title(memo_title);
			memo.setMemo_target_time(memoTargetTime);
			MybatisUtil.getSqlSession().insert(sql,memo);
			MybatisUtil.getSqlSession().commit();
			MybatisUtil.closeSqlSession();
			MemoBean newMemo = getMemoFromMemoTitle(user_id, memo_title);
			if(newMemo != null) {
				ResponseUtils.renderJsonDataSuccess(response, MsgConfig.MEMO_ADD_SUCCESS);
			}else {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}
	
	/**
	 * 获取所有数据
	 * @param user_id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllMemo",method = RequestMethod.GET)
	public void getAllMemo(@RequestParam("user_id")Integer user_id,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(user_id == null) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.MEMO_ADD_TITLE_USER_ID_NULL);
				return;
			}
			System.out.println("user_id="+user_id);
			String sql = "com.vincent.julie.dao.MemoMapping.getMemoAll";
			List<MemoBean> data = MybatisUtil.getSqlSession().selectList(sql, user_id);
			if(data != null && data.size()>0) {
				ResponseUtils.renderJsonDataSuccess(response, MsgConfig.MEMO_SELECT_ALL_SUCCESS, data);
			}else {
				ResponseUtils.renderJsonDataSuccess(response, MsgConfig.MEMO_SELECT_ALL_IS_NULL);
			}
		} catch (Exception e) {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
		
	}
	
	/**
	 * 根据title查询
	 * @param userId
	 * @param memoTitle
	 * @return
	 */
	private MemoBean getMemoFromMemoTitle(int userId,String memoTitle) {
		String sql = "com.vincent.julie.dao.MemoMapping.getMemoFromTitle";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("memo_title", memoTitle);
		MemoBean memo = MybatisUtil.getSqlSession().selectOne(sql,map);
		MybatisUtil.closeSqlSession();
		return memo;
	}
	
	
}
