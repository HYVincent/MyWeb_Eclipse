package com.vincent.julie.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.vincent.julie.CodeConfig;

public class ResponseUtils {

	

	/**
	 * 结果正确
	 * 
	 * @param response
	 * @param statusCode
	 * @param msg
	 * @param dataJson
	 */
	public static void renderJsonDataSuccess(HttpServletResponse response, String msg) {
		try {

			JSONObject responseObj = new JSONObject();
			responseObj.put("success", true);
			responseObj.put("responseCode", CodeConfig.SERVICE_NORMAL);
			responseObj.put("msg", msg);
			responseObj.put("data", null);

			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(responseObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	/**
	 * 结果正确
	 * 
	 * @param response
	 * @param statusCode
	 * @param msg
	 * @param dataJson
	 */
	public static void renderJsonDataSuccess(HttpServletResponse response, String msg, Object o) {
		try {

			JSONObject responseObj = new JSONObject();
			responseObj.put("success", true);
			responseObj.put("errorCode", CodeConfig.SERVICE_NORMAL);
			responseObj.put("msg", msg);
			responseObj.put("data", o);

			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(responseObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 结果错误
	 * 
	 * @param response
	 * @param statusCode
	 * @param msg
	 * @param dataJson
	 */
	public static void renderJsonDataFail(HttpServletResponse response, int errorCode, String msg) {
		try {

			JSONObject responseObj = new JSONObject();
			responseObj.put("success", false);
			responseObj.put("errorCode", errorCode);
			responseObj.put("msg", msg);
			responseObj.put("data", null);

			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(responseObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
