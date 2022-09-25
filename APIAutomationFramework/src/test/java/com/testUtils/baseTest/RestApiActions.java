package com.testUtils.baseTest;

import static com.testUtils.baseTest.SpecBuilder.getRequestSpec;
import static com.testUtils.baseTest.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.output.WriterOutputStream;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.gson.JsonObject;
import com.testAutomation.utils.report.ExtentLogger;

import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiActions {

	/**
	 * POST method
	 * 
	 * @param baseURL, resourcePath, accessToken , payLoad
	 * 
	 * @return Response
	 */
	public static Response POST(String baseURI, String resourcePath, String accessToken, String payLoad) {

		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);
		// Example For Bearer Token
		/*
		 * Response response = given(getRequestSpec()).header("Authorization", "Bearer "
		 * + accessToken).body(payLoad).auth().oauth2(accessToken) .filter(new
		 * RequestLoggingFilter(captor)).when()
		 */

		Response response = given(getRequestSpec(baseURI)).body(payLoad).auth().oauth2(accessToken)
				.filter(new RequestLoggingFilter(captor)).when().

				post(resourcePath).then().spec(getResponseSpec(baseURI)).extract().response();

		printDetailsInExtentReport(writerRequest, response);
		return response;
	}

	/**
	 * GET method
	 * 
	 * @param baseURI, resourcePath, accessToken
	 * 
	 * @return Response
	 */
	public static Response GET(String baseURI, String resourcePath, String accessToken) {

		
		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);
		Map<String, String> js = new HashMap<String, String>();
		js.put("limit", "1");
		Response response = given(getRequestSpec(baseURI)).auth().oauth2(accessToken)
				.filter(new RequestLoggingFilter(captor)).when().get(resourcePath).then()
				.extract().response();

		printDetailsInExtentReport(writerRequest, response);
		return response;
	}

	/**
	 * PUT method
	 * 
	 * @param resourcePath, accessToken, payload
	 * 
	 * @return Response
	 */
	public static Response PUT(String baseURI, String resourcePath, String accessToken, Object payLoad) {

		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response = given(getRequestSpec(baseURI)).body(payLoad).
		// header("Authorization", "Bearer "+accessToken).
				auth().oauth2(accessToken).filter(new RequestLoggingFilter(captor)).when().

				put(resourcePath).then().

				extract().response();

		printDetailsInExtentReport(writerRequest, response);
		return response;
	}

	/**
	 * Returns value from JSON based on JSONPath
	 * 
	 * @param Response, jsonPath
	 * 
	 * @return a <T>
	 */
	public static <T> T getDataFromJsonPath(Response response, String jsonPath) {

		JsonPath jpath = response.jsonPath();
		return jpath.get(jsonPath);
	}
	
	/**
	 * Returns List of MAP 
	 * 
	 * @param Response
	 * 
	 * @return List<Map<String,Object>>
	 */
	public static  List<Map<String,Object>> getResponseAsList(Response response) {
		 List<Map<String,Object>> responseBody = null;
		 responseBody = response.as(new TypeRef<List<Map<String,Object>>>() {});
		 return responseBody;
	}

	/**
	 * This method logs the request and response in the Extent Report
	 * 
	 * @param request, response
	 * 
	 * @return
	 */
	private static void printDetailsInExtentReport(StringWriter request, Response response) {

		ExtentLogger.info("<details><summary><i><font color=black> Request details: </font></i>" + "</summary>"
				+ "<pre>" + request.toString() + "</pre>" + "</details> \n");
		ExtentLogger.info("<details><summary><i><font color=black> Response details: </font></i>" + "</summary>"
				+ "<pre>" + response.asString() + "</pre>" + "</details> \n");
		ExtentLogger.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));

	}

}
