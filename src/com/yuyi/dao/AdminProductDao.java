package com.yuyi.dao;

/**
 * 賬戶後臺管理商品的類，封裝了CRUD的方法
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yuyi.bean.Category;
import com.yuyi.bean.Product;
import com.yuyi.util.C3P0UTils;
import com.yuyi.vo.Condition;

public class AdminProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "SELECT * FROM product";
		List<Product> lists = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
		return lists;
	}

	public void addProduct(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		queryRunner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(),
				product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(),
				product.getPdesc(), product.getPflag(), product.getCategory());

	}

	public List<Category> findAllCategory() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "SELECT * FROM category";
		List<Category> lists = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
		return lists;
	}

	public void delProductById(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "DELETE  FROM product WHERE pid=?";
		queryRunner.update(sql, pid);
	}

	public Product findProductById(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid=?";
		Product query = queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return query;
	}

	public Product updateProductById(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "UPDATE product set pname=?,market_price=?,shop_price=?,pdesc=? WHERE pid=?";
		int num = queryRunner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPdesc(), product.getPid());
		if (num > 0) {
			System.out.println("更新成功！");
		}
		return product;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		//定义一个存储实际参数的容器
		 List<String> list = new ArrayList<String>();
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from product  where 1=1";
		if(condition.getPname()!=null && !condition.getPname().trim().equals("")) {
			//当我介入这个if 里面      是不是代表这个pname有值  做条件的拼接
			sql +=" and pname like ? ";
			list.add("%"+condition.getPname().trim()+"%");
		}
		if(condition.getIsHot()!=null && !condition.getIsHot().trim().equals("")) {
			sql +=" and is_hot=? ";
			list.add(condition.getIsHot().trim());
		}
		if(condition.getCid()!=null && !condition.getCid().trim().equals("")) {
			sql +=" and cid=? ";
			list.add(condition.getCid().trim());
		}
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
		return query;
	}
	//select * from product  where 1=1 and pname like ? and cid=? 
	}


