package com.vincent.julie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vincent.julie.CodeConfig;
import com.vincent.julie.MsgConfig;
import com.vincent.julie.bean.VersionBean;
import com.vincent.julie.utils.MybatisUtil;
import com.vincent.julie.utils.ResponseUtils;

/**  
 * @Title:  VersionController.java   
 * @Package com.vincent.julie.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Vinent QQ:1032006226
 * @date:   2018年3月18日 下午9:04:18   
 * @version V1.0 
 * @Copyright: 2018 
 * 注意：本内容仅限于是我写的
 */
@Controller
@RequestMapping("/version")
public class VersionController {
	
	/**
	 * 添加新版本
	 * @param version_code
	 * @param version_codes
	 * @param version_desc
	 * @param filePath
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "addNewVersion",method = RequestMethod.POST)
	public static void addNewVersion(@RequestParam("version_code")int version_code,@RequestParam("version_codes")String version_codes,
			@RequestParam("version_desc")String version_desc,@RequestParam("version_file_path")String filePath,HttpServletRequest request,HttpServletResponse response) {
		try {
			String sql = "com.vincent.julie.dao.VersionMapping.addVersion";
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("version_code", version_code);
			params.put("version_codes", version_codes);
			params.put("version_desc", version_desc);
			params.put("version_file_path", filePath);
			MybatisUtil.getSqlSession().insert(sql, params);
			MybatisUtil.getSqlSession().commit();
			MybatisUtil.closeSqlSession();
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.VERSION_ADD_NEW_VERSION_SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}

	/**
	 * 检查新版本
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "checkNewVersion",method = RequestMethod.GET)
	public static void checkNewVersion(@RequestParam("version")int version,HttpServletRequest request,HttpServletResponse response) {
		String sql = "com.vincent.julie.dao.VersionMapping.getLatestVersion";
		VersionBean result = MybatisUtil.getSqlSession().selectOne(sql);
		MybatisUtil.closeSqlSession();
		int versionDB = result.getVersion_code();
		System.out.println("------------"+result.getVersion_desc());
		if(versionDB != version) {
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.VERSION_CHECK_NEW_VERSION_SUCCESS,result);
		}else {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.VERSION_VERSION_LATEST);
		}
		
	}
	/**
	 * 删除某个id对应的版本
	 * @param versionId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "deleteIdVersion",method = RequestMethod.POST)
	public static void deleteIdVersion(@RequestParam("version_id")int versionId,HttpServletRequest request,HttpServletResponse response) {
		try {
			String sql = "com.vincent.julie.dao.VersionMapping.deleteIdVersion";
			MybatisUtil.getSqlSession().delete(sql, versionId);
			MybatisUtil.getSqlSession().commit();
			MybatisUtil.closeSqlSession();
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.VERSION_DELETE_VERSION_SUCCESS);
		} catch (Exception e) {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}
	
}
