package com.epam.preprod.veremiev.task11.model.parsers.db.products;

import java.sql.ResultSet;

import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.model.entities.utils.Category;
import com.epam.preprod.veremiev.task11.model.parsers.db.AbstractDBParser;

/**
 * Category {@link ResultSet} parser
 * 
 * @author Illia_Veremiev
 *
 */
public class CategoryDBPasrer extends AbstractDBParser<Category> {

	@Override
	public Category parse(ResultSet rs) throws Exception {
		Category category = new Category(rs.getString(DBFields.CAT_NAME));
		category.setId(rs.getLong(DBFields.CAT_ID));
		category.setParent(rs.getLong(DBFields.CAT_PARENT));
		return category;
	}
}
