package com.epam.preprod.veremiev.task11.core.filters.utils.security.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.epam.preprod.veremiev.task11.model.entities.utils.Role;

/**
 * Contains all access rights for pages
 * 
 * @author Illia_Veremiev
 *
 */
public class AccessRights {

	private List<Constraint> constraints;

	public AccessRights() {
		this.constraints = new ArrayList<>();
	}

	/**
	 * @return the constraints
	 */
	public List<Constraint> getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints the constraints to set
	 */
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}

	/**
	 * Returns true if URL fit at least one pattern
	 * 
	 * @param url - url to check
	 * @return true if URL fit at least one pattern
	 */
	public boolean fitURL(String url) {
		return getConstraints().stream().anyMatch(i -> Pattern.compile(i.getUrlPattern()).matcher(url).find());
	}

	/**
	 * Returns true if user have access to <b>url</b> resource
	 * 
	 * @param role - user role
	 * @param url  - url to check
	 * @return true if user have access to <b>url</b> resource
	 */
	public boolean hasAccess(Role role, String url) {
		return getConstraints().stream().filter(i -> Pattern.compile(i.getUrlPattern()).matcher(url).find())
				.allMatch(i -> i.match(role.toString()));
	}
}
