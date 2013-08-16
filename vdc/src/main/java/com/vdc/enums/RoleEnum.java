package com.vdc.enums;

/**
 * 角色枚举
 */
public enum RoleEnum {
	ROOT(1L), SYSTEM_ADMIN(2L), CUSTOMER_ADMIN(3L), ;
	private Long roleId;

	private RoleEnum(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return this.roleId;
	}
}
