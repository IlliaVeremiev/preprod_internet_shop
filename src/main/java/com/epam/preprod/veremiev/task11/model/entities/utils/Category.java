package com.epam.preprod.veremiev.task11.model.entities.utils;

import com.epam.preprod.veremiev.task11.model.entities.AbstractEntity;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;

/**
 * {@link InputDevice} category
 * 
 * @author Illia_Veremiev
 *
 */
public class Category extends AbstractEntity {

	/**
	 * Id
	 */
	private long id;

	/**
	 * Product name
	 */
	private String name;

	/**
	 * Parent {@link Category}
	 */
	private long parent;

	public Category(String name) {
		this.name = name;
	}

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public long getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(long parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (parent ^ (parent >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Category other = (Category) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parent != other.parent) {
			return false;
		}
		return true;
	}
}
