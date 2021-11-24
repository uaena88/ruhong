package com.yuyi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    //訂單id
	private String oid;
	//訂單時間
	private Date ordertime;
	//訂單的總數
	private double total;
	//訂單的狀態
	private int state;
	//訂單的地址
	private String address;
	//用戶的名字
	private String name;
	//用戶的號碼
	private String telephone;
	//用戶
	private User user;
	//orderItem
	List<orderItem> orderItems=new ArrayList<orderItem>();
	
	public List<orderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<orderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
