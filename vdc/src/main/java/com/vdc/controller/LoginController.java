package com.vdc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vdc.enums.RoleEnum;
import com.vdc.model.MenuInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private SystemService systemService;

	//
	// @RequestMapping("/login")
	// public ModelAndView login(@RequestParam String username, @RequestParam
	// String password, @RequestParam String captcha) {
	// UserInfo user = systemService.selectUserByName(username);
	// if (user == null || !password.equals(user.getPassword())) {
	// return new ModelAndView("/login");
	// }
	// ModelAndView mv = new ModelAndView("/main");
	// initMenuList(mv, user.getRoleId());
	// return mv;
	// }

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, @RequestParam String captcha) {
		UserInfo user = systemService.selectUserByName(username);
		if (user == null || !password.equals(user.getPassword())) {
			return new ModelAndView("/login");
		}
		ModelMap map = new ModelMap();
		map.addAttribute("roleId", user.getRoleId());
		return new ModelAndView("redirect:/main", map);
	}

	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/main");
		Long roleId = null;
		try {
			roleId = Long.valueOf(req.getParameter("roleId"));
		} catch (Exception e) {
		}
		initMenuList(mv, roleId);
		return mv;
	}

	private void initMenuList(ModelAndView mv, Long roleId) {
		Map<Long, List<MenuInfo>> menuMap = new LinkedHashMap<Long, List<MenuInfo>>();
		if (roleId != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (RoleEnum.ROOT.getRoleId() != roleId) {
				// add other criteria
				paramMap.put("roleId", roleId);
			}
			paramMap.put("excludeButton", true);
			List<MenuInfo> menuList = this.systemService.selectMenu(paramMap);
			for (MenuInfo menu : menuList) {
				if (menu.getMenuLevel() == 1) {
					List<MenuInfo> list = new ArrayList<MenuInfo>();
					list.add(menu);
					menuMap.put(menu.getMenuId(), list);
				}
			}
			for (MenuInfo menu : menuList) {
				if (menu.getMenuLevel() == 2) {
					Long key = menu.getParentMenuId();
					List<MenuInfo> value = menuMap.get(key);
					if (value == null) {
						value = new ArrayList<MenuInfo>();
					}
					value.add(menu);
					menuMap.put(key, value);
				}
			}
		}
		mv.addObject("menuMap", menuMap);
	}
}
