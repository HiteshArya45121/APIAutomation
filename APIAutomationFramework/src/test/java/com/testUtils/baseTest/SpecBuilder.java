package com.testUtils.baseTest;

import com.testAutomation.payload.RequestPayloadProcessOrder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	protected RequestPayloadProcessOrder RequestPayloadObj = new RequestPayloadProcessOrder();
	
	public static RequestSpecification getRequestSpec(String baseURI) {
		return new RequestSpecBuilder().setBaseUri(baseURI)
				.setContentType(ContentType.JSON).log(LogDetail.ALL).build();
	}

	public static ResponseSpecification getResponseSpec(String baseURI) {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();
	}

}
