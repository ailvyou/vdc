<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 - Powered By SHOP++</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jsbn.js"></script>
<script type="text/javascript" src="${ctx}/js/prng4.js"></script>
<script type="text/javascript" src="${ctx}/js/rng.js"></script>
<script type="text/javascript" src="${ctx}/js/rsa.js"></script>
<script type="text/javascript" src="${ctx}/js/base64.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript">
	$().ready( function() {
		
		var $loginForm = $("#loginForm");
		var $enPassword = $("#enPassword");
		var $username = $("#username");
		var $password = $("#password");
		var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		var $isRememberUsername = $("#isRememberUsername");
		
		// 记住用户名
		if(getCookie("adminUsername") != null) {
			$isRememberUsername.prop("checked", true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		} else {
			$isRememberUsername.prop("checked", false);
			$username.focus();
		}
		
		// 更换验证码
		$captchaImage.click( function() {
			$captchaImage.attr("src", "/shopxx/admin/common/captcha.jhtml?captchaId=bdf261f4-c598-42cf-81c2-fc80c3415199&timestamp=" + (new Date()).valueOf());
		});
		
		// 表单验证、记住用户名
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$.message("warn", "请输入您的用户名");
				return false;
			}
			if ($password.val() == "") {
				$.message("warn", "请输入您的密码");
				return false;
			}
			if ($captcha.val() == "") {
				$.message("warn", "请输入您的验证码");
				return false;
			}
			
			if ($isRememberUsername.prop("checked")) {
				addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
			} else {
				removeCookie("adminUsername");
			}
			
			var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex("AISnSOb9hxr/m8VNdG0/zX8XR4YoyzoBMS6sIfhtzUNq003Uyo1mYL2H1q5w5WeO2NwegsIs0Bw2ddJb5yCOjq2IqyoKPvPLi7uk52d+EZO3V8aaFSSr9wSpZrVUOdqDpKmC8X2GVIXcqonrHUDUXd+rQTlbDAlgdua7POTHnotr"), b64tohex("AQAB"));
			var enPassword = hex2b64(rsaKey.encrypt($password.val()));
			$enPassword.val(enPassword);
		});
		
		
	});
</script>

</head>
<body>
	
		<div class="login">
			<form id="loginForm" action="${ctx}/login" method="post">
				<input type="hidden" id="enPassword" name="enPassword" />
				
					<input type="hidden" name="captchaId" value="bdf261f4-c598-42cf-81c2-fc80c3415199" />
				
				<table>
					<tr>
						<td width="190" rowspan="2" align="center" valign="bottom">
							<img src="${ctx}/images/login_logo.gif" alt="SHOP++" />
						</td>
						<th>
							用户名:
						</th>
						<td>
							<input type="text" id="username" name="username" value="root" class="text" maxlength="20" />
						</td>
					</tr>
					<tr>
						<th>
							密&nbsp;&nbsp;&nbsp;码:
						</th>
						<td>
							<input type="password" id="password" name="password" value="root" class="text" maxlength="20" autocomplete="off" />
						</td>
					</tr>
					
						<tr>
							<td>
								&nbsp;
							</td>
							<th>
								验证码:
							</th>
							<td>
								<input type="text" id="captcha" name="captcha" value="aaaa" class="text captcha" maxlength="4" autocomplete="off" /><img id="captchaImage" class="captchaImage" src="/shopxx/admin/common/captcha.jhtml?captchaId=bdf261f4-c598-42cf-81c2-fc80c3415199" title="点击更换验证码" />
							</td>
						</tr>
					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							<label>
								<input type="checkbox" id="isRememberUsername" value="true" />
								记住用户名:
							</label>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/shopxx/'" /><input type="submit" class="loginButton" value="登录" />
						</td>
					</tr>
				</table>
				<div class="powered">COPYRIGHT © 2005-2013 SHOPXX.NET ALL RIGHTS RESERVED.</div>
				<div class="link">
					<a href="/shopxx/">前台首页</a> |
					<a href="http://www.shopxx.net">官方网站</a> |
					<a href="http://bbs.shopxx.net">交流论坛</a> |
					<a href="http://www.shopxx.net/about.html">关于我们</a> |
					<a href="http://www.shopxx.net/contact.html">联系我们</a> |
					<a href="http://www.shopxx.net/license.html">授权查询</a>
				</div>
			</form>
		</div>
	
</body>
</html>
