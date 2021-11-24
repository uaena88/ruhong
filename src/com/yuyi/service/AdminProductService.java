package com.yuyi.service;

import java.sql.SQLException;
import java.util.List;

import com.yuyi.bean.Category;
import com.yuyi.bean.Product;
import com.yuyi.dao.AdminProductDao;
import com.yuyi.vo.Condition;

public class AdminProductService {
	/**
	 * 这个方法是查询Product表里面所有的商品
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAllProduct() throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findAllProduct();
	}

	/**
	 * 这个方法是添加商品信息
	 * 
	 * @param product
	 * @throws SQLException
	 */

	public void addProduct(Product product) throws SQLException {
		// 沒有複雜的業務，直接傳遞到dao層
		AdminProductDao dao = new AdminProductDao();
		dao.addProduct(product);
	}

	/**
	 * 这个方法是查询所有的商品类别的
	 * 
	 * @return
	 */
	public List<Category> findAllCategory() {
		AdminProductDao dao = new AdminProductDao();

		try {
			return dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 这个方法是删除商品的信息的
	 * 
	 * @param pid
	 * @throws SQLException
	 */
	public void delProductById(String pid) throws SQLException {
		// 沒有複雜的業務，直接傳遞到dao層
		AdminProductDao dao = new AdminProductDao();
		dao.delProductById(pid);

	}

	/**
	 * 这个方法是查詢一條商品数据
	 * 
	 * @param pid
	 * @throws SQLException
	 */
	public Product findProductById(String pid) throws SQLException {
		// 沒有複雜的業務，直接傳遞到dao層
		AdminProductDao dao = new AdminProductDao();
		return dao.findProductById(pid);

	}

	/**
	 * 這個方法是更新商品的數據
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public Product updateProductById(Product product) throws SQLException {
		// 沒有複雜的業務，直接傳遞到dao層
		AdminProductDao dao = new AdminProductDao();
		return dao.updateProductById(product);
	}
/**
 * 根据条件查询数据
 * @param condition
 * @return
 * @throws SQLException 
 */
	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findProductListByCondition(condition);
	}

}
