package com.yuyi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yuyi.bean.Category;
import com.yuyi.bean.Order;
import com.yuyi.bean.Product;
import com.yuyi.bean.orderItem;
import com.yuyi.util.C3P0UTils;
/**
 * 這個類封裝了操作商品分頁的方法
 * @author 育奕
 *
 */
public class ProductDao {
	/**
	 * 查詢所有的商品信息
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAllProduct() throws SQLException {

		return new QueryRunner(C3P0UTils.getDataSource()).query("select * from product",
				new BeanListHandler<Product>(Product.class));
	}
/**
 * 查詢商品的縂條數
 * @return
 * @throws SQLException
 */
	public int getTotalCount() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select count(*) from product";
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	/**
	 * 分页
	 * @param index:当前第几页
	 * @param currentCount：當前顯示的條數
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findProductForPageBean(int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from product limit ?,?";
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class), index, currentCount);

		return query;
	}
	/**
	 * 這個方法是查詢熱門商品
	 * @return
	 * @throws SQLException 
	 */
	public  List<Product> findHotProductList() throws SQLException{
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from product where is_hot=? limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
	}
	/**
	 * 查詢最新上架的商品
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findNewProductList() throws SQLException{
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from product order by pdate desc limit ?,?";//降序排序	
		return runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
	}
	/**
	 * 這個方法是查詢類別
	 * @throws SQLException 
	 */
	public List<Category> findAllCategory() throws SQLException{
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));	
	}
	/**
	 * 這個方法是根據cid來是實現分頁查詢																																																				
	 * @param cid
	 * @param index
	 * @param currentCount
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findProductListById(String cid,int index,int currentCount) throws SQLException{
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		List<Product> list=runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
	    return list;
	}
	/**
	 * 這個方法是顯示當前類別的條數
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public int getCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),cid);
		return query.intValue();
	}
	/**
	 * 根据pid查询商品信息
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product findProductById(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "select * from product where pid=?";
		return runner.query(sql,new BeanHandler<Product>(Product.class),pid);
	}
	/**
	 * 这个办法是添加订单
	 * @param order
	 * @throws SQLException 
	 */
	public void addOrders(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Connection conn=C3P0UTils.getConnection();
		runner.update(conn, sql, order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getState(),order.getAddress(),order.getName(),order.getTelephone(),
				order.getUser().getUid());
	}
	/**
	 * 这个办法是添加orderItem
	 * @throws SQLException 
	 */
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection conn = C3P0UTils.getConnection();
		List<orderItem> orderItems=order.getOrderItems();
		for(orderItem item:orderItems) {
			runner.update(conn,sql,item.getItemid(),item.getCount(),item.getSubtotal(),
					item.getProduct().getPid(),item.getOrder().getOid());
		}
	}
	/**
	 * 更新地址
	 * @param order
	 * @throws SQLException 
	 */
	public void updateOrderAdrr(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		runner.update(sql,order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
		
	}
	/**
	 * 更新狀態
	 * @throws SQLException 
	 */
	public void updateOrderState(String orderid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "update orders set state=? where oid=?";
		runner.update(sql,1,orderid);
		
	}
}
																																															