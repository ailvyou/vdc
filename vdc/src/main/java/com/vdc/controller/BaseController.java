package com.vdc.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vdc.util.JsonUtil;
import com.vdc.util.RandomValidateCodeUtil;

public class BaseController {

	// protected static final String MSG = "msg";
	// protected static final String IS_SUCCESS = "isSuccess";

	// /**
	// * 获得session中的用户
	// *
	// * @param request
	// * @return
	// * @author jack
	// */
	// protected UserDto getSessionUser(HttpServletRequest request) {
	// UserDto sessionUser = null;
	// String userId = this.getCookieUserId(request);
	// if (userId != null) {
	// sessionUser = (UserDto) MemcachedUtil.getInstance().get(userId);
	// }
	// return sessionUser;
	// }
	//
	// /**
	// * 获得session中的当前用户ID
	// *
	// * @param request
	// * @return
	// * @author daniel
	// */
	// // @ModelAttribute("currentUserId")
	// protected Long getCurrentUserId(HttpServletRequest request) {
	// UserDto sessionUser = getSessionUser(request);
	// if (sessionUser != null && sessionUser.getUserId() != null) {
	// return sessionUser.getUserId();
	// }
	// return null;
	// }
	//
	// /**
	// * 获得cookie中的用户信息
	// *
	// * @param request
	// * @return
	// * @author jason
	// */
	// protected String getCookieUserId(HttpServletRequest request) {
	// // String userId = "";
	// Cookie[] cookies = request.getCookies();
	// if (cookies != null) {
	// for (Cookie cookie : cookies) {
	// // 检查用户是否已登录
	// if (Constant.COOIKE_USER_ID.equals(cookie.getName())) {
	// return cookie.getValue();
	// // userId = cookie.getValue();
	// // cookie.setMaxAge(0);
	// // if (userId != null) {
	// // return userId;
	// // }
	// // break;
	// }
	// }
	// }
	// // return userId;
	// return null;
	// }

	/**
	 * 移除cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookiename
	 */
	protected void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookiename) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// 检查用户是否已登录
				if (cookiename.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	//
	// /**
	// * 登录
	// *
	// * @param request
	// * @param response
	// * @return
	// */
	// protected boolean doLogin(HttpServletRequest request, HttpServletResponse
	// response) {
	// boolean flag = false;
	// UserDto userDto = null;
	// String userName = request.getParameter("username");
	// String password = request.getParameter("password");
	// int autologin = StringUtils.isEmpty(request.getParameter("autologin")) ?
	// 0 : Integer.parseInt(request.getParameter("autologin"));
	// userDto = this.userService.login(userName, password);
	// if (userDto != null) {
	// String cookieUser = RandomIDUtil.getNewUUID();
	// MemcachedUtil.getInstance().set(cookieUser, MemcachedUtil.SESSION_SECOND,
	// userDto);
	// Cookie cookieUserId = new Cookie(Constant.COOIKE_USER_ID, cookieUser);
	// // TODO 此处以后在正式上线的时候要设域 setPath("/");
	// cookieUserId.setPath("/");
	// response.addCookie(cookieUserId);
	// if (autologin == 1) {
	// Cookie cookie = new Cookie(Constant.USER_INFO, userName);
	// cookie.setMaxAge(Constant.COOKIE_VALID);
	// // TODO 此处以后在正式上线的时候要设域 setPath("/");
	// cookie.setPath("/");
	// response.addCookie(cookie);
	// }
	// request.getSession().setAttribute(Constant.COOIKE_USER_ID, cookieUser);
	// log.info("user:" + userName + " login,COOIKE_USER_ID=" + cookieUser);
	// flag = true;
	// }
	// return flag;
	// }
	//
	// /**
	// * 登出
	// *
	// * @param request
	// * @param response
	// */
	// protected void doLogout(HttpServletRequest request, HttpServletResponse
	// response) {
	// String userId = this.getCookieUserId(request);
	// request.getSession().removeAttribute(Constant.COOIKE_USER_ID);
	// if (userId != null && !userId.equals("")) {
	// MemcachedUtil.getInstance().remove(userId);
	// this.removeCookie(request, response, Constant.COOIKE_USER_ID);
	// this.removeCookie(request, response, Constant.USER_INFO);
	// }
	// }

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 */
	protected void createValidateCode(HttpServletRequest request, HttpServletResponse response) {
		RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil(80, 26, 40, 4);
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		randomValidateCode.getRandcode(request, response);
	}

	//
	// /**
	// * 返回业务逻辑为空的情况下的视图
	// *
	// * @param msg
	// * @return
	// */
	// public ModelAndView getNullErrorResultJson(String msg) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject(IS_SUCCESS, false);
	// mv.addObject(MSG, msg);
	// return mv;
	// }
	//
	// /**
	// * 返回业务逻辑为空的情况下的视图
	// *
	// * @param msg
	// * @return
	// */
	// public ModelAndView getNullErrorResultPage(String msg) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject(IS_SUCCESS, "error");
	// mv.addObject(MSG, msg);
	// mv.setViewName("/error/error");
	// return mv;
	// }

	protected void outputJson(boolean isSuccess, String msg, HttpServletResponse response) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("type", isSuccess ? "success" : "");
		msgMap.put("content", isSuccess ? "admin.message.success" : msg);
		this.outputJson(JsonUtil.objectToJson(msgMap), response);
	}

	protected void outputJson(String str, HttpServletResponse response) {
		this.output(str, response, "application/json;charset=UTF-8");
	}

	protected void outputText(String str, HttpServletResponse response) {
		this.output(str, response, "text/html;charset=UTF-8");
	}

	protected void outputXml(String str, HttpServletResponse response) {
		this.output(str, response, "text/xml;charset=UTF-8");
	}

	protected void output(String str, HttpServletResponse response, String contentType) {
		response.reset();
		response.setContentType(contentType);
		try {
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /**
	// * 返回消息到页面（默认放入的消息就算错误的消息）
	// *
	// * @param mv
	// * @param msg
	// * @return
	// * @author daniel
	// */
	// protected ModelAndView putMsgToMv(ModelAndView mv, String msg) {
	// return putMsgToMv(mv, msg, false);
	// }
	//
	// /**
	// * 返回消息到页面
	// *
	// * @param mv
	// * @param msg
	// * @param isSuccess
	// * @return
	// * @author daniel
	// */
	// protected ModelAndView putMsgToMv(ModelAndView mv, String msg, boolean
	// isSuccess) {
	// mv.addObject(IS_SUCCESS, isSuccess);
	// mv.addObject(MSG, msg);
	// return mv;
	// }

	/**
	 * 将request中的参数构造成map形式
	 * 
	 * @param request
	 * @return
	 * @author daniel
	 */
	protected Map<String, Object> buildParamMapFromRequest(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getParameter(name);
			paramMap.put(name, value);
		}
		return paramMap;
	}

}
