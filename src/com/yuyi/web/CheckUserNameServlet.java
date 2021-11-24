package com.yuyi.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuyi.service.UserService;

public class CheckUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckUserNameServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		UserService userService = new UserService();
		boolean isExist=userService.CheckUserName(username);
		String json="{\"isExist\":"+isExist+"}";
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
