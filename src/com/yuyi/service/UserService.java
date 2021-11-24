package com.yuyi.service;

import java.sql.SQLException;

import com.yuyi.bean.User;
import com.yuyi.dao.UserDao;

/**
 * User用戶的事務管理層
 * 
 * @author 育奕
 *
 */
public class UserService {

	public boolean register(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.register(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row > 0 ? true : false;
	}

	public boolean CheckUserName(String username) {
		UserDao dao = new UserDao();
		Long checkUserName = 0L;
		try {
			checkUserName = dao.CheckUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkUserName>0?true:false;

	}
//用户登录
	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}
}
