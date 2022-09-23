package com.testAutomation.enums;

/**
 * This class returns the endpoint value which will be used
 * as a resource while sending the API request 
 */
public enum APIConstants {

	/**
     * Get the list of users of page 2
     */
	getUsers("/users?page=2");
	
	
	private String resourceValue;

	APIConstants(String value) {
		this.resourceValue = value;
	}

	public String getval() {
		return resourceValue;
	}
}
