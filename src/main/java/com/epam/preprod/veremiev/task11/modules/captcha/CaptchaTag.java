package com.epam.preprod.veremiev.task11.modules.captcha;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Captcha tag
 * 
 * @author Illia_Veremiev
 *
 */
public class CaptchaTag extends SimpleTagSupport {

	/**
	 * Captcha
	 */
	private Captcha captcha;

	/**
	 * Captcha id
	 */
	private long id;

	/**
	 * @return the image
	 */
	public Captcha getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha - captcha to set
	 */
	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}

	/**
	 * @return the captchaId
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - captcha id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		StringBuilder builder = new StringBuilder();
		builder.append("<img src=\"data:image/png;base64,");
		builder.append(Base64.encodeBase64String(captcha.getImage()));
		builder.append("\" />");
		builder.append("<input type=\"hidden\" name=\"captcha-number\"value=\"");
		builder.append(id);
		builder.append("\"></input>");
		out.print(builder.toString());
	}
}
