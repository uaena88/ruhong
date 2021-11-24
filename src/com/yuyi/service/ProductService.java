package com.yuyi.service;
/**
 * 這個類封裝了商品的操作
 * @author 育奕
 *
 */

import java.sql.SQLException;
import java.util.List;

import com.yuyi.bean.Category;
import com.yuyi.bean.Order;
import com.yuyi.bean.PageBean;
import com.yuyi.bean.Product;
import com.yuyi.dao.ProductDao;
import com.yuyi.util.C3P0UTils;

public class ProductService {
	/**
	 * 準備熱門商品
	 * 
	 * @return
	 */
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProduct = null;
		try {
			hotProduct = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProduct;
	}

	/**
	 * 准备最新商品
	 * 
	 * @return
	 */
	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> newProduct = null;
		try {
			newProduct = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProduct;
	}

	/**
	 * 查询全部类别
	 * 
	 * @return
	 */
	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> ListCategory = null;
		try {
			ListCategory = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ListCategory;
	}

	/**
	 * 查詢全部商品
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAllProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAllProduct();
	}

	/**
	 * 分页操作
	 * 
	 * @param currentPage：当前页
	 * @param currentCount：显示的条数
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Product> findPagebean(int currentPage, int currentCount) throws SQLException {
		ProductDao dao = new ProductDao();
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		// 总条数 private int totalCountl;
		int totalCount = dao.getTotalCount();
		pageBean.setTotalCountl(totalCount);
		// 总页数 private int totalPage;
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3.0 11 4 3 12 4 3 13 4 4 14 4 4 公式： 总的页数= 带小数 向上取整
		 * Math.ceil(总条数/当前页显示的条数)
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据 private List<T> productList = new ArrayList<T>();
		/*
		 * 页数与limit起始索引的关系 每页显示4条数据 页数 起始索引 每页显示的条数 1 0 4 2 4 4 3 8 4 4 12 4
		 * 
		 * 索引index = （当前页-1）*每页显示的条数
		 */
		int index = (currentPage - 1) * currentCount;
		List<Product> list = dao.findProductForPageBean(index, currentCount);
		pageBean.setProductList(list);
		return pageBean;
	}

	/**
	 * 根据类别做分页
	 * 
	 * @param cid
	 * @param currentPage
	 * @param currentCount
	 * @return
	 */
	public PageBean findProductListById(String cid, int currentPage, int currentCount) {
		ProductDao dao = new ProductDao();
		// 目的： 就是想办法封装一个pagebean 返回
		PageBean<Product> pageBean = new PageBean<Product>();
		// 当前页 private int currentPage;
		pageBean.setCurrentPage(currentPage);

		// 当前显示的条数 private int currentCount;
		pageBean.setCurrentCount(currentCount);

		// 总条数 private int totalCountl;
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCountl(totalCount);

		// 总页数 private int totalPage;
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3.0 11 4 3 12 4 3 13 4 4 14 4 4 公式： 总的页数= 带小数 向上取整
		 * Math.ceil(总条数/当前页显示的条数)
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据 private List<T> productList = new ArrayList<T>();
		/*
		 * 页数与limit起始索引的关系 每页显示4条数据 页数 起始索引 每页显示的条数 1 0 4 2 4 4 3 8 4 4 12 4
		 * 
		 * 索引index = （当前页-1）*每页显示的条数
		 */
		int index = (currentPage - 1) * currentCount;

		List<Product> list = null;
		try {
			list = dao.findProductListById(cid, index, currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setProductList(list);
		return pageBean;
	}

	/**
	 * 根据id查找商品的信息
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product findProductById(String pid) throws SQLException {
		ProductDao productDao = new ProductDao();
		Product product = productDao.findProductById(pid);
		return product;
	}

	/**
	 * 這個辦法是提交訂單的
	 *
	 * @param order
	 */
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();

		try {
			C3P0UTils.startTransaction();
			// 调用这个dao存储order这个表的数据的方法
			dao.addOrders(order);
			// 调用这个dao存储orderitem这个表的数据的方法
			dao.addOrderItem(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				C3P0UTils.commitAndRelease();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新訂單地址
	 * 
	 * @param order
	 */
	public void updateOrderAdrr(Order order) throws SQLException {
		ProductDao dao = new ProductDao();
		dao.updateOrderAdrr(order);
	}

	/**
	 * 更新訂單狀態
	 * 
	 * @param orderid
	 * @throws SQLException 
	 */
	public void updateOrderState(String orderid) throws SQLException {
		ProductDao dao = new ProductDao();
        dao.updateOrderState(orderid);
	}

}
