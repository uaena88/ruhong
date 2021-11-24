package com.yuyi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 這個類是顯示頁數
 * @author 育奕
 *
 * @param <T>
 */
public class PageBean<T> {
	//當前頁數
	private int currentPage;
	//當前顯示的條數
	private int currentCount;
	//總條數
	private int totalCountl;
	//總頁數
	private int totalPage;
	//每頁顯示的數據
	private List<T> productList=new ArrayList<T>();
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCountl() {
		return totalCountl;
	}
	public void setTotalCountl(int totalCountl) {
		this.totalCountl = totalCountl;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getProductList() {
		return productList;
	}
	public void setProductList(List<T> productList) {
		this.productList = productList;
	}
	
}
