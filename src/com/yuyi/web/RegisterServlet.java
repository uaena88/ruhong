package com.yuyi.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.yuyi.bean.User;
import com.yuyi.service.UserService;
import com.yuyi.util.CommonsUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		// 自定义一个类型转换器 String 转换成 Date
		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class clazz, Object value) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = null;
				try {
					parse = format.parse(value.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return parse;
			}
		}, Date.class);
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// private String uid;
		user.setUid(CommonsUtils.getUUID());
		// private String telephone;
		user.setTelephone(null);
		// private int state; 是否激活 0代表的是激活
		user.setState(0);
		// private String code;
		user.setCode(CommonsUtils.getUUID());
		
		UserService userService=new UserService();
		boolean isRegister=userService.register(user);
		//判斷是否注冊成功
		if(isRegister) {
			//成功
		}else {
			//失敗
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
