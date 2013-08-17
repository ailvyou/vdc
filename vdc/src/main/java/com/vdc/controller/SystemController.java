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
import com.vdc.model.CustomerInfo;
import com.vdc.model.MenuInfo;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;
import com.vdc.util.JsonUtil;

@RequestMapping("/system")
@Controller
public class SystemController extends BizBaseController {

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
		return super.listRole(pagination, paramMap);
	}

	@RequestMapping("/role/create")
	public ModelAndView createRole() {
		return super.createRole();
	}

	@RequestMapping("/role/save")
	public ModelAndView saveRole(@ModelAttribute RoleInfo roleForm) {
		return super.saveRole(roleForm);
	}

	@RequestMapping("/role/edit/{roleId}")
	public ModelAndView editRole(@PathVariable Long roleId) {
		return super.editRole(roleId);
	}

	@RequestMapping("/role/update/{roleId}")
	public ModelAndView updateRole(@PathVariable Long roleId, @ModelAttribute RoleInfo roleForm) {
		return super.updateRole(roleId, roleForm);
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
		return mv;
	}

	@RequestMapping("/user/create")
	public ModelAndView createUser() {
		ModelAndView mv = super.createUser();
		return mv;
	}

	@RequestMapping("/user/save")
	public ModelAndView saveUser(@ModelAttribute UserInfo userForm) {
		ModelAndView mv = super.saveUser(userForm);
		return mv;
	}

	@RequestMapping("/user/edit/{userId}")
	public ModelAndView editUser(@PathVariable Long userId) {
		ModelAndView mv = super.editUser(userId);
		return mv;
	}

	@RequestMapping("/user/update/{userId}")
	public ModelAndView updateUser(@PathVariable Long userId, @ModelAttribute UserInfo userForm) {
		ModelAndView mv = super.updateUser(userId, userForm);
		return mv;
	}

	@RequestMapping("/user/delete")
	public void deleteUser(@RequestParam String ids, HttpServletResponse response) {
		super.deleteUser(ids, response);
	}

	/*****************************************************/
	/**
	 * 客户列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/customer/list")
	public ModelAndView listCustomer(@ModelAttribute Pagination<CustomerInfo> pagination, @RequestParam Map<String, Object> paramMap) {
		ModelAndView mv = new ModelAndView("/system/customerList");

		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectCustomerWithPagination(pagination, paramMap);

		return mv;
	}

	@RequestMapping("/customer/create")
	public ModelAndView createCustomer() {
		ModelAndView mv = new ModelAndView("/system/customerEdit");
		return mv;
	}

	@RequestMapping("/customer/save")
	public ModelAndView saveCustomer(@ModelAttribute CustomerInfo customerForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/customer/list");

		this.systemService.insertCustomerInfo(customerForm);

		return mv;
	}

	@RequestMapping("/customer/edit/{customerId}")
	public ModelAndView editCustomer(@PathVariable Long customerId) {
		ModelAndView mv = new ModelAndView("/system/customerEdit");

		mv.addObject("customer", this.systemService.selectCustomerInfoById(customerId));

		return mv;
	}

	@RequestMapping("/customer/update/{customerId}")
	public ModelAndView updateCustomer(@PathVariable Long customerId, @ModelAttribute CustomerInfo customerForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/customer/list");

		CustomerInfo customerInDb = this.systemService.selectCustomerInfoById(customerId);
		customerInDb.setCustomerName(customerForm.getCustomerName());
		customerInDb.setContactName(customerForm.getContactName());
		customerInDb.setTelephone(customerForm.getTelephone());
		customerInDb.setMobilephone(customerForm.getMobilephone());
		customerInDb.setEmail(customerForm.getEmail());
		customerInDb.setQq(customerForm.getQq());
		customerInDb.setFax(customerForm.getFax());
		this.systemService.updateCustomerInfo(customerInDb);

		return mv;
	}
}
