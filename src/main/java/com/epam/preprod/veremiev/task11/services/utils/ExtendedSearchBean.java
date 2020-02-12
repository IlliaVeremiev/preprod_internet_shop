package com.epam.preprod.veremiev.task11.services.utils;

import java.util.List;

import com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts.Condition;
import com.epam.preprod.veremiev.task11.model.entities.utils.Category;

/**
 * Search bean params with extra fields
 * 
 * @author Illia_Veremiev
 *
 */
public class ExtendedSearchBean extends SearchBean {

	/**
	 * All categories
	 */
	private List<Category> categories;

	/**
	 * Search conditions
	 */
	private List<Condition> conditions;

	public ExtendedSearchBean(SearchBean bean) {
		setBaseCategory(bean.getBaseCategory());
		setManufacturer(bean.getManufacturer());
		setName(bean.getName());
		setOrderBy(bean.getOrderBy());
		setOrderDir(bean.getOrderDir());
		setPageItemsCount(bean.getPageItemsCount());
		setPageNumber(bean.getPageNumber());
		setPriceMax(bean.getPriceMax());
		setPriceMin(bean.getPriceMin());
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;

	}

	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
}
