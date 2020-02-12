package com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts;

/**
 * MYSQL {@link Table} field
 * 
 * @author Illia_Veremiev
 *
 */
public class Field {

	/**
	 * Field name
	 */
	private String name;

	/**
	 * Field table
	 */
	private Table table;

	public Field(String name, Table table) {
		this.name = name;
		this.table = table;
	}

	@Override
	public String toString() {
		return table + ".`" + name + "`";
	}
}
