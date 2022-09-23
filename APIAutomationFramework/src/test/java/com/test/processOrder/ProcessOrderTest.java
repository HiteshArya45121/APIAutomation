package com.test.processOrder;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import com.testUtils.baseTest.RestApiActions;
import com.testUtils.baseTest.SpecBuilder;
import com.testAutomation.enums.APIConstants;
import com.testAutomation.enums.StatusCodes;
import com.testAutomation.pojos.ProcessOrder;

public class ProcessOrderTest extends SpecBuilder {

	Response RESPONSE;
	private String orderStatusCol = "orderStatus";
	private String lastUpdatedTimestampCol = "lastUpdatedTimestamp";
	private String TOKEN = "xcvcv8776";
	@Test(description = "verify Order status should be processed", priority = 1, enabled = true)
	public void validateOrderStatus() {

		RESPONSE = RestApiActions.post(APIConstants.valueOf("getUsers").getval(), "",
				RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RESPONSE.statusCode(), StatusCodes.CODE_201, "Status code not matched");
		assertEquals(RestApiActions.getDataFromJsonPath(RESPONSE, orderStatusCol).toString(),
				RESPONSE.as(ProcessOrder.class).getOrderStatus(), "Order status not matched");
	}

	@Test(description = "Verify last TimeStamp should be updated", priority = 2, enabled = true)
	public void validatelastUpdatedTimeStamp() {
		RESPONSE = RestApiActions.post(APIConstants.valueOf("getUsers").getval(), "",
				RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RestApiActions.getDataFromJsonPath(RESPONSE, lastUpdatedTimestampCol).toString(),
				RESPONSE.as(ProcessOrder.class).getLastUpdatedTimestamp(), " Time stamp not updated");
	}
	
	@Test(description = "Verify order should not be processed with Invalid token", priority = 2, enabled = true)
	public void ValidateAccessToken() {
		RESPONSE = RestApiActions.post(APIConstants.valueOf("getUsers").getval(), TOKEN,
				RequestPayloadObj.createPayloadProcessOrder());
		assertEquals(RESPONSE.statusCode(), StatusCodes.CODE_201, "Status code not matched");
	}
}
