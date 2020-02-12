package com.epam.preprod.veremiev.task11.model.entities.utils;

public enum Status {
	ACCEPTED("accepted"), CONFIRMED("confirmed"), IN_PROCESS("in_process"), SENDED("sended"), FINISHED("finished"),
	CANSELED("canseled");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	/**
	 * Returns {@link Status} instance by string representation
	 * 
	 * @param value - string value
	 * @return {@link Status} instance by string representation
	 */
	public static Status forValue(String value) {
		if (value == null) {
			return null;
		}
		Status[] statuses = Status.values();
		value = value.toLowerCase();
		for (Status status : statuses) {
			if (status.value.toLowerCase().equals(value)) {
				return status;
			}
		}
		return null;
	}

	/**
	 * Returns instance name
	 * 
	 * @return instance name
	 */
	public String toValue() {
		return this.value;
	}
}