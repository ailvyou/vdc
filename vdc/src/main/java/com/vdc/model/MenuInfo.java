package com.vdc.model;

import java.io.Serializable;

public class MenuInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -983684966936118038L;

	private Long menuId;

    private String menuName;

    private Long parentMenuId;

    private String menuDescription;

    private String menuUri;
    
    private Integer menuLevel;

    private Integer isEnabled;

    private Long allowRoleId;

    private Long limitRoleId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription == null ? null : menuDescription.trim();
    }

    public String getMenuUri() {
        return menuUri;
    }

    public void setMenuUri(String menuUri) {
        this.menuUri = menuUri == null ? null : menuUri.trim();
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getAllowRoleId() {
        return allowRoleId;
    }

    public void setAllowRoleId(Long allowRoleId) {
        this.allowRoleId = allowRoleId;
    }

    public Long getLimitRoleId() {
        return limitRoleId;
    }

    public void setLimitRoleId(Long limitRoleId) {
        this.limitRoleId = limitRoleId;
    }

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
    
    
}