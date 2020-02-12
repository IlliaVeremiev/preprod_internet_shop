package com.epam.preprod.veremiev.task11.constants;

import com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts.Field;

/**
 * MYSQL fields
 * 
 * @author Illia_Veremiev
 *
 */
public class MYSQLFields {

	public static final Field INDEV_PRICE = new Field(DBFields.INDEV_PRICE, MYSQLTables.INPUT_DEVIECS);
	public static final Field INDEV_CATEGORY = new Field(DBFields.INDEV_CATEGORY, MYSQLTables.INPUT_DEVIECS);
	public static final Field INDEV_MANUFACTURER = new Field(DBFields.INDEV_MANUFACTURER, MYSQLTables.INPUT_DEVIECS);
}
