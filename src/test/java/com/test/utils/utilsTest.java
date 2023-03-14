package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePath;

public class utilsTest {
	private FileInputStream stream = null;
	private Properties propertyFile = null;
	
	
	public Properties loadFile(String fileName) {
		System.out.println("loading started");
		String propertyFilePath = null;
		switch (fileName) {
		case "config.properties":
			System.out.println("login file found");
			System.out.println(SourcePath.CONFG_PROPERTIES_FILE);
			propertyFilePath = SourcePath.CONFG_PROPERTIES_FILE;
			break;
		default:
			System.out.println("No file found");
		}
		try {
			System.out.println("Loading the login propertyFile");
			stream = new FileInputStream(SourcePath.CONFG_PROPERTIES_FILE);
			propertyFile = new Properties();
			propertyFile.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	System.out.println("Returning propertyfile");
		return propertyFile;
	}

	public  String getConfigProperty(String key) {
		//	System.out.println("Entered getPropertyData");
		//	System.out.println(propertyFile);
		/*
		 * if (propertyFile.isEmpty()) { System.out.println("property file empty"); }
		 * else { System.out.println("Property file is  not empty"); }
		 */
			String value = propertyFile.getProperty(key);
			System.out.println("Property we got from this file is:" + value);
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return value;
		}

	
}
