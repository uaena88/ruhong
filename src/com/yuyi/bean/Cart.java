package com.yuyi.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 購物車類
 * @author 育奕
 *
 */
public class Cart {
	//购物车中存储的n多个购物项
	private Map<String, CartItem> cartItems=new HashMap<String,CartItem>();
	//商品的總金額
	private double total;
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
