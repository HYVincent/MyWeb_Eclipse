package com.vincent.julie.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vincent.julie.CodeConfig;
import com.vincent.julie.MsgConfig;
import com.vincent.julie.bean.ResponseResult;
import com.vincent.julie.bean.User;
import com.vincent.julie.netty.NettyPush;
import com.vincent.julie.netty.msg.MsgType;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.julie.utils.MD5Util;
import com.vincent.julie.utils.MybatisUtil;
import com.vincent.julie.utils.ResponseUtils;
import com.vincent.julie.utils.SessionUtil;
import com.vincent.julie.utils.Sha1Util;
import com.vincent.julie.utils.StringUtils;
import com.vincent.julie.utils.UserUtils;

import io.netty.util.CharsetUtil;

/**
 * @desc �û����
 * @author Vincent QQ:1032006226
 * @version 1.0
 * @date 2018��1��27��
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * ע��ӿ�
	 * 
	 * @param password
	 * @param phone
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void register(@RequestParam("user_password") String user_password,
			@RequestParam("user_phone") String user_phone, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEntity(user_phone)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_PHONE_IS_NOT_NULL);
				return;
			}
			if (StringUtils.isEntity(user_password)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_PASSWORD_IS_NOT_NULL);
				return;
			}
			if (UserUtils.getInstance().queryUserIsExist(user_phone)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.USER_REGISTER_USER_IS_EXIST);
			} else {
				// ResponseUtils.renderJsonDataFail(response, "102", "�û������ڣ�����ע��");
				User user = new User();
				user.setUser_password(user_password);
				user.setUser_phone(user_phone);
				String register = "com.vincent.julie.dao.UserMapping.registerUser";
				MybatisUtil.getSqlSession().insert(register, user);
				MybatisUtil.getSqlSession().commit();
				MybatisUtil.closeSqlSession();
				if (UserUtils.getInstance().queryUserIsExist(user_phone)) {
					ResponseUtils.renderJsonDataSuccess(response, MsgConfig.USER_REGISTER_SUCCESS);
				} else {
					// ע��ʧ��
					ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.USER_REGISTER_FAILE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}

	/**
	 * 
	 * @param originalToken
	 * @return
	 */
	private static String generatorToken(String originalToken) {
		/*ByteArrayInputStream inputStream = new ByteArrayInputStream("hello".getBytes());
		try {
			return DigestUtils.md5DigestAsHex(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return MD5Util.md5(originalToken);
	}

	/**
	 * �û���¼
	 * 
	 * @param user_phone
	 * @param user_password
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(@RequestParam("user_phone") String user_phone,
			@RequestParam("user_password") String user_password, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (StringUtils.isEntity(user_phone)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_PHONE_IS_NOT_NULL);
				return;
			}
			if (StringUtils.isEntity(user_password)) {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_PASSWORD_IS_NOT_NULL);
				return;
			}
			User user = UserUtils.getInstance().getUserForPhone(user_phone);
			if (user != null) {
				if (StringUtils.equest(user_password, user.getUser_password())) {
					// ��¼�ɹ�
					user.setUser_password("********");
					UserUtils.getInstance().setLoginTime(user_phone, System.currentTimeMillis());
					// ���ɿͻ���token������֤�û��Ƿ��¼�ɹ�
					String originalToken  = user.getUser_name() + "_JULIE" + "TOKEN"+System.currentTimeMillis(); 
					String token = generatorToken(originalToken);
					String sha1Token = Sha1Util.encode(token);
					request.getSession().setAttribute("API_TOKEN", sha1Token);
					SessionUtil.addSession(request.getRequestedSessionId(), request.getSession());
					user.setApi_token(sha1Token);
					ResponseUtils.renderJsonDataSuccess(response, MsgConfig.USER_LOGIN_SUCCESS, user);
					System.out.println("�û�" + user_phone + "��¼�ɹ� token="+sha1Token);
					System.out.println("������token:"+request.getSession().getAttribute("API_TOKEN"));
					
				} else {
					// ��¼ʧ��
					ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.USER_LOGIN_FAIL);
				}
			} else {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_USER_ACCOUNT_IS_NOT_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}

	/**
	 * �޸�����
	 * 
	 * @param user_phone
	 *            �ֻ�����
	 * @param old_password
	 *            ������
	 * @param new_password
	 *            ������
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "alertPassword", method = RequestMethod.POST)
	public void alertPassword(@RequestParam("user_phone") String user_phone,
			@RequestParam("old_password") String old_password, @RequestParam("new_password") String new_password,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = UserUtils.getInstance().getUserForPhone(user_phone);
			if (user != null) {
				if (StringUtils.equest(user.getUser_password(), old_password)) {
					user.setUser_password(new_password);
					String sql = "com.vincent.julie.dao.UserMapping.resetPassword";
					Map<String, String> map = new HashMap<String, String>();
					map.put("user_password", new_password);
					MybatisUtil.getSqlSession().update(sql, map);
					MybatisUtil.getSqlSession().commit();
					MybatisUtil.closeSqlSession();
					User newUser = UserUtils.getInstance().getUserForPhone(user_phone);
					if (newUser != null) {
						ResponseUtils.renderJsonDataSuccess(response, MsgConfig.USER_ALERT_PASSWORD_SUCCESS);
					} else {
						ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
								MsgConfig.USER_ALERT_PASSWORD_FAIL);
					}
				} else {
					ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
							MsgConfig.USER_ALERT_PASSWORD_ERROR);
				}
			} else {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_USER_ACCOUNT_IS_NOT_EXIST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}

	/**
	 * ��������
	 * 
	 * @param user_phone
	 * @param user_password
	 *            ������
	 * @param httpServletRequest
	 * @param response
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public void resetPassword(@RequestParam("user_phone") String user_phone,
			@RequestParam("user_password") String user_password, HttpServletRequest httpServletRequest,
			HttpServletResponse response) {
		try {
			User user = UserUtils.getInstance().getUserForPhone(user_phone);
			if (user != null) {
				String sql = "com.vincent.julie.dao.UserMapping.resetPassword";
				Map<String, String> map = new HashMap<>();
				map.put("user_phone", user_phone);
				map.put("user_password", user_password);
				MybatisUtil.getSqlSession().update(sql, map);
				MybatisUtil.getSqlSession().commit();
				MybatisUtil.closeSqlSession();
				if (StringUtils.equest(UserUtils.getInstance().getUserForPhone(user_phone).getUser_password(),
						user_password)) {
					ResponseUtils.renderJsonDataSuccess(response, MsgConfig.USER_RESET_PASSWORD_SUCCESS);
				} else {
					ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
							MsgConfig.USER_RESET_PASSWORD_FAIL);
				}
			} else {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_USER_ACCOUNT_IS_NOT_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}
	}

	/**
	 * ��ѯĳ���û��Ƿ����
	 * 
	 * @param user_phone
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "checkPhoneIsExist", method = RequestMethod.POST)
	public void checkUserIsExist(@RequestParam("user_phone") String user_phone, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isEntity(user_phone)) {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.COMMON_PHONE_IS_NOT_NULL);
			return;
		}
		if (UserUtils.getInstance().queryUserIsExist(user_phone)) {
			// �˺Ŵ���
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR, MsgConfig.USER_REGISTER_USER_IS_EXIST);
		} else {
			ResponseUtils.renderJsonDataSuccess(response, MsgConfig.COMMON_USER_ACCOUNT_IS_NOT_EXIST);
		}
	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param user_phone
	 * @param user_name
	 * @param user_head
	 * @param user_height
	 * @param user_sex
	 * @param user_birthday
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "changeUserInfo", method = RequestMethod.POST)
	public void alertUserInfo(@RequestParam("user_phone") String user_phone,
			@RequestParam("user_name") String user_name, @RequestParam("user_height") int user_height,
			@RequestParam("user_sex") String user_sex, @RequestParam("user_weight") String user_weight,
			@RequestParam("user_birthday") String user_birthday, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (UserUtils.getInstance().queryUserIsExist(user_phone)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("user_phone", user_phone);
				params.put("user_name", user_name);
				params.put("user_height", user_height);
				params.put("user_sex", user_sex);
				params.put("user_birthday", user_birthday);
				params.put("user_weight", user_weight);
				String sql = "com.vincent.julie.dao.UserMapping.updateUserInfo";
				MybatisUtil.getSqlSession().update(sql, params);
				MybatisUtil.getSqlSession().commit();
				MybatisUtil.closeSqlSession();
				ResponseUtils.renderJsonDataSuccess(response, MsgConfig.USER_CHANGE_USER_INFO_SUCCESSFAILE);
			} else {
				ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_ERROR,
						MsgConfig.COMMON_USER_ACCOUNT_IS_NOT_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_SERVICE_EXCEPTION);
		}

	}
}
