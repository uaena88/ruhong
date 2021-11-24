package com.yuyi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuyi.bean.User;
import com.yuyi.service.UserService;

public class UserServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 获取用户的名 和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		UserService service = new UserService();
		User user = null;

		try {
			user = service.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			// 登陸成功
			if ("autoLogin".equals(autoLogin)) {
				// 代表自动登录
				Cookie cookie_username = new Cookie("cookie_username", user.getUsername());
				cookie_username.setMaxAge(20 * 60);
				Cookie cookie_password = new Cookie("cookie_password", user.getPassword());
				cookie_password.setMaxAge(20 * 60);
			}
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/default.jsp");
		} else {
			request.setAttribute("errorlogin", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	/**
	 * 用戶注銷
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		Cookie cookie_username = new Cookie("cookie_username", "");
		cookie_username.setMaxAge(0);
		Cookie cookie_password = new Cookie("cookie_password", "");
		cookie_password.setMaxAge(0);
		
		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
