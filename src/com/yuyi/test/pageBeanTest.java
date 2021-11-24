package com.yuyi.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yuyi.bean.Product;
import com.yuyi.dao.ProductDao;

public class pageBeanTest {
	public static void main(String[] args) throws SQLException {
		ProductDao dao = new ProductDao();
		Long result = (long) dao.getTotalCount();
		System.out.println("商品总条数result=" + result);
		List<Product> list = new ArrayList<Product>();
		int index = 2;
		int currentCount = 20;
		list = dao.findProductForPageBean(index, currentCount);
        Product product=null;
        Iterator<Product> it=list.iterator();
        while(it.hasNext()) {
        	product=it.next();
        	System.out.println("Pname="+product.getPname());
        }
	}
}
