package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.service.AdminProductService;


public class AdminDelProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminDelProductServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要删除的pid
		String pid=request.getParameter("pid");
		//传递这个pid到service层
		AdminProductService adminProductService=new AdminProductService();
		try {
			adminProductService.delProductById(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/adminproductListServlet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
