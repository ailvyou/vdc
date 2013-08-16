package com.vdc.enums;

public enum RoleEnum {
	ROOT(1L),
	;
	private Long roleId;
	
	private RoleEnum(Long roleId){
		this.roleId=roleId;
	}
	
	public Long getRoleId(){
		return this.roleId;
	}
}
