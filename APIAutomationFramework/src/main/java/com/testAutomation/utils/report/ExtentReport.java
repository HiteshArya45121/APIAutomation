package com.testAutomation.utils.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {

	private ExtentReport() {
	}

	private static ExtentReports extent;

	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/extent-reports/" + "ExtentReportsTestNG.html");

			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			extent.setSystemInfo("Demo",  "Assignment" );
		
		}
	}

	public static void flushReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}

		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "/extent-reports/" + "ExtentReportsTestNG.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	}

	
}
