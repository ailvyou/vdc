package com.vdc.dto;

import java.io.Serializable;
import java.util.List;

public class Pagination<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3050871755743648466L;

	/**
	 * 当前页码
	 */
	private long pageNo=1;
	
	/**
	 * 每页显示多少条记录
	 */
	private long pageSize=3;

	/**
	 * 表示总共搜索到多少条记录
	 */
	private long numFound;
	
	/**
	 * 数据列表
	 */
	private List<E> data;
	
	public Pagination(){
	}
	public Pagination(long pageNo){
		this.pageNo=pageNo;
	}
	public Pagination(long pageNo, long pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public long getNumFound() {
		return numFound;
	}

	public void setNumFound(long numFound) {
		this.numFound = numFound;
	}
	
	public long getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	
	public long getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getTotalPages(){
		long totalPages=1;
		if (this.numFound < this.pageSize) {
			totalPages = 1;
		} else {
			if (this.numFound % this.pageSize == 0) {
				totalPages = this.numFound / this.pageSize;
			} else {
				totalPages = (this.numFound / this.pageSize) + 1;
			}
		}
		return totalPages;
	}
	
	public long getStartIndex(){
		return (this.pageNo-1)*this.pageSize;
	}
	
	public long getEndIndex(){
		return this.pageNo*this.pageSize;
	}
	
}
