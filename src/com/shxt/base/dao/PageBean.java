package com.shxt.base.dao;

import java.util.List;

public class PageBean {
	/**初始化每页现实5条*/
	private Integer pageSize = 5;
	/**初始化当前页为1*/
	private Integer pageNow = 1;
	/**总记录数*/
	private Long totalCount ;
	/**总页数*/
	private Long totalPages;
	/**记录集合*/
	private List<?> datas;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

}
