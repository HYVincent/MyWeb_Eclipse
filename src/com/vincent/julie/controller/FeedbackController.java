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
import com.vincent.julie.bean.FeedbackBean;
import com.vincent.julie.utils.MybatisUtil;
import com.vincent.julie.utils.ResponseUtils;

/**  
 * @Title:  FeedbackController.java   
 * @Package com.vincent.julie.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年4月4日 下午9:30:45   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */
@Controller()
@RequestMapping("user/feedback")
public class FeedbackController {

	@RequestMapping(value = "addFeedback",method = RequestMethod.POST)
	public void addFeedback(@RequestParam("user_id")int user_id,@RequestParam("feedback_title")String feedback_title,@RequestParam("feedback_type")int feedback_type,
			@RequestParam("feedback_content")String feedback_content,
			HttpServletRequest request,HttpServletResponse response) {
		try {
			String sql = "com.vincent.julie.dao.FeedbackMapping.addFeedback";
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("user_id", user_id);
			params.put("feedback_title", feedback_title);
			params.put("feedback_type", feedback_type);
			params.put("feedback_content", feedback_content);
			params.put("feedback_time", System.currentTimeMillis());
			MybatisUtil.getSqlSession().insert(sql, params);
			MybatisUtil.getSqlSession().commit();
			MybatisUtil.closeSqlSession();
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.FEEDBACK_COMMIT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.FEEDBACK_COMMIT_FAIL);
		}
	}
	
	@RequestMapping(value = "getAllFeedback",method = RequestMethod.GET)
	public void getAllFeedback(@RequestParam("user_id")Integer user_id,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(user_id == null) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.FEEDBACK_USER_ID_NULL);
				return;
			}
			String sql = "com.vincent.julie.dao.FeedbackMapping.selectAllFeedback";
			List<FeedbackBean> result = MybatisUtil.getSqlSession().selectList(sql, user_id);
			System.out.println(result.size());
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.FEEDBACK_QUERY_SUCCESS, result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.FEEDBACK_QUERY_FAILE);
		}
	}
	
}
