package com.vdc.dao;

import java.util.List;
import java.util.Map;

import com.vdc.dto.TreeObject;
import com.vdc.model.MenuInfo;

public interface MenuInfoMapper {
	List<TreeObject> selectMenuForTree(Map<String, Object> paramMap);

	int countMenu(Map<String, Object> paramMap);

	List<MenuInfo> selectMenu(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long menuId);

	int insert(MenuInfo record);

	// int insertSelective(MenuInfo record);

	MenuInfo selectByPrimaryKey(Long menuId);

	int updateByPrimaryKeySelective(MenuInfo record);

	int updateByPrimaryKey(MenuInfo record);
}