package com.vdc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vdc.constants.Constants;

@Controller
public class LogoutController extends BaseController {

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		req.getSession().removeAttribute(Constants.USER_INFO);
		req.getSession().invalidate();
		return new ModelAndView("/login");
	}
}
