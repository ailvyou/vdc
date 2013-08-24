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
	public ModelAndView listRole(Pagination<RoleInfo> pagination, Map<String, Object> paramMap) {
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

	public ModelAndView saveRole(RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		this.systemService.insertRoleInfo(roleForm);

		return mv;
	}

	public ModelAndView editRole(Long roleId) {
		ModelAndView mv = new ModelAndView("/system/roleEdit");

		mv.addObject("role", this.systemService.selectRoleInfoById(roleId));

		return mv;
	}

	public ModelAndView updateRole(Long roleId, RoleInfo roleForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/role/list");

		RoleInfo roleInDb = this.systemService.selectRoleInfoById(roleId);
		roleInDb.setRoleName(roleForm.getRoleName());
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

	public void loadMenuTree(Long roleId, HttpServletResponse response) {
		TreeObject tree = this.systemService.getMenuTreeByRoleId(roleId);
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		treeList.add(tree);
		Type treeListType = new TypeToken<List<TreeObject>>() {
		}.getType();
		String json = JsonUtil.objectToJson(treeList, treeListType);
		this.outputJson(json, response);
	}

	public void doAuthorize(Long roleId, String roleMenuData, HttpServletResponse response) {
		// try {
		if (roleMenuData != null && !"".equals(roleMenuData)) {
			// [{id:111,pid:1,ppid:0}]
			Type treeListType = new TypeToken<List<TreeObject>>() {
			}.getType();
			@SuppressWarnings("unchecked")
			List<TreeObject> treeList = (List<TreeObject>) JsonUtil.jsonToObject(roleMenuData, treeListType);
			List<RoleMenuRef> rmList = new ArrayList<RoleMenuRef>();
			Set<String> pidSet = new HashSet<String>();
			Set<String> ppidSet = new HashSet<String>();
			Set<String> addedIdSet = new HashSet<String>();
			for (TreeObject tree : treeList) {
				String id = tree.getId();
				String pid = tree.getPid();
				String ppid = tree.getPpid();
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(id), 1L);
				rmList.add(rm);

				pidSet.add(pid);
				ppidSet.add(ppid);
				addedIdSet.add(id);
			}
			// JSONArray jSONArray = new JSONArray(roleMenuData);
			// for (int i = 0; i < jSONArray.length(); i++) {
			// RoleFunction fun = new RoleFunction();
			// JSONObject jsonObj = jSONArray.getJSONObject(i);
			// long functionId = jsonObj.getLong("functionId");
			// long parentId = jsonObj.getLong("parentId");
			// long pPrentId = jsonObj.getLong("pPrentId");
			// fun.setUpdateUserId(this.getCurrentUserId());
			// fun.setPostUserId(this.getCurrentUserId());
			// fun.setUpdateUserId(this.getCurrentUserAreaId());
			// fun.setFunctionId(functionId);
			// fun.setRoleId(this.roleId);
			// fun.setShowLink(1);
			// fun.setOpe("");
			// rmList.add(fun);
			//
			// pidSet.add(parentId);
			// ppidSet.add(pPrentId);
			//
			// addedIdSet.add(functionId);
			// }
			for (String pid : pidSet) {
				if (pid == null || "".equals(pid) || addedIdSet.contains(pid)) {
					continue;
				}
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(pid), 1L);
				rmList.add(rm);

				addedIdSet.add(pid);
			}
			// for (long parentId : pidSet) {
			// if (parentId == 0 || parentId == 1 ||
			// addedIdSet.contains(parentId)) {
			// continue;
			// }
			// RoleFunction fun = new RoleFunction();
			// fun.setUpdateUserId(this.getCurrentUserId());
			// fun.setPostUserId(this.getCurrentUserId());
			// fun.setUpdateUserId(this.getCurrentUserAreaId());
			// fun.setFunctionId(parentId);
			// fun.setRoleId(this.roleId);
			// fun.setShowLink(1);
			// fun.setOpe("");
			// rmList.add(fun);
			//
			// addedIdSet.add(parentId);
			// }

			for (String ppid : ppidSet) {
				if (ppid == null || "".equals(ppid) || addedIdSet.contains(ppid)) {
					continue;
				}
				RoleMenuRef rm = new RoleMenuRef(roleId, Long.valueOf(ppid), 1L);
				rmList.add(rm);

				addedIdSet.add(ppid);
			}
			// for (long pParentId : ppidSet) {
			// if (pParentId == 0 || pParentId == 1 ||
			// addedIdSet.contains(pParentId)) {
			// continue;
			// }
			// RoleFunction fun = new RoleFunction();
			// fun.setUpdateUserId(this.getCurrentUserId());
			// fun.setPostUserId(this.getCurrentUserId());
			// fun.setUpdateUserId(this.getCurrentUserAreaId());
			// fun.setFunctionId(pParentId);
			// fun.setRoleId(this.roleId);
			// fun.setShowLink(1);
			// fun.setOpe("");
			// rmList.add(fun);
			//
			// addedIdSet.add(pParentId);
			// }
			this.systemService.saveRoleMenuList(roleId, rmList);
			// this.sysRoleService.editRoleAuthority(roleId, rmList);
			super.outputJson(true, "保存成功", response);
		} else {
			super.outputJson(false, "请选择菜单项", response);
		}
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
	}

	/*****************************************************/
	/**
	 * 用户列表
	 * 
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public ModelAndView listUser(Pagination<UserInfo> pagination, Map<String, Object> paramMap) {
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

	public ModelAndView saveUser(UserInfo userForm) {
		ModelAndView mv = new ModelAndView("redirect:/system/user/list");

		userForm.setCustomerId(1L);
		this.systemService.insertUserInfo(userForm);

		return mv;
	}

	public ModelAndView editUser(Long userId) {
		ModelAndView mv = new ModelAndView("/system/userEdit");

		mv.addObject("user", this.systemService.selectUserInfoById(userId));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", 1L);
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", Integer.MAX_VALUE);
		mv.addObject("roleList", this.systemService.selectRole(paramMap));
		return mv;
	}

	public ModelAndView updateUser(Long userId, UserInfo userForm) {
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
