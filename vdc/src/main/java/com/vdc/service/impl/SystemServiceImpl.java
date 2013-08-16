package com.vdc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdc.dao.CustomerAccountLogMapper;
import com.vdc.dao.CustomerInfoMapper;
import com.vdc.dao.MenuInfoMapper;
import com.vdc.dao.RoleInfoMapper;
import com.vdc.dao.UserInfoMapper;
import com.vdc.dto.Pagination;
import com.vdc.dto.TreeObject;
import com.vdc.enums.RoleEnum;
import com.vdc.model.CustomerAccountLog;
import com.vdc.model.CustomerInfo;
import com.vdc.model.MenuInfo;
import com.vdc.model.RoleInfo;
import com.vdc.model.UserInfo;
import com.vdc.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private MenuInfoMapper menuInfoMapper;

	@Autowired
	private RoleInfoMapper roleInfoMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private CustomerAccountLogMapper customerAccountLogMapper;

	/**
	 * 生成菜单树
	 * 
	 * @return
	 */
	public List<TreeObject> getMenuTree(Long parentMenuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentMenuId", parentMenuId);
		List<TreeObject> treeList = this.menuInfoMapper.selectMenuForTree(paramMap);
		return treeList;
	}

	public List<MenuInfo> selectMenu(Map<String, Object> paramMap) {
		return this.menuInfoMapper.selectMenu(paramMap);
	}

	public MenuInfo selectMenuInfoById(Long id) {
		return this.menuInfoMapper.selectByPrimaryKey(id);
	}

	public void insertMenuInfo(MenuInfo record) {
		record.setIsEnabled(1);
		this.menuInfoMapper.insert(record);
	}

	public void updateMenuInfo(MenuInfo record) {
		this.menuInfoMapper.updateByPrimaryKey(record);
	}

	public Pagination<RoleInfo> selectRoleWithPagination(Pagination<RoleInfo> pagination, Map<String, Object> paramMap) {
		int numFound = this.roleInfoMapper.countRole(paramMap);
		pagination.setNumFound(numFound);
		paramMap.put("startIndex", pagination.getStartIndex());
		paramMap.put("pageSize", pagination.getPageSize());
		List<RoleInfo> roleList = this.roleInfoMapper.selectRole(paramMap);
		pagination.setData(roleList);
		return pagination;
	}

	public RoleInfo selectRoleInfoById(Long id) {
		return this.roleInfoMapper.selectByPrimaryKey(id);
	}

	public void insertRoleInfo(RoleInfo record) {
		record.setIsEnabled(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		this.roleInfoMapper.insert(record);
	}

	public void updateRoleInfo(RoleInfo record) {
		record.setUpdateTime(new Date());
		this.roleInfoMapper.updateByPrimaryKey(record);
	}

	public Pagination<UserInfo> selectUserWithPagination(Pagination<UserInfo> pagination, Map<String, Object> paramMap) {
		int numFound = this.userInfoMapper.countUser(paramMap);
		pagination.setNumFound(numFound);
		paramMap.put("startIndex", pagination.getStartIndex());
		paramMap.put("pageSize", pagination.getPageSize());
		List<UserInfo> userList = this.userInfoMapper.selectUser(paramMap);
		pagination.setData(userList);
		return pagination;
	}

	public UserInfo selectUserByName(String userName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("startIndex", 0);
		paramMap.put("pageSize", 1);
		List<UserInfo> userList = this.userInfoMapper.selectUser(paramMap);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	public UserInfo selectUserInfoById(Long id) {
		return this.userInfoMapper.selectByPrimaryKey(id);
	}

	public void insertUserInfo(UserInfo record) {
		record.setIsLocked(0);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		this.userInfoMapper.insert(record);
	}

	public void updateUserInfo(UserInfo record) {
		record.setUpdateTime(new Date());
		this.userInfoMapper.updateByPrimaryKey(record);
	}

	public Pagination<CustomerInfo> selectCustomerWithPagination(Pagination<CustomerInfo> pagination, Map<String, Object> paramMap) {
		int numFound = this.customerInfoMapper.countCustomer(paramMap);
		pagination.setNumFound(numFound);
		paramMap.put("startIndex", pagination.getStartIndex());
		paramMap.put("pageSize", pagination.getPageSize());
		List<CustomerInfo> customerList = this.customerInfoMapper.selectCustomer(paramMap);
		pagination.setData(customerList);
		return pagination;
	}

	public CustomerInfo selectCustomerInfoById(Long id) {
		return this.customerInfoMapper.selectByPrimaryKey(id);
	}

	public void insertCustomerInfo(CustomerInfo record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setAccountBalance(BigDecimal.ZERO);
		this.customerInfoMapper.insert(record);

		String userName = record.getEmail() == null ? ("admin_" + record.getCustomerId()) : record.getEmail().trim();
		String password = "123456";
		UserInfo user = new UserInfo();
		user.setUserName(userName);
		user.setPassword(password);
		user.setCustomerId(record.getCustomerId());
		user.setRoleId(RoleEnum.CUSTOMER_ADMIN.getRoleId());
		user.setRealName(record.getCustomerName() + "（管理员）");
		user.setEmail(record.getEmail());
		user.setQq(record.getQq());
		user.setTelephone(record.getTelephone());
		user.setMobilephone(record.getMobilephone());
		user.setFax(record.getFax());
		this.insertUserInfo(user);
	}

	public void updateCustomerInfo(CustomerInfo record) {
		record.setUpdateTime(new Date());
		this.customerInfoMapper.updateByPrimaryKey(record);
	}

	public void insertCustomerAccountLog(CustomerAccountLog record) {
		this.customerAccountLogMapper.insert(record);
	}
}
