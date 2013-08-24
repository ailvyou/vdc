/**
 * 
 */
package com.vdc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.vdc.dto.Pagination;
import com.vdc.dto.TreeObject;
import com.vdc.model.RoleInfo;
import com.vdc.model.RoleMenuRef;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;
import com.vdc.util.JsonUtil;

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
	public ModelAndView listRole(Pagination<RoleInfo> pagination, Map<String, Object> paramMap, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/system/roleList");

		Long currentCustomerId = super.getCurrentCustomerId(req);
		if (currentCustomerId == null) {
			paramMap.put("customerIdIsNull", true);
		} else {
			paramMap.put("customerId", super.getCurrentCustomerId(req));
		}
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

	public ModelAndView saveRole(RoleInfo roleForm, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		roleForm.setCustomerId(super.getCurrentCustomerId(req));
		roleForm.setCreateBy(super.getCurrentUserId(req));
		roleForm.setUpdateBy(super.getCurrentUserId(req));
		this.systemService.insertRoleInfo(roleForm);

		return mv;
	}

	public ModelAndView editRole(Long roleId) {
		ModelAndView mv = new ModelAndView("/system/roleEdit");

		mv.addObject("role", this.systemService.selectRoleInfoById(roleId));

		return mv;
	}

	public ModelAndView updateRole(Long roleId, RoleInfo roleForm, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		RoleInfo roleInDb = this.systemService.selectRoleInfoById(roleId);
		roleInDb.setRoleName(roleForm.getRoleName());
		roleInDb.setUpdateBy(super.getCurrentUserId(req));
		this.systemService.updateRoleInfo(roleInDb);

		return mv;
	}

	public void deleteRole(String ids, HttpServletResponse response) {
		String[] tempArr = ids.split(",");
		for (String id : tempArr) {
			RoleInfo roleInDb = this.systemService.selectRoleInfoById(Long.valueOf(id));
			roleInDb.setIsEnabled(0);
			this.systemService.updateRoleInfo(roleInDb);
		}
		super.outputJson(true, null, response);
	}

	public ModelAndView gotoAuthorize(Long roleId) {
		ModelAndView mv = new ModelAndView("/system/roleAuthorize");

		return mv;
	}

	public void loadMenuTree(Long roleId, HttpServletRequest req, HttpServletResponse response) {
		TreeObject tree = this.systemService.getMenuTreeByRoleId(roleId);
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		treeList.add(tree);
		Type treeListType = new TypeToken<List<TreeObject>>() {
		}.getType();
		String json = JsonUtil.objectToJson(treeList, treeListType);
		this.outputJson(json, response);
	}

	public void doAuthorize(Long roleId, String roleMenuData, HttpServletRequest req, HttpServletResponse response) {
		if (roleMenuData != null && !"".equals(roleMenuData.trim()) && !"[]".equals(roleMenuData.trim())) {
			// [{id:111,pid:1,ppid:0}]
			Type treeListType = new TypeToken<List<TreeObject>>() {
			}.getType();
			@SuppressWarnings("unchecked")
			List<TreeObject> treeList = (List<TreeObject>) JsonUtil.jsonToObject(roleMenuData, treeListType);
			List<RoleMenuRef> rmList = new ArrayList<RoleMenuRef>();
			Set<String> pidSet = new HashSet<String>();
			Set<String> ppidSet = new HashSet<String>();
			Set<String> addedIdSet = new HashSet<String>();
			Long createBy = super.getCurrentUserId(req);
			for (TreeObject tree : treeList) {
				String id = tree.getId();
				String pid = tree.getPid();
				String ppid = tree.getPpid();
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(id), createBy);
				rmList.add(rm);

				pidSet.add(pid);
				ppidSet.add(ppid);
				addedIdSet.add(id);
			}
			for (String pid : pidSet) {
				if (pid == null || "".equals(pid) || "0".equals(pid) || addedIdSet.contains(pid)) {
					continue;
				}
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(pid), createBy);
				rmList.add(rm);

				addedIdSet.add(pid);
			}
			for (String ppid : ppidSet) {
				if (ppid == null || "".equals(ppid) || "0".equals(ppid) || addedIdSet.contains(ppid)) {
					continue;
				}
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(ppid), createBy);
				rmList.add(rm);

				addedIdSet.add(ppid);
			}
			this.systemService.saveRoleMenuList(roleId, rmList);
			super.outputJson(true, "保存成功", response);
		} else {
			super.outputJson(false, "请选择菜单项", response);
		}
	}

	/*****************************************************/
	/**
	 * 用户列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public ModelAndView listUser(Pagination<UserInfo> pagination, Map<String, Object> paramMap, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/system/userList");

		Long currentCustomerId = super.getCurrentCustomerId(req);
		if (currentCustomerId == null) {
			paramMap.put("customerIdIsNull", true);
		} else {
			paramMap.put("customerId", super.getCurrentCustomerId(req));
		}
		if (paramMap.get("searchProperty") != null && !"".equals(paramMap.get("searchProperty").toString())) {
			paramMap.put(paramMap.get("searchProperty").toString(), paramMap.get("searchValue").toString());
		}
		this.systemService.selectUserWithPagination(pagination, paramMap);

		return mv;
	}

	public ModelAndView createUser(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/system/userEdit");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", super.getCurrentCustomerId(req));
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", Integer.MAX_VALUE);
		mv.addObject("roleList", this.systemService.selectRole(paramMap));
		return mv;
	}

	public ModelAndView saveUser(UserInfo userForm, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:/system/user/list");

		userForm.setCustomerId(super.getCurrentCustomerId(req));
		userForm.setCreateBy(super.getCurrentUserId(req));
		userForm.setUpdateBy(super.getCurrentUserId(req));
		this.systemService.insertUserInfo(userForm);

		return mv;
	}

	public ModelAndView editUser(Long userId, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/system/userEdit");

		mv.addObject("user", this.systemService.selectUserInfoById(userId));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", super.getCurrentCustomerId(req));
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", Integer.MAX_VALUE);
		mv.addObject("roleList", this.systemService.selectRole(paramMap));
		return mv;
	}

	public ModelAndView updateUser(Long userId, UserInfo userForm, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:/system/user/list");

		UserInfo userInDb = this.systemService.selectUserInfoById(userId);
		userInDb.setRoleId(userForm.getRoleId());
		userInDb.setRealName(userForm.getRealName());
		userInDb.setEmail(userForm.getEmail());
		userInDb.setQq(userForm.getQq());
		userInDb.setTelephone(userForm.getTelephone());
		userInDb.setMobilephone(userForm.getMobilephone());
		userInDb.setFax(userForm.getFax());
		userInDb.setUpdateBy(super.getCurrentUserId(req));
		this.systemService.updateUserInfo(userInDb);

		return mv;
	}

	public void deleteUser(String ids, HttpServletResponse response) {
		String[] tempArr = ids.split(",");
		for (String id : tempArr) {
			UserInfo userInDb = this.systemService.selectUserInfoById(Long.valueOf(id));
			userInDb.setIsLocked(1);
			this.systemService.updateUserInfo(userInDb);
		}
		super.outputJson(true, null, response);
	}
}
