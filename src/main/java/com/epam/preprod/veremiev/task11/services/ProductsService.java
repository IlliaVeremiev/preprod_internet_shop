package com.epam.preprod.veremiev.task11.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLFields;
import com.epam.preprod.veremiev.task11.core.validators.impl.CategoryValidator;
import com.epam.preprod.veremiev.task11.database.dao.CategoriesDAO;
import com.epam.preprod.veremiev.task11.database.dao.ManufacturerDAO;
import com.epam.preprod.veremiev.task11.database.dao.ProductDAO;
import com.epam.preprod.veremiev.task11.database.mysql.TransactionManager;
import com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts.Condition;
import com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts.Rule;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.model.entities.utils.Category;
import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;
import com.epam.preprod.veremiev.task11.services.utils.ExtendedSearchBean;
import com.epam.preprod.veremiev.task11.services.utils.SearchBean;

/**
 * Makes some logic with products
 *
 * @author Illia_Veremiev
 */
public class ProductsService {

	private static final Logger log = Logger.getLogger(ProductsService.class);

	private ProductDAO productsDAO;

	private CategoriesDAO categoriesDAO;

	private ManufacturerDAO manufacturerDAO;

	public ProductsService(ProductDAO productsDAO, CategoriesDAO categoriesDAO, ManufacturerDAO manufacturerDAO) {
		this.productsDAO = productsDAO;
		this.categoriesDAO = categoriesDAO;
		this.manufacturerDAO = manufacturerDAO;
	}

	/**
	 * Returns {@link List} of products filtered by {@link SearchBean} parameters
	 * 
	 * @param bean - bean with parameters
	 * @return {@link List} of products filtered by {@link SearchBean} parameters
	 * @throws ServiceException if errors occurs
	 */
	public List<InputDevice> getByParameters(SearchBean bean) throws ServiceException {
		ExtendedSearchBean extendedBean = new ExtendedSearchBean(bean);
		try {
			return new TransactionManager().doInTransaction(con -> {
				CategoryValidator categoryValidator = new CategoryValidator(extendedBean.getBaseCategory());
				if (!categoryValidator.isCastable()) {
					extendedBean.setBaseCategory("1");
				}
				List<Category> categories = categoriesDAO.getByIdWithChilds(con, extendedBean.getBaseCategory());
				extendedBean.setCategories(categories);
				fillByConditions(extendedBean);
				return productsDAO.getBySortingRequest(con, extendedBean);
			});
		} catch (SQLException e) {
			throw new ServiceException(ExceptionMessages.CANT_LOAD_PRODUCTS_LIST);
		}
	}

	/**
	 * Returns manufacturers list
	 * 
	 * @return manufacturers list
	 * @throws ServiceException if errors occurs
	 */
	public List<Manufacturer> getManufacturers() throws ServiceException {
		try {
			return new TransactionManager().doInTransaction(con -> manufacturerDAO.getManufacturers(con));
		} catch (SQLException e) {
			throw new ServiceException(ExceptionMessages.CANT_LOAD_MANUFACTURERS_LIST);
		}
	}

	/**
	 * Returns products count by filters
	 * 
	 * @param bean - bean with parameters
	 * @return products count by filters
	 * @throws ServiceException if errors occurs
	 */
	public long getProductsCountByParameters(SearchBean bean) throws ServiceException {
		ExtendedSearchBean extendedBean = new ExtendedSearchBean(bean);
		try {
			return new TransactionManager().doInTransaction(con -> {
				CategoryValidator categoryValidator = new CategoryValidator(extendedBean.getBaseCategory());
				if (!categoryValidator.isCastable()) {
					extendedBean.setBaseCategory("1");
				}
				List<Category> categories = categoriesDAO.getByIdWithChilds(con, extendedBean.getBaseCategory());
				extendedBean.setCategories(categories);
				fillByConditions(extendedBean);
				return productsDAO.getProductsCount(con, extendedBean);
			});
		} catch (SQLException e) {
			throw new ServiceException(ExceptionMessages.CANT_LOAD_PRODUCTS_LIST);
		}
	}

	/**
	 * Returns pages count for current search parameters
	 * 
	 * @param bean       - {@link SearchBean}
	 * @param itemsCount - items count for this {@link SearchBean}
	 * @return pages count for current search parameters
	 */
	public long calculatePagesCount(SearchBean bean, long itemsCount) {
		long pageItemsCount = 10;
		try {
			pageItemsCount = Long.parseLong(bean.getPageItemsCount());
		} catch (NumberFormatException e) {
			log.trace("Empty items count parameter");
		}
		return itemsCount / pageItemsCount + (itemsCount % pageItemsCount != 0 ? 1 : 0);
	}

	private void fillByConditions(ExtendedSearchBean bean) {
		List<Condition> conditions = new ArrayList<>();
		conditions.add(Condition.make(MYSQLFields.INDEV_MANUFACTURER, Rule.EQUALS, bean.getManufacturer()));
		conditions.add(Condition.make(MYSQLFields.INDEV_CATEGORY, Rule.IN,
				bean.getCategories().stream().map(c -> String.valueOf(c.getId())).collect(Collectors.joining(", "))));
		conditions.add(Condition.make(MYSQLFields.INDEV_PRICE, Rule.BIGGEROREQUALS, bean.getPriceMin()));
		conditions.add(Condition.make(MYSQLFields.INDEV_PRICE, Rule.LESSOREQUALS, bean.getPriceMax()));
		bean.setConditions(conditions);
	}

	/**
	 * Returns input device by id
	 * 
	 * @param sid - {@link InputDevice} id
	 * @return input device by id
	 * @throws ServiceException if errors occurs
	 */
	public InputDevice getInputDevice(String sid) throws ServiceException {
		try {
			long id = Long.parseLong(sid);
			return new TransactionManager().doInTransaction(con -> productsDAO.get(con, id));
		} catch (SQLException e) {
			throw new ServiceException("Unable t find device");
		} catch (NumberFormatException e) {
			throw new ServiceException("Unable to parse id");
		}
	}
}
