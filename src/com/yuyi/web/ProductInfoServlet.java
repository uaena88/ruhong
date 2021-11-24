package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.bean.Product;
import com.yuyi.service.ProductService;

public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductInfoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		String cid = request.getParameter("cid");

		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 有值
			for (Cookie cookie : cookies) {
				// pids:pid-pid-pid
				if ("pids".equals(cookie.getName())) {
					pids = cookie.getValue();
					// 1-2-3 现在访问9 ---？ 9-1-2-3
					// 9-1-2-3 现在访问2 ---？ 2-9-1-3
					// 2-9-1-3 现在访问3 ---？ 3-2-9-1
					// ——————————————————————————
					// 将pids拆成一个数值
					String[] spilt = pids.split("-");// [1,2,3]
					List<String> alList = Arrays.asList(spilt);// [1,2,3]
					LinkedList<String> list = new LinkedList<String>(alList);// [1,2,3]
					// 判断当前的集合是否存在当前的pid
					if (list.contains(pid)) {
						// 包含当前查看的这个pid
						list.remove(pid);
						list.addFirst(pid);
					} else {
						// 不包含当前查看的这个pid
						list.addFirst(pid);
					}
					// [2,4,5]
					// 将[2,4,5] 转成2-4-5字符串
					StringBuffer sBuffer = new StringBuffer();
					// &&i<6 这是添加的一个限制条件
					for (int i = 0; i < list.size() && i < 6; i++) {
						sBuffer.append(list.get(i));
						sBuffer.append("-");
					}
					// 拼接的结果？？？ 2-4-5-
					// 所以 要删掉最后的尾巴 -
					pids = sBuffer.substring(0, sBuffer.length() - 1);
				}
			}
		}
		
		Cookie cookie=new Cookie("pids", pids);
		response.addCookie(cookie);

		ProductService productService = new ProductService();
		Product product = null;
		try {
			product = productService.findProductById(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("product", product);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
