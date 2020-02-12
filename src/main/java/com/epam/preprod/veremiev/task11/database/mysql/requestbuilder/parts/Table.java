package com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts;

/**
 * MYSQL Table
 * 
 * @author Illia_Veremiev
 *
 */
public class Table {

	/**
	 * Table name
	 */
	private String name;

	public Table(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "`" + name + "`";
	}
}
