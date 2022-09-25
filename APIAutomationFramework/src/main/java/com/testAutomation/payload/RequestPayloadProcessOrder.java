package com.testAutomation.payload;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.testAutomation.pojos.ProcessOrder;
import com.testAutomation.utils.TestDataUtils;

/**
 * This is the class for creating JSON Pay load at Runtime
 * 
 */
public class RequestPayloadProcessOrder extends TestDataUtils {

	protected Gson gson = new Gson();
	ProcessOrder processOrderObj = new ProcessOrder();

	public Map<String, String> setHeaders() {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("", "");
		return requestHeaders;

	}

	/**
	 * Returns a JSON String containing request payload
	 *
	 * @return a JSON String
	 */
	public String createPayloadProcessOrder() {

		processOrderObj.setOrderId(Integer.parseInt(getTestData("ProcessOrderPayload", "order", "Value")));
		processOrderObj.setOrderDescription(getTestData("ProcessOrderPayload", "Description", "Value"));
		processOrderObj.setOrderStatus(getTestData("ProcessOrderPayload", "Status", "Value"));
		processOrderObj.setLastUpdatedTimestamp(getTestData("ProcessOrderPayload", "LastUpdatedTimeStamp", "Value"));
		processOrderObj
				.setSpecialOrder(Boolean.parseBoolean(getTestData("ProcessOrderPayload", "SpecialOrder", "Value")));
		return gson.toJson(processOrderObj);

	}
}
