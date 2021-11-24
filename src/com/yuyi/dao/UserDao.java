package com.yuyi.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yuyi.bean.User;
import com.yuyi.util.C3P0UTils;

/**
 * 這個類是包含操作用戶類的方法
 * @author 育奕
 *
 */
public class UserDao {
/**
 * 這個方法是測試用戶的登陸
 * @param user
 * @return
 * @throws SQLException
 */
	public int register(User user) throws SQLException {
		QueryRunner queryRunner=new QueryRunner(C3P0UTils.getDataSource());
		String sql="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
		int row=queryRunner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return row;
		
	}
	/**
	 * 這個方法是 測試用戶名有沒有重複
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Long CheckUserName(String username) throws SQLException {
		QueryRunner queryRunner=new QueryRunner(C3P0UTils.getDataSource());
		String sql="SELECT COUNT(*) FROM user WHERE username=?";
		Long query=(Long) queryRunner.query(sql, new ScalarHandler(),username);
		return query;
	}
	/**
	 * 這個方法是實現用戶登陸
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public User login(String username, String password) throws SQLException {
		QueryRunner queryRunner=new QueryRunner(C3P0UTils.getDataSource());
		String sql="select * from user where username=? and password=?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class),username,password);
	}
}
