package com.epam.preprod.veremiev.task11.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag display first, last and current page number
 * 
 * @author Illia_Veremiev
 *
 */
public class PageDisplayTag extends SimpleTagSupport {

	/**
	 * Current page
	 */
	private Integer current;

	/**
	 * Pages count
	 */
	private Integer max;

	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		StringBuilder builder = new StringBuilder();
		builder.append("<div class=\"row mt-5\"><div class=\"col text-center\"><div class=\"block-27\"><ul>");
		if (current == null || current == 0) {
			current = 1;
		}
		if (max == null || max == 0) {
			max = 1;
		}

		List<Integer> head = new ArrayList<>();
		List<Integer> body = new ArrayList<>();
		List<Integer> tail = new ArrayList<>();

		fillByIntegers(head, 1, 3);
		fillByIntegers(body, current - 2, current + 2);
		fillByIntegers(tail, max - 2, max);

		head.removeAll(body);
		tail.removeAll(body);
		head.addAll(body);
		head.addAll(tail);
		builder.append(head.stream().filter(i -> i > 0).filter(i -> i <= max).map(i -> colorizeCurrent(i))
				.collect(Collectors.joining()));
		builder.append("</ul></div></div></div>");
		out.print(builder.toString());
	}

	private void fillByIntegers(List<Integer> list, int from, int to) {
		for (int i = from; i <= to; i++) {
			list.add(i);
		}
	}

	private String colorizeCurrent(int i) {
		if (i == current) {
			return "<li class=\"active\"><span href=\"#\" class=\"pagination-button\" value=\"" + i + "\">" + i
					+ "</span></li>";
		} else {
			return "<li><a href=\"#\" class=\"pagination-button\" value=\"" + i + "\">" + i + "</a></li>";
		}
	}

	/**
	 * @return the current
	 */
	public Integer getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Integer current) {
		this.current = current;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

}
