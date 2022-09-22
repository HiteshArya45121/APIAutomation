package com.test.processOrder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.test.testUtils.BaseTest;
import com.testAutomation.endpoints.APIConstants;

public class ProcessOrderTest extends BaseTest {

	Response resp;

	@Test(priority = 1)
	public void validateProcessOrder() {
		System.out.println(requestPayloadObj.createPayloadProcessOrder());
	}

	@Test(priority = 2)
	public void validategetUsers() {
		resp = RestAssured.given().spec(repoSpec).get(APIConstants.valueOf("getUsers").getval());
		resp.prettyPrint();
	}
}
