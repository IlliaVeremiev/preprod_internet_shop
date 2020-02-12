package com.epam.preprod.veremiev.task11.model.parsers.db.products;

import java.sql.ResultSet;

import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.model.entities.utils.Category;
import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;
import com.epam.preprod.veremiev.task11.model.parsers.db.AbstractDBParser;

/**
 * InputDevice {@link ResultSet} parser
 * 
 * @author Illia_Veremiev
 *
 */
public class InputDeviceDBPasrer extends AbstractDBParser<InputDevice> {

	@Override
	public InputDevice parse(ResultSet rs) throws Exception {
		String category = rs.getString(DBFields.INDEV_CATEGORY);
		InputDevice device = ProductParserController.get(category).parse(rs);
		device.setCategory(new Category(category));
		device.setManufacturer(new Manufacturer(rs.getString(DBFields.INDEV_MANUFACTURER)));
		device.setCount(rs.getLong(DBFields.INDEV_COUNT));
		device.setPhoto(rs.getString(DBFields.INDEV_PHOTO));
		device.setPrice(rs.getBigDecimal(DBFields.INDEV_PRICE));
		device.setTitle(rs.getString(DBFields.INDEV_TITLE));
		device.setId(rs.getLong(DBFields.INDEV_ID));
		return device;

	}
}
