package com.testAutomation.utils;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDataUtils {

	private static Logger Log = LogManager.getLogger(TestDataUtils.class.getName());

	static String dataFilePath = System.getProperty("user.dir") + "/resources/" + "TestData.xlsx";
	static String propertyFilePath = System.getProperty("user.dir") + "/resources/" + "Global.properties";

	public static String getTestData(String sheetname, String payload, String columnName) {
		String value = null;
		try {

			Fillo filObj = new Fillo();
			Log.info("fillo filepath is:" + dataFilePath);
			Connection connection = filObj.getConnection(dataFilePath);
			String strQuery = "Select * from " + sheetname + " " + "where Payload='" + payload + "'";
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

	public static String getGlobalValue(String key) {
		Properties prop = new Properties();
		try {

			FileInputStream fis = new FileInputStream(propertyFilePath);
			prop.load(fis);

		} catch (Exception e) {
			e.getMessage();
			Log.error("unable to read property from global.properties");
		}
		return prop.getProperty(key);

	}
}
