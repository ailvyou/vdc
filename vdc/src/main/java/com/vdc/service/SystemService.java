package com.vdc.service;

import java.util.List;
import java.util.Map;

import com.vdc.dto.Pagination;
import com.vdc.dto.TreeObject;
import com.vdc.model.CustomerAccountLog;
import com.vdc.model.CustomerInfo;
import com.vdc.model.MenuInfo;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;

/**
 * 系统管理接口：包括菜单、角色、用户、客户
 * 
 * @author daniel
 * 
 */
public interface SystemService {

	public List<TreeObject> getMenuTree(Long parentMenuId);

	public List<MenuInfo> selectMenu(Map<String, Object> paramMap);

	public MenuInfo selectMenuInfoById(Long id);

	public void insertMenuInfo(MenuInfo record);

	public void updateMenuInfo(MenuInfo record);

	public List<RoleInfo> selectRole(Map<String, Object> paramMap);

	public Pagination<RoleInfo> selectRoleWithPagination(Pagination<RoleInfo> pagination, Map<String, Object> paramMap);

	public RoleInfo selectRoleInfoById(Long id);

	public void insertRoleInfo(RoleInfo record);

	public void updateRoleInfo(RoleInfo record);

	public Pagination<UserInfo> selectUserWithPagination(Pagination<UserInfo> pagination, Map<String, Object> paramMap);

	public UserInfo selectUserByName(String userName);

	public UserInfo selectUserInfoById(Long id);

	public void insertUserInfo(UserInfo record);

	public void updateUserInfo(UserInfo record);

	public Pagination<CustomerInfo> selectCustomerWithPagination(Pagination<CustomerInfo> pagination, Map<String, Object> paramMap);

	public CustomerInfo selectCustomerInfoById(Long id);

	public void insertCustomerInfo(CustomerInfo record);

	public void updateCustomerInfo(CustomerInfo record);

	public void insertCustomerAccountLog(CustomerAccountLog record);
}
