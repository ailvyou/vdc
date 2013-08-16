package com.vdc.dao;

import java.util.List;
import java.util.Map;

import com.vdc.model.CustomerInfo;

public interface CustomerInfoMapper {
	int countCustomer(Map<String,Object> paramMap);
	
	List<CustomerInfo> selectCustomer(Map<String,Object> paramMap);
	
    int deleteByPrimaryKey(Long customerId);

    int insert(CustomerInfo record);

//    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);
}