package com.epam.preprod.veremiev.task11.model.parsers.bean;

import com.epam.preprod.veremiev.task11.exceptions.MultipleException;
import com.epam.preprod.veremiev.task11.model.bean.AbstractDataBean;
import com.epam.preprod.veremiev.task11.model.entities.AbstractEntity;

/**
 * Abstract parser
 * 
 * @author Illia_Veremiev
 *
 * @param <E> - {@link AbstractDataBean}
 * @param <T> - {@link AbstractEntity}
 */
public abstract class AbstractBeanParser<E extends AbstractDataBean, T extends AbstractEntity> {

	/**
	 * Main class method
	 * 
	 * @param bean - bean to parse
	 * @return parsed entity
	 * @throws MultipleException if errors occurs
	 */
	public abstract T parse(E bean) throws MultipleException;
}
