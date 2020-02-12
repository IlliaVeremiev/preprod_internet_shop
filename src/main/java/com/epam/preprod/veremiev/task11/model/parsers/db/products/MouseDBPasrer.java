package com.epam.preprod.veremiev.task11.model.parsers.db.products;

import java.sql.ResultSet;

import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.model.entities.Mouse;
import com.epam.preprod.veremiev.task11.model.parsers.db.AbstractDBParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Database result set parser
 * 
 * @author Illia_Veremiev
 *
 */
public class MouseDBPasrer extends AbstractDBParser<Mouse> {

	@Override
	public Mouse parse(ResultSet rs) throws Exception {
		String parameters = rs.getString(DBFields.INDEV_PARAMETERS);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(parameters, Mouse.class);
	}
}
