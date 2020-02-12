package com.epam.preprod.veremiev.task11.core.contextlisteners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.core.filters.utils.security.AccessRightsDomParser;
import com.epam.preprod.veremiev.task11.database.dao.CategoriesDAO;
import com.epam.preprod.veremiev.task11.database.dao.ManufacturerDAO;
import com.epam.preprod.veremiev.task11.database.dao.OrderItemsDAO;
import com.epam.preprod.veremiev.task11.database.dao.OrdersDAO;
import com.epam.preprod.veremiev.task11.database.dao.ProductDAO;
import com.epam.preprod.veremiev.task11.database.dao.UserDAO;
import com.epam.preprod.veremiev.task11.database.mysql.CategoriesMYSQL;
import com.epam.preprod.veremiev.task11.database.mysql.ManufacturerMYSQL;
import com.epam.preprod.veremiev.task11.database.mysql.OrderItemsMYSQL;
import com.epam.preprod.veremiev.task11.database.mysql.OrdersMYSQL;
import com.epam.preprod.veremiev.task11.database.mysql.ProductMYSQL;
import com.epam.preprod.veremiev.task11.database.mysql.UserMYSQL;
import com.epam.preprod.veremiev.task11.modules.captcha.CaptchaDispatcher;
import com.epam.preprod.veremiev.task11.services.CaptchaService;
import com.epam.preprod.veremiev.task11.services.CartService;
import com.epam.preprod.veremiev.task11.services.FileService;
import com.epam.preprod.veremiev.task11.services.OrdersService;
import com.epam.preprod.veremiev.task11.services.ProductsService;
import com.epam.preprod.veremiev.task11.services.UserService;

/**
 * Class initializator for server
 * 
 * @author Illia_Veremiev
 *
 */
public class InitializationListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(InitializationListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		initWebXmlParams(servletContext);
		initCaptcha(servletContext);
		initServices(servletContext);
		initAccessRights(servletContext);
	}

	private void initAccessRights(ServletContext servletContext) {
		AccessRightsDomParser domParser = new AccessRightsDomParser(
				servletContext.getRealPath("/") + "/WEB-INF/security/access_rights.xml");
		try {
			servletContext.setAttribute(ContextAttributes.ACCESS_RIGHTS, domParser.parse());
		} catch (Exception e) {
			log.error("Unable to load access rights list");
		}
	}

	private void initServices(ServletContext servletContext) {
		CategoriesDAO categoriesDAO = new CategoriesMYSQL();
		ManufacturerDAO manufacturerDAO = new ManufacturerMYSQL();
		ProductDAO productDAO = new ProductMYSQL();
		UserDAO userDAO = new UserMYSQL();
		OrdersDAO ordersDAO = new OrdersMYSQL();
		OrderItemsDAO orderItemsDAO = new OrderItemsMYSQL();

		CaptchaService captchaService = new CaptchaService();
		servletContext.setAttribute(ContextAttributes.CAPTCHA_SERVICE, captchaService);
		FileService fileService = new FileService();
		servletContext.setAttribute(ContextAttributes.FILE_SERVICE, fileService);
		ProductsService productsService = new ProductsService(productDAO, categoriesDAO, manufacturerDAO);
		servletContext.setAttribute(ContextAttributes.PRODUCTS_SERVICE, productsService);
		UserService userService = new UserService(userDAO);
		servletContext.setAttribute(ContextAttributes.USER_SERVICE, userService);
		CartService cartService = new CartService();
		servletContext.setAttribute(ContextAttributes.CART_SERVICE, cartService);
		OrdersService ordersService = new OrdersService(ordersDAO, orderItemsDAO);
		servletContext.setAttribute(ContextAttributes.ORDER_SERVICE, ordersService);
	}

	private void initWebXmlParams(ServletContext servletContext) {
		servletContext.setAttribute(ContextAttributes.USER_ICON_PATH,
				servletContext.getInitParameter(ContextAttributes.USER_ICON_PATH));
		servletContext.setAttribute(ContextAttributes.USER_ICON_DEFAULT,
				servletContext.getInitParameter(ContextAttributes.USER_ICON_DEFAULT));
		servletContext.setAttribute(ContextAttributes.PRODUCTS_PHOTO_PATH,
				servletContext.getInitParameter(ContextAttributes.PRODUCTS_PHOTO_PATH));
	}

	private void initCaptcha(ServletContext servletContext) {
		String captchaLocation = servletContext.getInitParameter(ContextAttributes.CAPTCHA_LOCATION);
		CaptchaDispatcher captchaDispatcher = new CaptchaDispatcher(captchaLocation, servletContext);
		servletContext.setAttribute(ContextAttributes.CAPTCHA_DISPATCHER, captchaDispatcher);
		String captchaLifetime = servletContext.getInitParameter(ContextAttributes.CAPTCHA_LIFETIME);
		servletContext.setAttribute(ContextAttributes.CAPTCHA_LIFETIME, Integer.parseInt(captchaLifetime));
	}
}
