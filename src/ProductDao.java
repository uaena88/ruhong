
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yuyi.bean.Product;
import com.yuyi.util.C3P0UTils;

/**
 * 這個是封裝了操作商品的方法
 * 
 * @author 育奕
 *
 */
public class ProductDao {
	/**
	 * 這個方法是查詢所有的商品信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAllProduct() throws SQLException {
		return new QueryRunner(C3P0UTils.getDataSource()).query("SELECT * FROM product",
				new BeanListHandler<Product>(Product.class));
	}

	/**
	 * 這個方法是查詢商品信息的總條數
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getTotalCount() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "SELECT COUNT(*) FROM product";
		Long query = (Long) queryRunner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	// 獲取分頁的商品信息
	public List<Product> findProductForPageBean(int index, int currentCount) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0UTils.getDataSource());
		String sql = "SELECT * FROM product limit ?,?";
		List<Product> qList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
		return qList;
	}

}
