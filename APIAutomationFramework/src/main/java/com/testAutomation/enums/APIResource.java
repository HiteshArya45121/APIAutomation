package com.testAutomation.enums;

/**
 * This class returns the endpoint value which will be used
 * as a resource while sending the API request 
 */
public enum APIResource {

	/**
     * Get the list of users of page 2
     */
	getUsers("/users?page=2"),
	products("/product");
	
	private String resourceValue;

	APIResource(String value) {
		this.resourceValue = value;
	}

	public String getval() {
		return resourceValue;
	}
}
