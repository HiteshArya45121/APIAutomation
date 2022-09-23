package com.testAutomation.utils;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import com.testAutomation.constants.FrameworkConstants;
import com.testAutomation.utils.report.Log;

public class TestDataUtils {

	static String propertyFilePath = FrameworkConstants.PROJECT_PATH + FrameworkConstants.PROPERTYFILE_PATH;
	static String dataFilePath = FrameworkConstants.PROJECT_PATH + FrameworkConstants.TESTDATA_PATH;

	/**
	 * Returns value from TestData.xlsx based on Cell and Column 
	 * @param 	Sheetname, payloadRowValue, columnName  
	 *			
	 * @return a String
	 */
	public static String getTestData(String sheetname, String payloadRowValue, String columnName) {
		String value = null;
		try {

			Fillo filObj = new Fillo();
			Connection connection = filObj.getConnection(dataFilePath);
			String strQuery = "Select * from " + sheetname + " " + "where Payload='" + payloadRowValue + "'";
			Recordset recordset = null;

			recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				value = recordset.getField(columnName);
			}

			recordset.close();
			connection.close();

		}

		catch (Exception e) {
			e.getMessage();
			Log.error("Unable to Read the data from TestData.xlsx");
		}
		return value;
	}

	public static void insertData(String sheetName, String columnName, String value) {
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(dataFilePath);
			String strQuery = "INSERT INTO " + sheetName + "(" + columnName + ") VALUES('" + value + "')";
			Log.info("Insert Data Query is" + strQuery);
			connection.executeUpdate(strQuery);

			connection.close();
		} catch (Exception e) {
			e.getMessage();
			Log.error("unable to insert data in file");
		}
	}

}
