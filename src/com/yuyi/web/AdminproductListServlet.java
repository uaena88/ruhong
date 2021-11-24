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

public class AdminproductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminproductListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminProductService adminproductService = new AdminProductService();
		List<Product> list = null;
		try {
			list = adminproductService.findAllProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("productList", list);
		
		AdminProductService service=new AdminProductService();
		List<Category> listCategory=null;
		listCategory=service.findAllCategory();
		
		//将查询到的数据放到request域中
		request.setAttribute("categoryList", listCategory);
		//转发到jsp页面        Ctrl+r
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
