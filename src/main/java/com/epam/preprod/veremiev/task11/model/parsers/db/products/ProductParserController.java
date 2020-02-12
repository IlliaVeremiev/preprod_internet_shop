package com.epam.preprod.veremiev.task11.model.parsers.db.products;

import java.util.Map;
import java.util.TreeMap;

import com.epam.preprod.veremiev.task11.constants.ApplicationErrors;
import com.epam.preprod.veremiev.task11.constants.ProductsCategories;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.model.parsers.db.AbstractDBParser;

/**
 * Manage parsers by category name
 * 
 * @author Illia_Veremiev
 *
 */
public class ProductParserController {

	private static final Map<String, AbstractDBParser<? extends InputDevice>> map = new TreeMap<>();
	static {
		map.put(ProductsCategories.CATEGORY_MOUSE, new MouseDBPasrer());
		map.put(ProductsCategories.CATEGORY_KEYBOARD, new KeyboardDBPasrer());
		map.put(ProductsCategories.CATEGORY_MECHANICAL_KEYBOARD, new MechanicalKeyboardDBPasrer());
	}

	/**
	 * Returns parser by category name
	 * 
	 * @param key - category name
	 * @return parser by category name
	 */
	public static AbstractDBParser<? extends InputDevice> get(String key) {
		if (!map.containsKey(key)) {
			throw new IllegalStateException(ApplicationErrors.NO_SUCH_CATEGORY);
		}
		return map.get(key);
	}
}
