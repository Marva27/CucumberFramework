package com.cucumberframework.www.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import com.cucumberframework.www.enums.BrowserType;
import com.cucumberframework.www.enums.ExecutionType;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath = "configs/configuration.properties";
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties file not found at " + propertyFilePath);
		}
	}

	public BrowserType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return BrowserType.CHROME;
		else if(browserName.equals("firefox")) return BrowserType.FIREFOX;
		else if(browserName.equals("iexplorer")) return BrowserType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in configuration.properties is not matched : " + browserName);
	}
	
	public ExecutionType getExecution() {
		String executionType = properties.getProperty("execution");
		if(executionType == null || executionType.equalsIgnoreCase("local")) return ExecutionType.LOCAL;
		else if(executionType.equals("remote")) return ExecutionType.REMOTE;
		else throw new RuntimeException("Execution Type Key value in configuration.properties is not matched : " + executionType);
	}
	
	public String getTestApplicationUrl() {
		String url = properties.getProperty("testUrl");
		if(url != null) return url;
		else throw new RuntimeException("testUrl not specified in configuration.properties file.");
	}
	
	public String getStageApplicationUrl() {
		String url = properties.getProperty("stageUrl");
		if(url != null) return url;
		else throw new RuntimeException("stageUrl not specified in configuration.properties file.");
	}

}
