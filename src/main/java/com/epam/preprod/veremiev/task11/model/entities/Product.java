package com.epam.preprod.veremiev.task11.model.entities;

/**
 * Product base class
 * 
 * @author Illia_Veremiev
 *
 */
public abstract class Product extends AbstractEntity {

	/**
	 * Product id
	 */
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
