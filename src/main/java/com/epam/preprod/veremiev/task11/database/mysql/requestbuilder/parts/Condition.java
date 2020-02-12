package com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts;

/**
 * MYSQL query condition
 * 
 * @author Illia_Veremiev
 *
 */
public class Condition {

	/**
	 * Comparing rule
	 */
	private Rule rule;

	/**
	 * Field to compare
	 */
	private String field;

	/**
	 * Value to compare
	 */
	private String value;

	private Condition(Field field, Rule rule, Object value) {
		this.field = field.toString();
		this.rule = rule;
		this.value = value.toString();
	}

	/**
	 * Returns new condition instance if value is not null
	 * 
	 * @param field - field to compare
	 * @param rule  - comparing rule
	 * @param value - value to compare
	 * @return new condition instance if value is not null
	 */
	public static Condition make(Field field, Rule rule, Object value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		Condition c = new Condition(field, rule, value);
		return c;
	}

	@Override
	public String toString() {
		return rule.obtainData(field, value);
	}
}
