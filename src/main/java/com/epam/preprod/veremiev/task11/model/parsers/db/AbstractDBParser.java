package com.epam.preprod.veremiev.task11.model.parsers.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.model.entities.AbstractEntity;

/**
 * Abstract DB data parser
 * 
 * @author Illia_Veremiev
 *
 * @param <T> - {@link AbstractEntity}
 */
public abstract class AbstractDBParser<T extends AbstractEntity> {

	/**
	 * Main class method
	 * 
	 * @param rs - DB result set
	 * @return parsed entity
	 * @throws SQLException if errors occurs
	 */
	public abstract T parse(ResultSet rs) throws Exception;

}
