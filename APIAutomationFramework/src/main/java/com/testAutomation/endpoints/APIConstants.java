package com.testAutomation.endpoints;

public enum APIConstants {

	getUsers("/users?page=2");

	private String resourceValue;

	APIConstants(String value) {
		this.resourceValue = value;
	}

	public String getval() {
		return resourceValue;
	}
}
