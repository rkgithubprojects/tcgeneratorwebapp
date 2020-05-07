package com.cigniti.airlines.accelerators;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.cigniti.airlines.utils.BaseClass;
import com.cigniti.airlines.utils.TestData;

public class GenerateTestScript extends BaseClass {

	boolean result=true;
	
	/*
	 * Method to run test script
	 * @excelData : all possible combinations of testData in form of set
	 * @storeData : [@key : feature+possibleValue] and [@value :locator data in form of TestData object]
	 * @staticData : [@key : feature] and value [@value: locators data in the form of TestData object]
	 * @sheetData : sheet name for which test scripts are executed  
	 */
	
	public void runTestScript(Set<List<String>> excelData, Map<String, TestData> storeData,
			Map<String, List<TestData>> staticData, String sheetName,HttpSession session) {
		try {
			String s=(String) session.getAttribute("generateTS");
			if(s.equalsIgnoreCase("TRUE"))
			{
			currentSheetName=sheetName;
			executionStartTime=getCurrentTime();
			
			for (List<String> list : excelData) {
				
				tcCount=tcCount+1;
				stepCount=1;
				if(props.get("OS").equals("MacOS")){
				System.setProperty("webdriver.chrome.driver", props.get("ProjectDirectory")+"/macchromedriver");
				}
				else{
				System.setProperty("webdriver.chrome.driver", props.get("ProjectDirectory")+"\\winchromedriver.exe");
				}
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("disable-infobars");
				opt.addArguments("--start-maximized");
				opt.addArguments("--disable-extensions");
				driver = new ChromeDriver();
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				initialize(staticData);

				for (String key : list) {

					TestData data = storeData.get(key);
					
					String featureType = data.getFeatureType();
					String locatorType = data.getLocatorType();
					String operation = data.getOperation();
					String statement = data.getStatement();
					String locatorValue = data.getLocatorValue();
					String category = data.getCategory();
					String textData = data.getTextData();

					if (category.equalsIgnoreCase("ONEWAY")) {
						isOneWay = true;
					}
					try
					{
						perform(operation, locatorType, locatorValue, category, textData, statement);
						executeStaticSteps(staticData, data);
					}
					catch(Exception e)
					{
						closeDriver();
						result=false;
						break;
					}
				}
				if(result)
				{
					closeDriver();
				}
				sheetName = "DemoTestCase";
				String currentTestCaseName=sheetName+tcCount;
				allReports.put(currentTestCaseName, reports);
				reports= new ArrayList<>();
				writePNRInformationToExcel(tcCount,session);	
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			executionEndTime=getCurrentTime();
		}
	}
	public void writePNRInformationToExcel(int rowindex, HttpSession session) {
		boolean status=false;
		InputStream inputStream = null;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		try
		{
			String sheetName="Sheet1";
			File testCaseFile = null;
			if(props.get("OS").equals("MacOS")){
				testCaseFile = new File(session.getAttribute("modulePath")+"/GeneratedTestCases.xlsx");
			}else {
				testCaseFile = new File(session.getAttribute("modulePath")+"\\GeneratedTestCases.xlsx");
			}
			
			inputStream = new FileInputStream(testCaseFile);
			tcWorkbook = new XSSFWorkbook(inputStream);
			Sheet tcOutsheet = tcWorkbook.getSheet(sheetName);
			int rowsCount = tcOutsheet.getLastRowNum() - tcOutsheet.getFirstRowNum();
			while (rowindex < rowsCount + 1) {
				Cell cell = null;
				if(rowindex == 1) {
					cell = tcOutsheet.getRow(0).createCell(3);
					cell.setCellValue("Flight Number");
					cell = tcOutsheet.getRow(0).createCell(4);
					cell.setCellValue("PNR Number");
					cell = tcOutsheet.getRow(0).createCell(5);
					cell.setCellValue("PNR Validity");
					cell = tcOutsheet.getRow(0).createCell(6);
					cell.setCellValue("Origin");
					cell = tcOutsheet.getRow(0).createCell(7);
					cell.setCellValue("Destination");
					cell = tcOutsheet.getRow(rowindex).createCell(3);
					cell.setCellValue(flightNumber);
					flightNumber = "";
					cell = tcOutsheet.getRow(rowindex).createCell(4);
					cell.setCellValue(pnrNumber);
					pnrNumber = "Booking Failed";
					cell = tcOutsheet.getRow(rowindex).createCell(5);
					cell.setCellValue(journeyDate);
					journeyDate = "";
					cell = tcOutsheet.getRow(rowindex).createCell(6);
					cell.setCellValue(origin);
					origin = "";
					cell = tcOutsheet.getRow(rowindex).createCell(7);
					cell.setCellValue(destination);
					destination = "";
					break;
					
				}else {
				cell = tcOutsheet.getRow(rowindex).createCell(3);
				cell.setCellValue(flightNumber);
				flightNumber = "";
				cell = tcOutsheet.getRow(rowindex).createCell(4);
				cell.setCellValue(pnrNumber);
				pnrNumber = "Booking Failed";
				cell = tcOutsheet.getRow(rowindex).createCell(5);
				cell.setCellValue(journeyDate);
				journeyDate = "";
				cell = tcOutsheet.getRow(rowindex).createCell(6);
				cell.setCellValue(origin);
				origin = "";
				cell = tcOutsheet.getRow(rowindex).createCell(7);
				cell.setCellValue(destination);
				destination = "";
				break;
				}
			}
			inputStream.close();
			tcOutputStream = new FileOutputStream(testCaseFile);
			tcWorkbook.write(tcOutputStream);
			tcOutputStream.close();
			status=true;
		}
		catch(Exception e)
		{
			if(!status)
			{
				System.out.println("Error in writing PNR Information to excel");
			}
		e.printStackTrace();
	}
	}
	
	
	/*
	 * Method to execute verification/static steps
	 * @staticData : [@key : feature] and value [@value: locators data in the form of TestData object]
	 * @testData : Locators data in the form of TestData object
	 */
	public void executeStaticSteps(Map<String, List<TestData>> staticData, TestData testData) {
		try {
			List<TestData> staticSteps = new ArrayList<>();
			staticSteps = getValidStaticSteps(staticData, testData);
			if (staticSteps != null && staticSteps.size() > 0) {
				for (TestData data : staticSteps) {
					perform(data.getOperation(), data.getLocatorType(), data.getLocatorValue(), "", data.getTextData(),
							data.getStatement());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * get valid steps depending upon the test case combination.
	 * @staticData : [@key : feature] and value [@value: locators data in the form of TestData object]
	 * @testData : Locators data in the form of TestData object
	 */
	
	private List<TestData> getValidStaticSteps(Map<String, List<TestData>> staticData, TestData testData) {
		List<TestData> staticSteps = new ArrayList<>();
		try {

			staticSteps = staticData.get(testData.getFeatureType());
			if ((!isOneWay) && testData.getFeatureType().equalsIgnoreCase("CabinClassOut"))
			{
				staticSteps.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staticSteps;
	}

	
	/*
	 * Method to run init steps
	 */
	private void initialize(Map<String, List<TestData>> staticData) {
		try {
			List<TestData> initData = staticData.get("InitialSteps");
			for (TestData testData : initData) {
				perform(testData.getOperation(), testData.getLocatorType(), testData.getLocatorValue(), "",
						testData.getTextData(), testData.getStatement());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to open application URL
	 */
/*	public void openUrl() {
		try {
			driver.get(propData.get("URL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*
	 * Method to close browser
	 */
	
	public void closeDriver() {
		try {
			driver.close();
		} catch (Exception e) {
			throw e;
		}
	}


}
