package com.yuyi.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yuyi.bean.Category;
import com.yuyi.service.ProductService;


public class categoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public categoryListServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Category> CategoryList =  service.findAllCategory();
		
		Gson gson = new Gson();
		String json = gson.toJson(CategoryList);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
