package com.epam.preprod.veremiev.task11.model.bean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.preprod.veremiev.task11.constants.RequestParameters;

/**
 * User data bean
 * 
 * @author Illia_Veremiev
 *
 */
public class UserDataBean extends AbstractDataBean {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Surname
	 */
	private String surname;

	/**
	 * Login
	 */
	private String login;

	/**
	 * Email
	 */
	private String email;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Repassword
	 */
	private String rePassword;

	/**
	 * Country
	 */
	private String country;

	/**
	 * Accecced terms
	 */
	private String acceptTerms;

	/**
	 * Mailings list
	 */
	private String[] mailings;

	/**
	 * User photo
	 */
	private String photo;

	/**
	 * Constructor without parameters
	 */
	public UserDataBean() {
	}

	/**
	 * Constructor with parameters
	 * 
	 * @param parameters - {@link HttpServletRequest} parameters map
	 */
	public UserDataBean(Map<String, String[]> parameters) {
		setName(getFirstOrNull(parameters.get(RequestParameters.USER_NAME)));
		setSurname(getFirstOrNull(parameters.get(RequestParameters.USER_SURNAME)));
		setLogin(getFirstOrNull(parameters.get(RequestParameters.USER_LOGIN)));
		setEmail(getFirstOrNull(parameters.get(RequestParameters.USER_EMAIL)));
		setPassword(getFirstOrNull(parameters.get(RequestParameters.USER_PASSWORD)));
		setRePassword(getFirstOrNull(parameters.get(RequestParameters.USER_REPASSWORD)));
		setCountry(getFirstOrNull(parameters.get(RequestParameters.USER_COUNTRY)));
		setMailings(parameters.get(RequestParameters.USER_MAILINGS));
		setAcceptTerms(getFirstOrNull(parameters.get(RequestParameters.USER_ACCEPT_TERMS)));
	}

	private String getFirstOrNull(String[] objects) {
		if (objects == null) {
			return null;
		}
		return objects[0];
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}

	/**
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the acceptTerms
	 */
	public String getAcceptTerms() {
		return acceptTerms;
	}

	/**
	 * @param acceptTerms the acceptTerms to set
	 */
	public void setAcceptTerms(String acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	/**
	 * @return the mailings
	 */
	public String[] getMailings() {
		return mailings;
	}

	/**
	 * @param mailings the mailings to set
	 */
	public void setMailings(String[] mailings) {
		this.mailings = mailings;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
