package com.vdc.dao;

import com.vdc.model.RoleMenuRef;

public interface RoleMenuRefMapper {
    int deleteByPrimaryKey(Long roleMenuId);

    int insert(RoleMenuRef record);

//    int insertSelective(RoleMenuRef record);

    RoleMenuRef selectByPrimaryKey(Long roleMenuId);

    int updateByPrimaryKeySelective(RoleMenuRef record);

    int updateByPrimaryKey(RoleMenuRef record);
}