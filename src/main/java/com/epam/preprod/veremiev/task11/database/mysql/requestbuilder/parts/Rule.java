package com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts;

/**
 * MYSQL {@link Condition} rule
 * 
 * @author Illia_Veremiev
 *
 */
public enum Rule {
	EQUALS(Rule.FIELD_MARKER + " = " + Rule.VALUE_MARKER),
	BIGGEROREQUALS(Rule.FIELD_MARKER + " >= " + Rule.VALUE_MARKER),
	LESSOREQUALS(Rule.FIELD_MARKER + " <= " + Rule.VALUE_MARKER),
	IN(Rule.FIELD_MARKER + " IN (" + Rule.VALUE_MARKER + ")");

	private static final String FIELD_MARKER = "%f";
	private static final String VALUE_MARKER = "%v";

	private String pattern;

	Rule(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * Returns connected in the correct form condition
	 * 
	 * @param field - field to compare
	 * @param value - value to compare
	 * @return connected in the correct form condition
	 */
	public String obtainData(String field, String value) {
		return pattern.replace(FIELD_MARKER, field).replace(VALUE_MARKER, value);
	}
}
