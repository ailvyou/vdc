package com.vdc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vdc.dto.Pagination;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;

@RequestMapping("/setting")
@Controller
public class SettingController extends BizBaseController {

	@Autowired
	private SystemService systemService;

	/*****************************************************/
	/**
	 * 角色列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/role/list")
	public ModelAndView listRole(@ModelAttribute Pagination<RoleInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = super.listRole(pagination, paramMap);
		mv.setViewName("/setting/roleList");
		return mv;
	}

	@RequestMapping("/role/create")
	public ModelAndView createRole() {
		ModelAndView mv = super.createRole();
		mv.setViewName("/setting/roleEdit");
		return mv;
	}

	@RequestMapping("/role/save")
	public ModelAndView saveRole(@ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = super.saveRole(roleForm);
		mv.setViewName("redirct:/setting/role/list");
		return mv;
	}

	@RequestMapping("/role/edit/{roleId}")
	public ModelAndView editRole(@PathVariable Long roleId) {
		ModelAndView mv = super.editRole(roleId);
		mv.setViewName("/setting/roleEdit");
		return mv;
	}

	@RequestMapping("/role/update/{roleId}")
	public ModelAndView updateRole(@PathVariable Long roleId, @ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = super.updateRole(roleId, roleForm);
		mv.setViewName("redirct:/setting/role/list");
		return mv;
	}

	@RequestMapping("/role/delete")
	public void deleteRole(@RequestParam String ids, HttpServletResponse response) {
		super.deleteRole(ids, response);
	}

	/*****************************************************/
	/**
	 * 用户列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/user/list")
	public ModelAndView listUser(@ModelAttribute Pagination<UserInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = super.listUser(pagination, paramMap);
		mv.setViewName("/setting/userList");
		return mv;
	}

	@RequestMapping("/user/create")
	public ModelAndView createUser() {
		ModelAndView mv = super.createUser();
		mv.setViewName("/setting/userEdit");
		return mv;
	}

	@RequestMapping("/user/save")
	public ModelAndView saveUser(@ModelAttribute UserInfo userForm) {
		ModelAndView mv = super.saveUser(userForm);
		mv.setViewName("redirect:/setting/user/list");
		return mv;
	}

	@RequestMapping("/user/edit/{userId}")
	public ModelAndView editUser(@PathVariable Long userId) {
		ModelAndView mv = super.editUser(userId);
		mv.setViewName("/setting/userEdit");
		return mv;
	}

	@RequestMapping("/user/update/{userId}")
	public ModelAndView updateUser(@PathVariable Long userId, @ModelAttribute UserInfo userForm) {
		ModelAndView mv = super.updateUser(userId, userForm);
		mv.setViewName("redirect:/setting/user/list");
		return mv;
	}

	@RequestMapping("/user/delete")
	public void deleteUser(@RequestParam String ids, HttpServletResponse response) {
		super.deleteUser(ids, response);
	}
}
