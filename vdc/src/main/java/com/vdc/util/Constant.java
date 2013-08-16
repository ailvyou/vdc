/**
 * @ClassName Constant 
 *
 * Version V1.0
 *
 * @author Lynch
 *
 * date 2013-6-24 下午5:09:02 
 *
 * Copyright 上海创驰网络技术有限公司
 *
 */
package com.vdc.util;

/**
 * 常量类
 * 
 * @author Lynch
 */
public class Constant {
	// cookie中sessionId使用的KEY
	public final static String SESSION_ID_KEY = "sessionId";
	// cookie中用户自动登录信息使用的KEY
	public final static String USER_INFO = "www.zj100.com.cn_userInfo";
	// cookie中用户自动登录信息使用的分隔符
	public final static String USER_INFO_SEPARATE = "<|user_separate|>";
	// session中用户名使用的KEY
	public final static String USER_NAME = "userName";
	// memcached中用户名使用的KEY
	// public final static String MEMCACHED_USER_ID = "memcached_user_id";
	// memcached中用户扩展信息使用的KEY
	public final static String MEMCACHED_USER_EXT = "memcached_user_ext";
	/**
	 * memcache 中CommonEnumsType key
	 */
	public final static String MEMCACHED_SYSENUMSTYPE = "memcached_sysenumstype";
	// memcached中用户扩展信息是否显示使用的KEY
	public final static String MEMCACHED_USER_PRIVACY = "memcached_user_privacy";

	public final static String MEMCACHED_USER_VISIT = "memcached_user_visit";
	// memcached中访问控制URL使用的KEY
	public final static String USER_ACCESS_AUTH_URL = "userAccessAuthUrl";
	// cookie中用户ID使用的KEY
	public final static String COOIKE_USER_ID = "www.zj100.com.cn_user_id";
	// COOKIE有效期 30天
	public final static int COOKIE_VALID = 30 * 24 * 60 * 60;

	/**
	 * session用户的key
	 */
	public final static String SESSIONUSER_KEY = "sessionUser";
	//用户注册时默认头像
	public final static String USER_DEFAULT_ICON = "/images/defaultIcon/usercenter/default_user_icon.jpg";
}
