package com.yuyi.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0UTils {
//记载配置
	private static DataSource dataSource = new ComboPooledDataSource("yuyi");

	private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

	// 直接获取一个连接池
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 获取连接对象
	public static Connection getConnection() throws SQLException {
		Connection connection = t1.get();
		if (connection == null) {
			connection = dataSource.getConnection();
			t1.set(connection);
		}
		return connection;
	}

	// 开启事务
	public static void startTransaction() throws SQLException {
		Connection connection = getConnection();
		if (connection != null) {
			connection.setAutoCommit(false);
		}
	}

	// 事务回滚
	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}

	// 提交并且 关闭资源及从ThreadLocall中释放
	public static void commitAndRelease() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit(); // 事务提交
			con.close();// 关闭资源
			t1.remove();// 从线程绑定中移除
		}
	}

	// 关闭资源方法
	public static void closeConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}
