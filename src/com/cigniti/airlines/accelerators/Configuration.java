package com.cigniti.airlines.accelerators;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cigniti.airlines.utils.UtilitiesClass;

public class Configuration extends UtilitiesClass {
	
	public Map<String, String> loadPropertiesData()
	{
		InputStream inputStream = null;
		try {
			props = new HashMap<String, String>();
			String fileName = "TestData.properties";
			Properties properties = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(fileName + " : File not found ");
			}

			props = new HashMap<String, String>();
			
			props.put("ProjectDirectory", properties.getProperty("projectDirectory"));
			props.put("MinPaxCount", properties.getProperty("minPaxCount"));
			props.put("MaxPaxCount", properties.getProperty("maxPaxCount"));
			props.put("OS", properties.getProperty("os"));
			
			System.out.println("----------ProjectDirectory ---------"+props.get("ProjectDirectory"));
			
			propData=props;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;

	}
	
	public String getProjectDirectory()
	{
		return propData.get("ProjectDirectory");
	}

}
