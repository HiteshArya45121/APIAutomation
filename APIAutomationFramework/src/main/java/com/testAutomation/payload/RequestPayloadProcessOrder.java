package com.testAutomation.payload;

import com.google.gson.Gson;
import com.testAutomation.pojos.ProcessOrder;
import com.testAutomation.utils.TestDataUtils;

public class RequestPayloadProcessOrder extends TestDataUtils {

	protected Gson gson = new Gson();

	public String createPayloadProcessOrder() {
		ProcessOrder processOrderObj = new ProcessOrder();
		processOrderObj.setOrderId(Integer.parseInt(getTestData("ProcessOrderPayload", "order", "Value")));
		processOrderObj.setOrderDescription(getTestData("ProcessOrderPayload", "Description", "Value"));
		processOrderObj.setOrderStatus(getTestData("ProcessOrderPayload", "Status", "Value"));
		processOrderObj.setLastUpdatedTimestamp(getTestData("ProcessOrderPayload", "LastUpdatedTimeStamp", "Value"));
		processOrderObj.setSpecialOrder(Boolean.parseBoolean(getTestData("ProcessOrderPayload", "SpecialOrder", "Value")));
		return gson.toJson(processOrderObj);

	}
}
