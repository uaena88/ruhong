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

import com.yuyi.bean.Product;
import com.yuyi.service.AdminProductService;


public class AdminProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public AdminProductUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 1.获取表单参数
				Map<String, String[]> map = request.getParameterMap();
		// 2.封装数据
		String pid=request.getParameter("pid");
		String pname=request.getParameter("pname");
		String stringMarket_price=request.getParameter("market_price");
		String stringShop_price=request.getParameter("shop_price");
		String pdesc=request.getParameter("pdesc");
		double market_price=Double.parseDouble(stringMarket_price);
		double shop_price=Double.parseDouble(stringShop_price);
		Product product=new Product(pid, pname, market_price, shop_price, pdesc);
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		product.setPid(UUID.randomUUID().toString());
		product.setPimage("");
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);// 0表示商品未下架
		// 3.传递数据
		AdminProductService service = new AdminProductService();
		try {
			service.updateProductById(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/adminproductListServlet");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
