package com.epam.preprod.veremiev.task11.core.filters.utils.security.entities;

import java.util.List;

/**
 * Rule that contains information, which of users have access
 * 
 * @author Illia_Veremiev
 *
 */
public class Constraint {

	private String urlPattern;

	private List<String> roles;

	/**
	 * @return the urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @param urlPattern the urlPattern to set
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean match(String role) {
		return roles.contains(role);
	}
}
