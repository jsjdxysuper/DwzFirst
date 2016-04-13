package com.kedong.action;

import java.util.List;

public class Pager {
	private int totalCount;
	private int pageSize;
	private int pageNo;
	private int totalPage;
	private List pages;
	
	public Pager(){
		
	}
	public Pager(int pageNum, int numPerPage){
		pageNo = pageNum;
		pageSize = numPerPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getPages() {
		return pages;
	}
	public void setPages(List pages) {
		this.pages = pages;
	}
	
}
