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
import com.vdc.enums.RoleEnum;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;

@RequestMapping("/setting")
@Controller
public class SettingController extends BaseController {

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
		ModelAndView mv = new ModelAndView("/setting/roleList");

		paramMap.put("customerId", 1L);
		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectRoleWithPagination(pagination, paramMap);

		return mv;
	}

	@RequestMapping("/role/create")
	public ModelAndView createRole() {
		ModelAndView mv = new ModelAndView("/setting/roleEdit");
		return mv;
	}

	@RequestMapping("/role/save")
	public ModelAndView saveRole(@ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/setting/role/list");

		roleForm.setCustomerId(1L);
		roleForm.setParentRoleId(RoleEnum.CUSTOMER_ADMIN.getRoleId());
		this.systemService.insertRoleInfo(roleForm);

		return mv;
	}

	@RequestMapping("/role/edit/{roleId}")
	public ModelAndView editRole(@PathVariable Long roleId) {
		ModelAndView mv = new ModelAndView("/setting/roleEdit");

		mv.addObject("role", this.systemService.selectRoleInfoById(roleId));

		return mv;
	}

	@RequestMapping("/role/update/{roleId}")
	public ModelAndView updateRole(@PathVariable Long roleId, @ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/setting/role/list");

		RoleInfo roleInDb = this.systemService.selectRoleInfoById(roleId);
		roleInDb.setRoleName(roleForm.getRoleName());
		this.systemService.updateRoleInfo(roleInDb);

		return mv;
	}

	@RequestMapping("/role/delete")
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
	@RequestMapping("/user/list")
	public ModelAndView listUser(@ModelAttribute Pagination<UserInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = new ModelAndView("/setting/userList");

		paramMap.put("customerId", 1L);
		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectUserWithPagination(pagination, paramMap);

		return mv;
	}
}
