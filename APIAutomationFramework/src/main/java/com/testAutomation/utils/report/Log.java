package com.testAutomation.utils.report;

import org.apache.logging.log4j.*;

public class Log {

	private static Logger Log = LogManager.getLogger(Log.class);

	public static void info(String message) {
		Log.info(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}
}
