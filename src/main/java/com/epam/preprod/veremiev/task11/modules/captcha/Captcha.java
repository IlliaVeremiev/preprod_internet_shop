package com.epam.preprod.veremiev.task11.modules.captcha;

/**
 * Captcha data container
 * 
 * @author Illia_Veremiev
 *
 */
public class Captcha {

	/**
	 * String representation
	 */
	private String captchaText;

	/**
	 * Image representation
	 */
	private byte[] captchaImage;

	/**
	 * Constructor without parameters
	 */
	public Captcha() {
		captchaText = org.javalite.activeweb.Captcha.generateText();
		captchaImage = org.javalite.activeweb.Captcha.generateImage(captchaText);

	}

	/**
	 * @return the captchaText
	 */
	public String getText() {
		return captchaText;
	}

	/**
	 * @return the captchaImage
	 */
	public byte[] getImage() {
		return captchaImage;
	}
}