package com.epam.preprod.veremiev.task11.model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Mailing entity
 * 
 * @author Illia_Veremiev
 *
 */
public class Mailing extends AbstractEntity implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 572351760389034928L;

	/**
	 * Mailing name
	 */
	private String name;

	/**
	 * Constructor with parameter
	 * 
	 * @param name - mailing name
	 */
	public Mailing(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Mailing mailing = (Mailing)o;
		return Objects.equals(name, mailing.name);
	}

	@Override
	public int hashCode(){
		return Objects.hash(name);
	}
}
