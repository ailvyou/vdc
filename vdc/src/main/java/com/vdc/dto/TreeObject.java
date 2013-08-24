package com.vdc.dto;

import java.io.Serializable;
import java.util.List;

public class TreeObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1498196896321818062L;

	private String id;
	private String pid;
	private String text;
	private String state;
	private String checked;
	private List<TreeObject> children;
	private String ppid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public List<TreeObject> getChildren() {
		return children;
	}

	public void setChildren(List<TreeObject> children) {
		this.children = children;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

}
