package com.vdc.dao;

import com.vdc.model.CustomerAccountLog;

public interface CustomerAccountLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(CustomerAccountLog record);

//    int insertSelective(CustomerAccountLog record);

    CustomerAccountLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(CustomerAccountLog record);

    int updateByPrimaryKey(CustomerAccountLog record);
}