package com.yuyi.test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.yuyi.bean.Category;
import com.yuyi.bean.Product;
import com.yuyi.dao.AdminProductDao;

import javafx.print.PageOrientation;

public class productTest {
	public static void main(String[] args) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
//		List<Product> lists = dao.findAllProduct();
//		System.out.println(lists.size());
//		Iterator<Product> iterator=lists.iterator();
//		while(iterator.hasNext()) {
//			Product product=iterator.next();
//			System.out.println(product.getPname());
//			System.out.println("===============");
//			Product product2=new Product();
//			
//		}
		Product product = new Product();
		product.setPid("1");
		product.setMarket_price(2200);
		product.setShop_price(3000);
		product.setPdesc("小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待");
		product.setPname("小米4c");
		Product product2 = dao.updateProductById(product);
		System.out.println("product2.getPid=" + product2.getPid() + ",product2.getPname=" + product2.getPname()+",product2.getMarket_Price="+product2.getMarket_price());
		
	}
}
