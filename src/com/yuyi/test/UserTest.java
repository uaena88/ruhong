package com.yuyi.test;

import java.sql.SQLException;

import com.yuyi.bean.User;
import com.yuyi.dao.UserDao;
/**
 * 測試UserDao裏面的方法
 * @author 育奕
 *
 */
public class UserTest {
	public static void main(String[] args) throws SQLException {
		UserDao userDao=new UserDao();
//     User user=new User();
//     user.setEmail("2940141971");
//     user.setTelephone("13414873584");
//     user.setUid("f55b7d3a352a4f0782c910b2c70f1ea2");
//     user.setUsernamae("yuyi");
//     user.setPassword("123456");
//     user.setName("yy");
//     user.setBirthday(null);
//     user.setCode("71a3a933353347a4bcacff699e6baa9c950a02f6b84e4f6fb8404ca06febfd6k");
//     user.setSex("男");
//     user.setState(0);
//     int result=userDao.register(user);
//     if(result>0) {
//    	 System.out.println("登陸成功");
//     }
		User user=new User();
		String username="yuyi";
		Long long1=(long) 0;
		long1=userDao.CheckUserName(username);
		if(long1>0) {
			System.out.println("用戶名重複");
		}else {
			System.out.println("用戶名可用");
		}
	}
}
