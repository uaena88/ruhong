package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.bean.PageBean;
import com.yuyi.bean.Product;
import com.yuyi.service.ProductService;


public class productListByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public productListByIdServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cid = request.getParameter("cid");
		String currentPagestr = request.getParameter("currentPage");
		if (currentPagestr == null) currentPagestr = "1";
		int currentPage = Integer.parseInt(currentPagestr);
		
		int currentCount = 12;
		
		ProductService service = new ProductService();
		PageBean pageBean = service.findProductListById(cid,currentPage,currentCount);
		
		//浏览记录的显示
		//定义一个记录商品信息的集合
		ArrayList<Product> arrayList=new ArrayList<Product>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if("pids".equals(cookie.getName())) {
					String pids=cookie.getValue();
					String[] spilt=pids.split("-");
					Product product=null;
					for(String pid:spilt) {
						try {
							product=service.findProductById(pid);
							arrayList.add(product);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		request.setAttribute("arrayList", arrayList);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
