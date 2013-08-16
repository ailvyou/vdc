package com.vdc.dao;

import java.util.List;
import java.util.Map;

import com.vdc.model.UserInfo;

public interface UserInfoMapper {
	int countUser(Map<String,Object> paramMap);
	
	List<UserInfo> selectUser(Map<String,Object> paramMap);
	
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

//    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}