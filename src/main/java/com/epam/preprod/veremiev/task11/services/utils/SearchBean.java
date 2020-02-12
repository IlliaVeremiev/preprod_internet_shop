package com.epam.preprod.veremiev.task11.services.utils;

import java.util.Map;

import com.epam.preprod.veremiev.task11.constants.RequestParameters;

/**
 * Search params bean
 *
 * @author Illia_Veremiev
 */
public class SearchBean{

	/**
	 * Name
	 */
	private String name;

	/**
	 * Manufacturer
	 */
	private String manufacturer;

	/**
	 * Minimal price
	 */
	private String priceMin;

	/**
	 * Maximal price
	 */
	private String priceMax;

	/**
	 * Page number
	 */
	private String pageNumber;

	/**
	 * Page items count
	 */
	private String pageItemsCount;

	/**
	 * Base category
	 */
	private String baseCategory;

	/**
	 * Sort order
	 */
	private String orderBy;

	/**
	 * Sort direction
	 */
	private String orderDir;

	/**
	 * Constructor with parameters
	 *
	 * @param params - parameters map
	 */
	public SearchBean(Map<String, String[]> params){
		name = firstOrNull(params.get(RequestParameters.SEARCH_NAME));
		manufacturer = firstOrNull(params.get(RequestParameters.SEARCH_MANUFACTURER));
		priceMin = firstOrNull(params.get(RequestParameters.SEARCH_PRICE_MIN));
		priceMax = firstOrNull(params.get(RequestParameters.SEARCH_PRICE_MAX));
		baseCategory = firstOrNull(params.get(RequestParameters.SEARCH_CATEGORY));
		orderBy = firstOrNull(params.get(RequestParameters.SEARCH_ORDER_BY));
		orderDir = firstOrNull(params.get(RequestParameters.SEARCH_ORDER_DIR));
		pageNumber = firstOrNull(params.get(RequestParameters.SEARCH_PAGE_NUMBER));
		pageItemsCount = firstOrNull(params.get(RequestParameters.SEARCH_ITEMS_ON_PAGE));
	}

	public SearchBean(){
	}

	private String firstOrNull(String[] param){
		return (param == null) ? null : param[0];
	}

	/**
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer(){
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the priceMin
	 */
	public String getPriceMin(){
		return priceMin;
	}

	/**
	 * @param priceMin the priceMin to set
	 */
	public void setPriceMin(String priceMin){
		this.priceMin = priceMin;
	}

	/**
	 * @return the priceMax
	 */
	public String getPriceMax(){
		return priceMax;
	}

	/**
	 * @param priceMax the priceMax to set
	 */
	public void setPriceMax(String priceMax){
		this.priceMax = priceMax;
	}

	/**
	 * @return the pageNumber
	 */
	public String getPageNumber(){
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(String pageNumber){
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageItemsCount
	 */
	public String getPageItemsCount(){
		return pageItemsCount;
	}

	/**
	 * @param pageItemsCount the pageItemsCount to set
	 */
	public void setPageItemsCount(String pageItemsCount){
		this.pageItemsCount = pageItemsCount;
	}

	/**
	 * @return the baseCategory
	 */
	public String getBaseCategory(){
		return baseCategory;
	}

	/**
	 * @param baseCategory the baseCategory to set
	 */
	public void setBaseCategory(String baseCategory){
		this.baseCategory = baseCategory;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy(){
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}

	/**
	 * @return the orderDir
	 */
	public String getOrderDir(){
		return orderDir;
	}

	/**
	 * @param orderDir the orderDir to set
	 */
	public void setOrderDir(String orderDir){
		this.orderDir = orderDir;
	}
}
