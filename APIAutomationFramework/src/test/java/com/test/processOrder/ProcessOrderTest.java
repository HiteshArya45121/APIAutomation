package com.test.processOrder;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.testAutomation.constants.FrameworkConstants;
import com.testAutomation.enums.APIResource;
import com.testAutomation.enums.StatusCodes;

import com.testAutomation.utils.report.ExtentLogger;
import com.testAutomation.utils.report.Log;
import com.testUtils.baseTest.RestApiActions;
import com.testUtils.baseTest.SpecBuilder;

import io.restassured.response.Response;

public class ProcessOrderTest extends SpecBuilder {

	Response RESPONSE;
	private String orderStatusCol = "orderStatus";
	private String lastUpdatedTimestampCol = "lastUpdatedTimestamp";
	private String TOKEN = "xcvcv8776";
	private String Title = "title";
	private int Index = 0;
	private String OrderStatus = "New";
	private String lastUpdatedTimestamp = "1642321210439";
	private String ExpectedTitle ="Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops";

	@Test(description = "verify Order status should be processed", priority = 1, enabled = true)
	public void validateOrderStatus() {

		RESPONSE = RestApiActions.POST(FrameworkConstants.BASEURI_USERS, APIResource.valueOf("getUsers").getval(), "",
				RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RESPONSE.statusCode(), StatusCodes.CODE_201, "Status code not matched");
		assertEquals(RestApiActions.getDataFromJsonPath(RESPONSE, orderStatusCol).toString(), OrderStatus,
				"Order status not matched");

	}

	@Test(description = "Verify last TimeStamp should be updated", priority = 2, enabled = true)
	public void validatelastUpdatedTimeStamp() {
		RESPONSE = RestApiActions.POST(FrameworkConstants.BASEURI_USERS, APIResource.valueOf("getUsers").getval(), "",
				RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RestApiActions.getDataFromJsonPath(RESPONSE, lastUpdatedTimestampCol).toString(),
				lastUpdatedTimestamp, " Time stamp not updated");
	}

	// Forcefully failing the Test
	@Test(description = "Verify order should not be processed with Invalid token", priority = 3, enabled = true)
	public void ValidateAccessToken() {
		RESPONSE = RestApiActions.POST(FrameworkConstants.BASEURI_PRODUCTS, APIResource.valueOf("products").getval(),
				TOKEN, RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RESPONSE.statusCode(), StatusCodes.CODE_201, "Status code not matched");
	}

	@Test(description = "Verify Get Title", priority = 4, enabled = true)
	public void getProducts() {
		RESPONSE = RestApiActions.GET(FrameworkConstants.BASEURI_PRODUCTS, "", "");
		assertEquals(RESPONSE.statusCode(), StatusCodes.CODE_200, "Status code not matched");
		ExtentLogger.info("Title =  " + RestApiActions.getResponseAsList(RESPONSE).get(Index).get(Title).toString());
		Log.info(RestApiActions.getResponseAsList(RESPONSE).get(Index).get(Title).toString());
		assertEquals( RestApiActions.getResponseAsList(RESPONSE).get(Index).get(Title).toString(),ExpectedTitle);
		

	}
}
