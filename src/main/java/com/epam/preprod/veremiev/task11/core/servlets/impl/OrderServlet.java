package com.epam.preprod.veremiev.task11.core.servlets.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestAttributes;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.entities.Order;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.modules.cart.Cart;
import com.epam.preprod.veremiev.task11.services.CartService;
import com.epam.preprod.veremiev.task11.services.OrdersService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -994642107816544386L;

	private CartService cartService;

	private OrdersService ordersService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(config.getServletContext());
		cartService = contextAdapter.get(ContextAttributes.CART_SERVICE, CartService.class);
		ordersService = contextAdapter.get(ContextAttributes.ORDER_SERVICE, OrdersService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());

		Cart cart = sessionAdapter.getCart();
		request.setAttribute(RequestAttributes.CART_ITEMS, cartService.getCartProducts(cart));
		request.setAttribute(RequestAttributes.TOTAL_PRICE, cartService.getTotalPrice(cart));
		new Director(METHOD.FORWARD, "/WEB-INF/orders.jsp").now(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		User user = sessionAdapter.get(SessionAttributes.USER, User.class);
		if (user == null) {
			response.setStatus(422);
			return;
		}
		Cart cart = sessionAdapter.getCart();
		if (cart.isEmpty()) {
			response.setStatus(400);
			return;
		}
		String payType = request.getParameter(RequestAttributes.PAY_TYPE);
		String payRequisites = request.getParameter(RequestAttributes.PAY_REQUISITES);

		Order order;
		try {
			order = ordersService.createOrder(user, cart, payType, payRequisites);
			cartService.clearCart(cart);
			response.getWriter().print(order.getId());
		} catch (ServiceException e) {
			response.setStatus(500);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		User user = sessionAdapter.get(SessionAttributes.USER, User.class);
		if (user == null) {
			response.setStatus(401);
		}
	}
}
