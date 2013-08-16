package com.vdc.dao;

import java.util.List;
import java.util.Map;

import com.vdc.model.RoleInfo;

public interface RoleInfoMapper {
	int countRole(Map<String,Object> paramMap);
	
	List<RoleInfo> selectRole(Map<String,Object> paramMap);
	
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleInfo record);

//    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}