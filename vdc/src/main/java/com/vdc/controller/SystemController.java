package com.vdc.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.vdc.dto.Pagination;
import com.vdc.dto.TreeObject;
import com.vdc.model.MenuInfo;
import com.vdc.model.RoleInfo;
import com.vdc.service.SystemService;
import com.vdc.util.JsonUtil;

@RequestMapping("/system")
@Controller
public class SystemController extends BaseController {

	@Autowired
	private SystemService systemService;

	/*****************************************************/
	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@RequestMapping("/menu/list")
	public ModelAndView listMenu() {
		ModelAndView mv = new ModelAndView("/system/menuList");
		return mv;
	}

	@RequestMapping("/menu/load")
	public void loadRootMenu(HttpServletResponse response) {
		this.buildMenuTree(null, response);
	}

	@RequestMapping("/menu/load/{parentMenuId}")
	public void loadChildMenu(@PathVariable Long parentMenuId, HttpServletResponse response) {
		this.buildMenuTree(parentMenuId, response);
	}

	private void buildMenuTree(Long parentMenuId, HttpServletResponse response) {
		List<TreeObject> treeList = this.systemService.getMenuTree(parentMenuId);
		Type tvListType = new TypeToken<List<TreeObject>>() {
		}.getType();
		String json = JsonUtil.objectToJson(treeList, tvListType);
		super.outputJson(json, response);
	}

	@RequestMapping("/menu/create")
	public ModelAndView createMenu(@RequestParam Long parentMenuId) {
		ModelAndView mv = new ModelAndView("/system/menuEdit");
		mv.addObject("parentMenuId", parentMenuId);
		return mv;
	}

	@RequestMapping("/menu/save")
	public void saveMenu(@ModelAttribute MenuInfo menuForm, HttpServletResponse response) {
		if (menuForm.getMenuName() == null || "".equals(menuForm.getMenuName().trim())) {
			super.outputJson(false, "菜单名称不能为空！", response);
		} else {
			MenuInfo parentMenuInDb = this.systemService.selectMenuInfoById(menuForm.getParentMenuId());
			menuForm.setMenuLevel(parentMenuInDb.getMenuLevel() + 1);
			this.systemService.insertMenuInfo(menuForm);
			super.outputJson(true, null, response);
		}
	}

	@RequestMapping("/menu/edit/{menuId}")
	public ModelAndView editMenu(@PathVariable Long menuId) {
		ModelAndView mv = new ModelAndView("/system/menuEdit");
		mv.addObject("menu", this.systemService.selectMenuInfoById(menuId));
		return mv;
	}

	@RequestMapping("/menu/update/{menuId}")
	public void updateMenu(@PathVariable Long menuId, @ModelAttribute MenuInfo menuForm, HttpServletResponse response) {
		MenuInfo menuInDb = this.systemService.selectMenuInfoById(menuId);
		menuInDb.setMenuName(menuForm.getMenuName());
		menuInDb.setMenuDescription(menuForm.getMenuDescription());
		menuInDb.setMenuUri(menuForm.getMenuUri());
		this.systemService.updateMenuInfo(menuInDb);
		super.outputJson(true, null, response);
	}

	@RequestMapping("/menu/delete/{menuId}")
	public void deleteMenu(@PathVariable Long menuId, HttpServletResponse response) {
		MenuInfo menuInDb = this.systemService.selectMenuInfoById(menuId);
		menuInDb.setIsEnabled(0);
		this.systemService.updateMenuInfo(menuInDb);
		super.outputJson(true, null, response);
	}

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
		ModelAndView mv = new ModelAndView("/system/roleList");

		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectRoleWithPagination(pagination, paramMap);

		return mv;
	}

	@RequestMapping("/role/create")
	public ModelAndView createRole() {
		ModelAndView mv = new ModelAndView("/system/roleEdit");
		return mv;
	}

	@RequestMapping("/role/save")
	public ModelAndView saveRole(@ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		this.systemService.insertRoleInfo(roleForm);

		return mv;
	}

	@RequestMapping("/role/edit/{roleId}")
	public ModelAndView editRole(@PathVariable Long roleId) {
		ModelAndView mv = new ModelAndView("/system/roleEdit");

		mv.addObject("role", this.systemService.selectRoleInfoById(roleId));

		return mv;
	}

	@RequestMapping("/role/update/{roleId}")
	public ModelAndView updateRole(@PathVariable Long roleId, @ModelAttribute RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

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
}
