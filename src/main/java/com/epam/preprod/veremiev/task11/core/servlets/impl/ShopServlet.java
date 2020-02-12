package com.epam.preprod.veremiev.task11.core.servlets.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.ErrorHeaders;
import com.epam.preprod.veremiev.task11.constants.RequestAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.services.ProductsService;
import com.epam.preprod.veremiev.task11.services.utils.SearchBean;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8980704846292716531L;

	private ProductsService productsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(config.getServletContext());
		productsService = contextAdapter.get(ContextAttributes.PRODUCTS_SERVICE, ProductsService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		SearchBean bean = new SearchBean(request.getParameterMap());
		try {
			request.setAttribute(RequestAttributes.PRODUCTS_LIST, productsService.getByParameters(bean));
			long itemsCount = productsService.getProductsCountByParameters(bean);
			long pagesCount = productsService.calculatePagesCount(bean, itemsCount);
			request.setAttribute(RequestAttributes.PAGES_COUNT, pagesCount);
			request.setAttribute(RequestAttributes.SEARCH_PAGE_NUMBER, bean.getPageNumber());
			request.setAttribute(RequestAttributes.MANUFACTURER, bean.getManufacturer());
			request.setAttribute(RequestAttributes.MANUFACTURERS, productsService.getManufacturers());
		} catch (ServiceException e) {
			sessionAdapter.addError(ErrorHeaders.GET_PRODUCTS_LIST_ERROR, e.getMessage());
		}
		new Director(METHOD.FORWARD, "/WEB-INF/shop.jsp").now(request, response);
	}
}
