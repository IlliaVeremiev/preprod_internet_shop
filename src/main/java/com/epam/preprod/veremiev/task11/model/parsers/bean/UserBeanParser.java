package com.epam.preprod.veremiev.task11.model.parsers.bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.EmailValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.LoginValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.NameValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.PasswordValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.RePasswordValidator;
import com.epam.preprod.veremiev.task11.core.validators.impl.TermsValidator;
import com.epam.preprod.veremiev.task11.exceptions.MultipleException;
import com.epam.preprod.veremiev.task11.model.bean.UserDataBean;
import com.epam.preprod.veremiev.task11.model.entities.Mailing;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.model.entities.utils.Role;

/**
 * {@link UserDataBean} parser
 * 
 * @author Illia_Veremiev
 *
 */
public class UserBeanParser extends AbstractBeanParser<UserDataBean, User> {

	@Override
	public User parse(UserDataBean bean) throws MultipleException {
		User user = new User();
		MultipleException multipleException = new MultipleException();

		NameValidator nameValidator = new NameValidator(bean.getName());
		validCheck(nameValidator, multipleException, ExceptionMessages.INVALID_NAME);

		NameValidator surnameValidator = new NameValidator(bean.getSurname());
		validCheck(surnameValidator, multipleException, ExceptionMessages.INVALID_SURNAME);

		LoginValidator loginValidator = new LoginValidator(bean.getLogin());
		validCheck(loginValidator, multipleException, ExceptionMessages.INVALID_LOGIN);

		EmailValidator emailValidator = new EmailValidator(bean.getEmail());
		validCheck(emailValidator, multipleException, ExceptionMessages.INVALID_EMAIL);

		PasswordValidator passwordValidator = new PasswordValidator(bean.getPassword());
		validCheck(passwordValidator, multipleException, ExceptionMessages.INVALID_PASSWORD);

		RePasswordValidator repasswordValidator = new RePasswordValidator(bean.getRePassword(),
				passwordValidator.getConverted());
		validCheck(repasswordValidator, multipleException, ExceptionMessages.INVALID_REPASSWORD);

		TermsValidator termsValidator = new TermsValidator(bean.getAcceptTerms());
		validCheck(termsValidator, multipleException, ExceptionMessages.INVALID_ACCEPT_TERMS);

		if (multipleException.containsExceptions()) {
			throw multipleException;
		}

		user.setName(nameValidator.getConverted());
		user.setSurname(surnameValidator.getConverted());
		user.setLogin(loginValidator.getConverted());
		user.setEmail(emailValidator.getConverted());
		user.setPassword(passwordValidator.getConverted());
		user.setCountry(bean.getCountry());
		user.setPhoto(bean.getPhoto());

		List<Mailing> mailings = Arrays.stream(bean.getMailings()).map((str) -> new Mailing(str))
				.collect(Collectors.toList());

		user.setMailing(mailings);
		user.setRole(Role.CUSTOMER);

		return user;
	}

	private void validCheck(ConvertValidator<?> validator, MultipleException multipleException, String message) {
		if (!validator.isCastable()) {
			multipleException.addException(message);
		}
	}
}