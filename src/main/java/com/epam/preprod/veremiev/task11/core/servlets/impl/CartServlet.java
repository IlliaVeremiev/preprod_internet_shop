package com.epam.preprod.veremiev.task11.core.servlets.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestParameters;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.modules.cart.Cart;
import com.epam.preprod.veremiev.task11.services.CartService;
import com.epam.preprod.veremiev.task11.services.ProductsService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7448022770047089940L;

	private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> removers;

	private ProductsService productsService;

	private CartService cartService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		removers = new HashMap<>();
		removers.put("all", this::deleteAll);
		removers.put("one", this::deleteOne);
	}

	@Override
	public void init(ServletConfig config) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(config.getServletContext());
		productsService = contextAdapter.get(ContextAttributes.PRODUCTS_SERVICE, ProductsService.class);
		cartService = contextAdapter.get(ContextAttributes.CART_SERVICE, CartService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());

		Cart cart = sessionAdapter.getCart();
		request.setAttribute(RequestAttributes.CART_ITEMS, cartService.getCartProducts(cart));
		request.setAttribute(RequestAttributes.TOTAL_PRICE, cartService.getTotalPrice(cart));
		new Director(METHOD.FORWARD, "/WEB-INF/cart.jsp").now(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		try {
			InputDevice device = productsService.getInputDevice(request.getParameter(RequestParameters.PRODUCT_ID));
			Cart cart = sessionAdapter.getCart();
			cartService.setProductsCount(cart, device, request.getParameter(RequestAttributes.PRODUCTS_COUNT));
			response.getWriter().print(cartService.getPrice(cart, device));
		} catch (ServiceException e) {
			response.setStatus(500);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		try {
			InputDevice device = productsService.getInputDevice(request.getParameter(RequestParameters.PRODUCT_ID));
			Cart cart = sessionAdapter.getCart();
			long count = cartService.addProductToCart(device, cart);
			response.getWriter().print(count);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		removers.get(request.getParameter(RequestParameters.CART_DELETE_ACTION)).accept(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		try {
			InputDevice device = productsService.getInputDevice(request.getParameter(RequestParameters.PRODUCT_ID));
			Cart cart = sessionAdapter.getCart();
			cartService.removeProductFromCart(device, cart);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
	}

	private void deleteAll(HttpServletRequest request, HttpServletResponse response) {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		Cart cart = sessionAdapter.getCart();
		cartService.clearCart(cart);
	}
}
