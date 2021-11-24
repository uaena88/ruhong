package com.yuyi.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yuyi.bean.Cart;
import com.yuyi.bean.CartItem;
import com.yuyi.bean.Category;
import com.yuyi.bean.Order;
import com.yuyi.bean.Product;
import com.yuyi.bean.User;
import com.yuyi.bean.orderItem;
import com.yuyi.service.ProductService;
import com.yuyi.util.CommonsUtils;

public class ProductServlet extends BaseServlet {

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { //模块中的功能方法进行区分
	 * //Class.forName(className) String method = request.getParameter("method");
	 * if("Index".equals(method)) { Index(request,response); }else
	 * if("categoryList".equals(method)) { categoryList(request, response); }else
	 * if("ProductInfo".equals(method)) { ProductInfo(request, response); }
	 * 
	 * }
	 * 
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { doGet(request, response); }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 显示商品的类别的查询功能
	protected void categoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// 首页 显示热门 和 最新 类别
	public void Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductService service = new ProductService();

		// 准备热门商品
		List<Product> hotProduct = service.findHotProductList();

		// 准备最新商品
		List<Product> newProduct = service.findNewProductList();

		// 准备分类数据
		List<Category> category = service.findAllCategory();

		request.setAttribute("categoryList", category);
		request.setAttribute("hotProduct", hotProduct);
		request.setAttribute("newProduct", newProduct);

		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	// 显示商品详细的信息
	public void ProductInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	//
	public void productListById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

//將商品添加到購物車
	public void addProductToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		ProductService productService = new ProductService();

		String pid = request.getParameter("pid");
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));

		// Product > cartitem > cart对象 》 session区域
		Product product = productService.findProductById(pid);

		// 计算小计
		double subTotal = product.getShop_price() * buyNum;

		// 封装CartItem
		CartItem item = new CartItem();
		item.setBuyNum(buyNum);
		item.setProduct(product);
		item.setSubTotal(subTotal);

		// 获得购物车 判断session中是否存在cart
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
		}
		// 有购物车
		double newsubTotal = 0.0;
		// 先判断购物车中是否已经存在此购物项 ----》 判断key是否已经存在pid
		// 如果存在 与原有的相加
		Map<String, CartItem> cartItems = cart.getCartItems();
		if (cartItems.containsKey(pid)) {
			// 先取出原有的商品的数量
			CartItem cartItem = cartItems.get(pid);
			// 获取原有的
			int oldBuyNum = cartItem.getBuyNum();
			// 相加
			oldBuyNum += buyNum;
			// 放回购物车中
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);

			// 修改商品的小计

			// 原有商品的小计
			double oldsubTotal = cartItem.getSubTotal();
			// 新买的商品的小计
			newsubTotal = buyNum * product.getShop_price();
			cartItem.setSubTotal(oldsubTotal + newsubTotal);
		} else {
			// 到这里 代表 购物车中没有该商品
			cart.getCartItems().put(product.getPid(), item);
			newsubTotal = buyNum * product.getShop_price();
		}

		// 计算总计
		double total = cart.getTotal() + newsubTotal;
		cart.setTotal(total);

		// 将购物车再次存放到session中
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 删除选中的购物车中的商品
	public void delProCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			// 需要修改总价
			cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubTotal());
			// 删除商品
			cartItems.remove(pid);
			cart.setCartItems(cartItems);

		}
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 清空選中的購物車中的商品
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 提交訂單
	public void buttonOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.判斷用戶有沒有登陸，如果沒有登陸，不用封裝數據
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// user為空，沒有登陸
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		// 2.不爲空，封裝order對象到service層
		Order order = new Order();
		// 訂單id
		// private String oid;
		order.setOid(CommonsUtils.getUUID());
		// 訂單時間
		// private Date ordertime;
		order.setOrdertime(new Date());
		// 訂單的總數
		// private double total;
		Cart cart = (Cart) session.getAttribute("cart");
		order.setTotal(cart.getTotal());
		// 訂單的狀態
		// private int state;
		order.setState(0);
		// 訂單的地址
		// private String address;
		order.setAddress(null);
		// 用戶的名字
		// private String name;
		order.setName(null);
		// 用戶的號碼
		// private String telephone;
		order.setTelephone(null);
		// 用戶
		// private User user;
		order.setUser(user);

		// 该订单下面是有多个订单项
		// orderItem
		// List<orderItem> orderItems=new ArrayList<orderItem>();
		Map<String, CartItem> cartItems = cart.getCartItems();
		for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
			// 取出每一个购物项
			CartItem cartItem = entry.getValue();
			// 取出来 放到 订单项里面
			// 创建新的订单项
			orderItem orderI = new orderItem();
			// 这个是订单项的ID 所以要UUID
			orderI.setItemid(CommonsUtils.getUUID());
			orderI.setCount(cartItem.getBuyNum());
			orderI.setSubtotal(cartItem.getSubTotal());
			orderI.setProduct(cartItem.getProduct());
			orderI.setOrder(order);
			// 将该订单项添加到订单的订单项集合里面
			order.getOrderItems().add(orderI);
			// 到此为止 这个order就封装完成了
		}

		// 傳遞到service層
		ProductService service = new ProductService();
		service.submitOrder(order);

		session.setAttribute("order", order);
		response.sendRedirect(request.getContextPath() + "/order_info.jsp");
	}

	// 确认订单信息
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.更新收貨人信息
		Map<String, String[]> parameterMap = request.getParameterMap();
		String orderid = request.getParameter("orderid");
		Order order=new Order();
		
		try {
			BeanUtils.populate(order, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//傳遞到service層
		ProductService service = new ProductService();
		
		try {
			service.updateOrderAdrr(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.在线支付
		try {
			service.updateOrderState(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
