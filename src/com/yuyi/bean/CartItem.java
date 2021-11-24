package com.yuyi.bean;
/**
 * 購物車項對象
 * @author 育奕
 *
 */
public class CartItem {

	//商品
	private Product product;
	//購買數量
	private int buyNum;
	//商品的小計
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
