package com.yuyi.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yuyi.bean.Product;
import com.yuyi.service.AdminProductService;

public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddProductServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 1.获取表单参数
		Map<String, String[]> map = request.getParameterMap();
		// 2.封装数据
		Product product = new Product();
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 手动添加表单中没有的数据
//		private String pid;
//		private String pimage;
//		private String pdate;
//		private int pflag;
		product.setPid(UUID.randomUUID().toString());
		product.setPimage("");
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);// 0表示商品未下架
		// 3.传递数据
		AdminProductService service = new AdminProductService();
		try {
			service.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4.转发跳转,使用重定向
		response.sendRedirect(request.getContextPath() + "/adminproductListServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
