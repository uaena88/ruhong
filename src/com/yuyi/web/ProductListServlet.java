package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.bean.PageBean;
import com.yuyi.bean.Product;
import com.yuyi.service.ProductService;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductListServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductService();
		//模拟单前是第一页
		//int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null) currentPageStr="1";
		int currentPage = Integer.parseInt(currentPageStr);
		
		//认为每页12条
		int currentCount = 12;
		PageBean<Product> pageBean = null;
		try {
			pageBean = service.findPagebean(currentPage, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
