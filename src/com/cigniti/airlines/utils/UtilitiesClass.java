package com.cigniti.airlines.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/*import org.openqa.selenium.firefox.FirefoxDriver;*/

import com.cigniti.airlines.reports.Result;



public class UtilitiesClass {

	public static Map<String, String> propData = new HashMap<String, String>();
	public static Map<String, String> moduleData = new HashMap<String, String>();
	public static WebDriver driver = null;
	public static boolean isOneWay = false;
	public static Map<String, List<TestData>> reusableDataSteps = new HashMap<>();
	public static int adultsCount=0;
	public static int seniorsCount=0;
	public static int totalPax=0;
	public static String paxType="";
	public static int stepCount=1;
	public static int tcCount=0;
	public static List<Result> reports= new ArrayList<>();
	public static String executionStartTime="";
	public static String executionEndTime="";
	public static String currentSheetName="";
	public static Map<String, List<Result>> allReports=new HashMap<>();
	public String pnrNumber="Booking Failed";
	public String journeyDate="";
	public String flightNumber = "";
	private static Random rand = new Random();
	public String origin = "";
	public String destination = "";
	public static Map<String, String> props = null;
	
	public void highlightElement(By locator) {
		try {
			WebElement ele = driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", ele);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static int getRandomInt(int min, int max) {
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}
	
	public String getCurrentTime()
	{
		String currentTime="";
		try {
			Date date = null;
			String executionEndTime = "";
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			date = new Date();
			currentTime = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentTime;
	}
	
	/*
	 * Method to take screen shot of current opened page
	 */
	public static String takeScreenShot() throws Exception
	{
		String scrName="";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			TakesScreenshot scr=(TakesScreenshot)driver;
			File srcFile=scr.getScreenshotAs(OutputType.FILE);
			scrName="SCR_"+dateFormat.format(date)+".png";
			String projectDirectory = props.get("ProjectDirectory");
			FileUtils.copyFile(srcFile, new File(projectDirectory+"/Output/Reports/"+scrName));
												 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scrName;
	}
	
	public static By getLocator(String locatorType, String locatorValue) throws Exception {
		By locator = null;
		try {
			switch (locatorType) {
			case "XPATH":
				locator = By.xpath(locatorValue);
				break;

			case "CSS":
				locator = By.cssSelector(locatorValue);
				break;

			case "NAME":
				locator = By.name(locatorValue);
				break;

			case "ID":
				locator = By.id(locatorValue);
				break;

			case "CLASSNAME":
				locator = By.className(locatorValue);
				break;

			case "TAGNAME":
				locator = By.tagName(locatorValue);
				break;

			case "PARTIALLINKTEXT":
				locator = By.partialLinkText(locatorValue);
				break;

			case "LINKTEXT":
				locator = By.linkText(locatorValue);
				break;
				
			case "NA":
				locator = null;
				break;
			case "TESTDATA":
				locator = null;
				break;
			default:
				System.out.println("The specified locator [" + locatorType + "] is Invalid");
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot();
		}
		return locator;
	}

	public void createFolder(String folderPath) {

		File theDir = new File(folderPath);

		if (!theDir.exists()) {
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				System.out.println("Folder created");
			}

		}
	}

/*	public Map<String, String> getData() {

		Map<String, String> props = null;
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
			props.put("ReadFile", properties.getProperty("readFile"));
			props.put("WriteFile", properties.getProperty("writeFile"));
			props.put("TestCasesFile", properties.getProperty("testCasesFile"));
			props.put("MinPaxCount", properties.getProperty("minPaxCount"));
			props.put("MaxPaxCount", properties.getProperty("maxPaxCount"));
			props.put("ResultsPath", properties.getProperty("resultsPath"));
			props.put("BusinessRulesSheetName", properties.getProperty("businessRulesSheetName"));
			props.put("SheetNames", properties.getProperty("sheetNames"));
			props.put("URL", properties.getProperty("url"));
			props.put("GenerateTestCases", properties.getProperty("generateTestCases"));
			props.put("GenerateTestScript", properties.getProperty("generateTestScript"));
			props.put("ReportsDirectory", properties.getProperty("reportsDirectory"));
			props.put("VerificationSteps", properties.getProperty("verificationSteps"));
			props.put("TestCasesPath", properties.getProperty("testCasesPath"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}*/

		
}
