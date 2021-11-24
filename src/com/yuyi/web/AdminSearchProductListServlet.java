package com.yuyi.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yuyi.bean.Product;
import com.yuyi.service.AdminProductService;
import com.yuyi.vo.Condition;

public class AdminSearchProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchProductListServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.收集表单的数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		// 2.封装数据
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, parameterMap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3.传递数据
		// 传递
		AdminProductService service = new AdminProductService();
		List<Product> productList = null;
		try {
			productList = service.findProductListByCondition(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("condition", condition);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("admin/product/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
