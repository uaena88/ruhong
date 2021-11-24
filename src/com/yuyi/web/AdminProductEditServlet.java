package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.bean.Category;
import com.yuyi.bean.Product;
import com.yuyi.service.AdminProductService;


public class AdminProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProductEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		// 传递pid到service层
		AdminProductService service = new AdminProductService();
		Product product = null;
		try {
			product = service.findProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Category> list = null;
		list = service.findAllCategory();
		// 將查詢到的數據放到request中
		request.setAttribute("categoryList", list);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
