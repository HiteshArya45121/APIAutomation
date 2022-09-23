package com.testUtils.baseTest;

import static com.testUtils.baseTest.SpecBuilder.getRequestSpec;
import static com.testUtils.baseTest.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testAutomation.enums.StatusCodes;
import com.testAutomation.utils.report.ExtentLogger;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiActions {

	/**
	 * POST method
	 * 
	 * @param resourcePath, accessToken , payLoad
	 * 
	 * @return Response
	 */
	public static Response post(String resourcePath, String accessToken, String payLoad) {

		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);
		// Example For Bearer Token
		/* Response response =
		 given(getRequestSpec()).header("Authorization",
	              "Bearer " + accessToken).body(payLoad).auth().oauth2(accessToken)
		 .filter(new RequestLoggingFilter(captor)).when() */

		Response response = given(getRequestSpec()).body(payLoad).auth().oauth2(accessToken)
				.filter(new RequestLoggingFilter(captor)).when().

				post(resourcePath).then().spec(getResponseSpec()).extract().response();

		 printDetailsInExtentReport(writerRequest, response);
		return response;
	}

	/**
	 * GET method
	 * 
	 * @param resourcePath, accessToken
	 * 
	 * @return Response
	 */
	public static Response get(String resourcePath, String accessToken) {

		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response = given(getRequestSpec()).auth().oauth2(accessToken).filter(new RequestLoggingFilter(captor))
				.when().get(resourcePath).then().spec(getResponseSpec()).extract().response();

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
	public static Response put(String resourcePath, String accessToken, Object payLoad) {

		StringWriter writerRequest;
		PrintStream captor;
		writerRequest = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writerRequest), true);

		Response response = given(getRequestSpec()).body(payLoad).
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
	 * Asserts the status code
	 * 
	 * @param actualStatusCode , StatusCodes, message
	 * 
	 * @return a <T>
	 */
	public static void assertStatusCode(int actualStatusCode, int statusCode) {
		assertThat(actualStatusCode, equalTo(statusCode));
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
