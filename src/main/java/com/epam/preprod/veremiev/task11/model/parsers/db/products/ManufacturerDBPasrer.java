package com.epam.preprod.veremiev.task11.model.parsers.db.products;

import java.sql.ResultSet;

import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;
import com.epam.preprod.veremiev.task11.model.parsers.db.AbstractDBParser;

/**
 * Manufacturer {@link ResultSet} parser
 * 
 * @author Illia_Veremiev
 *
 */
public class ManufacturerDBPasrer extends AbstractDBParser<Manufacturer> {

	@Override
	public Manufacturer parse(ResultSet rs) throws Exception {
		Manufacturer manufacturer = new Manufacturer(rs.getString(DBFields.MAN_NAME));
		manufacturer.setId(rs.getLong(DBFields.MAN_ID));
		return manufacturer;
	}
}
