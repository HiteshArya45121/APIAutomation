package com.test.testUtils;

import org.testng.annotations.BeforeTest;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import com.testAutomation.payload.*;
import com.testAutomation.utils.TestDataUtils;

public class BaseTest {

	protected RequestSpecification repoSpec;
	protected RequestPayloadProcessOrder requestPayloadObj = new RequestPayloadProcessOrder();

	@BeforeTest
	protected void setUpConfiguration() {

		repoSpec = new RequestSpecBuilder().setBaseUri(TestDataUtils.getGlobalValue("BASE_URL")).setContentType("application/json").build()
				.log().all();
		
		
	}
}
