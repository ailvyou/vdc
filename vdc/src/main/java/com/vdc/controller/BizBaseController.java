/**
 * 
 */
package com.vdc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vdc.dto.Pagination;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;

/**
 * 业务controller基类
 * 
 * @author daniel
 */
public class BizBaseController extends BaseController {
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
	public ModelAndView listRole(@ModelAttribute Pagination<RoleInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = new ModelAndView("/system/roleList");

		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectRoleWithPagination(pagination, paramMap);

		return mv;
	}

	public ModelAndView createRole() {
		ModelAndView mv = new ModelAndView("/system/roleEdit");
		return mv;
	}

	public ModelAndView saveRole(@ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		this.systemService.insertRoleInfo(roleForm);

		return mv;
	}

	public ModelAndView editRole(@PathVariable Long roleId) {
		ModelAndView mv = new ModelAndView("/system/roleEdit");

		mv.addObject("role", this.systemService.selectRoleInfoById(roleId));

		return mv;
	}

	public ModelAndView updateRole(@PathVariable Long roleId, @ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		RoleInfo roleInDb = this.systemService.selectRoleInfoById(roleId);
		roleInDb.setRoleName(roleForm.getRoleName());
		this.systemService.updateRoleInfo(roleInDb);

		return mv;
	}

	public void deleteRole(@RequestParam String ids, HttpServletResponse response) {
		String[] tempArr = ids.split(",");
		for (String id : tempArr) {
			RoleInfo roleInDb = this.systemService.selectRoleInfoById(Long.valueOf(id));
			roleInDb.setIsEnabled(0);
			this.systemService.updateRoleInfo(roleInDb);
		}
		super.outputJson(true, null, response);
	}

	/*****************************************************/
	/**
	 * 用户列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public ModelAndView listUser(@ModelAttribute Pagination<UserInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = new ModelAndView("/system/userList");

		paramMap.put("customerId", 1L);
		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectUserWithPagination(pagination, paramMap);

		return mv;
	}

	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView("/system/userEdit");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", 1L);
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", Integer.MAX_VALUE);
		mv.addObject("roleList", this.systemService.selectRole(paramMap));
		return mv;
	}

	public ModelAndView saveUser(@ModelAttribute UserInfo userForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/user/list");

		userForm.setCustomerId(1L);
		this.systemService.insertUserInfo(userForm);

		return mv;
	}

	public ModelAndView editUser(@PathVariable Long userId) {
		ModelAndView mv = new ModelAndView("/system/userEdit");

		mv.addObject("user", this.systemService.selectUserInfoById(userId));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", 1L);
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", Integer.MAX_VALUE);
		mv.addObject("roleList", this.systemService.selectRole(paramMap));
		return mv;
	}

	public ModelAndView updateUser(@PathVariable Long userId, @ModelAttribute UserInfo userForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/user/list");

		UserInfo userInDb = this.systemService.selectUserInfoById(userId);
		userInDb.setRoleId(userForm.getRoleId());
		userInDb.setRealName(userForm.getRealName());
		userInDb.setEmail(userForm.getEmail());
		userInDb.setQq(userForm.getQq());
		userInDb.setTelephone(userForm.getTelephone());
		userInDb.setMobilephone(userForm.getMobilephone());
		userInDb.setFax(userForm.getFax());
		this.systemService.updateUserInfo(userInDb);

		return mv;
	}

	public void deleteUser(@RequestParam String ids, HttpServletResponse response) {
		String[] tempArr = ids.split(",");
		for (String id : tempArr) {
			UserInfo userInDb = this.systemService.selectUserInfoById(Long.valueOf(id));
			userInDb.setIsLocked(1);
			this.systemService.updateUserInfo(userInDb);
		}
		super.outputJson(true, null, response);
	}
}
