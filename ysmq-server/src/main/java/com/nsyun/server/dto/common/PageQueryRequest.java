package com.nsyun.server.dto.common;
import java.io.Serializable;
public class PageQueryRequest implements Serializable {

	/**
	 * currentPage 当前页
	 */
	private Integer currentPage;

	/**
	 * pageSize 每页显示的条数
	 */
	private Integer pageSize;
	
	private String sortName;//排序名称
	
	private boolean sortType = true;//排序类型 true asc, flas desc;

	/**
	 * sEcho 当前点击数，默认为1,用于接收datatables插件参数
	 */

	private int sEcho;

	/**
	 * iDisplayStart 查询起始数，默认为0,用于接收datatables插件参数
	 */

	private int iDisplayStart;

	/**
	 * iDisplayLength 每页显示条数,用于接收datatables插件参数
	 */

	private int iDisplayLength;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.iDisplayLength = pageSize;

	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		this.iDisplayStart = this.getIDisplayLength() * (currentPage - 1);
	}

	/**
	 * 设置datatables插件当前页数,并计算查询请求当前页数
	 *
	 * @param iDisplayStart
	 */
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
		// 查询起始数(从0开始)+每页显示数/每页显示数=当前页数
		this.currentPage = iDisplayStart / this.getIDisplayLength() + 1;
	}

	/**
	 * 设置datatables插件每页显示条数,并设置查询请求每页条数
	 *
	 * @param iDisplayLength
	 */
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
		this.pageSize = iDisplayLength;
	}

	public Integer getCurrentPage() {
		return this.currentPage == null || this.currentPage <= 0 ? 1 : this.currentPage;
	}

	public Integer getPageSize() {
		return this.pageSize == null || this.pageSize <= 0 ? 10 : this.pageSize;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getIDisplayStart() {
		return iDisplayStart;
	}

	public int getIDisplayLength() {
		return iDisplayLength;
	}
	

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public boolean isSortType() {
		return sortType;
	}

	public void setSortType(boolean sortType) {
		this.sortType = sortType;
	}


}
