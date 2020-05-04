package com.cigniti.airlines.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cigniti.airlines.reports.Result;


public class BaseClass extends UtilitiesClass {
	

	/*
	 * Method to verify text
	 * @locator : locator value
	 * @expectedData : expected string value
	 * @statement : operation description/explanation
	 */

	private boolean verifyText(By locator, String expectedData, String statement) {
		boolean flag=false;
		try {
			highlightElement(locator);
			String actual = driver.findElement(locator).getText().toLowerCase().trim();
			String expected = expectedData.toLowerCase().trim();
			flag= actual.contains(expected);
		} 
		catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	/*
	 * Method to click on selected element
	 * @locator : element to be clicked locator value
	 */
	
	public boolean clickOnElement(By locator) throws Exception {
		boolean flag=false;
		try {
			highlightElement(locator);
			driver.findElement(locator).click();
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	/*
	 * Method to perform operation on selected element
	 * @operation : Type of Operation to be performed
	 * @locatorType : Locator Type to find element to perform operation
	 * @locatorValue : locator value
	 * @category : possible value that is selected for the selected feature type
	 * @textData : test data value from excel sheet
	 * @statement : explanation of step that is currently performed
	 */

	public void perform(String operation, String locatorType, String locatorValue, String category, String textData,
			String statement) throws Exception {
		
		boolean status=false;
		
		try {
			switch (operation.toUpperCase()) {
			case "CLICK":
				status=clickOnElement(getLocator(locatorType, locatorValue));
				break;

			case "SENDTEXT":
				status=sendText(getLocator(locatorType, locatorValue), textData);
				break;

			case "NAVIGATE":
				status = navigateToUrl(textData);
				break;

			case "GETTEXT":
				String text=getText(getLocator(locatorType, locatorValue));
				status=(text!="")&&(text!=null);
				break;

			case "VERIFYTEXT":
				status=verifyText(getLocator(locatorType, locatorValue), textData, statement);
				break;
				
			case "NCLICKS":
				status=clickNTimes(getLocator(locatorType, locatorValue), textData);
				break;
				
			case "N_1CLICKS":
				status=clickNMinus1Times(getLocator(locatorType, locatorValue), textData);
				break;
				
			
			case "VERIFYELEMENT":
				status=verifyElement(getLocator(locatorType, locatorValue));
				break;

			case "SELECT":
				status=selectByVisibleText(getLocator(locatorType, locatorValue), textData);
				break;

			case "SELECTBYVALUE":
				status=selectByValue(getLocator(locatorType, locatorValue), textData);
				break;

			case "SELECTMONTH":
				String month =selectMonth(getLocator(locatorType, locatorValue));
				status=(month!=null)&&(month.length()!=0);
				break;

			case "SELECTYEAR":
				String year=selectYear(paxType, getLocator(locatorType, locatorValue));
				status=(year.length()!=0)&&(year!=null);
			break;

			case "SELECTDAY":
				int day=0;
				day=selectDay(getLocator(locatorType, locatorValue));
				status=(day!=0);
				break;
				
			case "VERIFYVISIBILITY":
				status=visibilityOfElementLocated(getLocator(locatorType, locatorValue));
			break;
			
			case "VERIFYINVISIBILITY":
				status=invisibilityOfElementLocated(getLocator(locatorType, locatorValue));
			break;
			
			case "LOGIN":
				status= fillData("LOGINDETAILS");
			break;
			
			case "DRAFTEMAIL":
				status= fillData("EMAILINFORMATION");
			break;
						
			case "ENTERPASSENGERDETAILS":
				status= fillData("PASSENGERDETAILS");
			break;
			
			case "CABINBAGSELECTION":
				status= fillData("BAGDETAILS");
			break;
			
			case "SEATSELECTION":
				status= fillData("SEATSELECTIONDETAILS");
			break;
						
			case "BOOKTICKET":
				status= fillData("PAYMENTINFORMATION");
			break;
			
			case "JSECLICK":
				status=clickOnElementByUsingJSE(getLocator(locatorType, locatorValue));
				break;
				
			case "GETPNR":
				pnrNumber=getText(getLocator(locatorType, locatorValue));
				status=(pnrNumber!="")&&(pnrNumber!=null);
				break;
				
			case "GETJOURNEYDATE":
				journeyDate=getText(getLocator(locatorType, locatorValue));
				status=(journeyDate!="")&&(journeyDate!=null);
				break;
				
			case "GETORIGIN":
				origin=getText(getLocator(locatorType, locatorValue));
				status=(origin!="")&&(origin!=null);
				break;
				
			case "GETDESTINATION":
				destination=getText(getLocator(locatorType, locatorValue));
				status=(destination!="")&&(destination!=null);
				break;
				
			case "SELECTFLIGHT":
				status=selectFlight(getLocator(locatorType, locatorValue));
				break;
			case "NAVIGATETO":
				status=navigateTo(textData);
				break;
				
			default:
				/*System.out.println("The operation Type [ " + operation + " ] is not defined");*/
			status=executeReusableLogic(operation);
			break;
			}
		} catch (TimeoutException tx) {
			
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE);
			
		}
		catch(NoSuchElementException nse)
		{
			status = false;
			System.out.println("Element with [locator Type :"+locatorValue+"] and [locator value :"+locatorValue+"] is not found");
			throw nse;
		}
		catch(SessionNotFoundException sne)
		{
			
		}
		catch (Exception e) {
			status =false;
			e.printStackTrace();
		}
		finally
		{
			Result result = null;
			if (status) {
				System.out.println(statement.replace("$", category) + " --  Passed");
				result = new Result(operation, stepCount++, statement.replace("$", category), true,"");
			} else {
				System.out.println(statement.replace("$", category) + " --  Failed");
				String scrName=takeScreenShot();
				result = new Result(operation, stepCount++, statement.replace("$", category), false,scrName);
			}
			reports.add(result);
		}
	}
	
	
	private boolean selectFlight(By locator){
		boolean flag=false;
		List<WebElement> flightList=null;
		try
		{
			flightList = driver.findElements(locator);
			WebElement flight = flightList.get(getRandomInt(0,flightList.size() - 1));
			flightNumber = flight.getAttribute("data-ref");
			System.out.println("flightNumber>>>> " + flightNumber);
			flight.click();
			flag=true;
			
		}
		catch(Exception e)
		{
			flag=false;
			throw e;
		}
		return flag;
	}
	
	private boolean visibilityOfElementLocated(By locator) throws Exception {
		boolean flag=false;
		try {
			WebDriverWait wd = new WebDriverWait(driver,5);
			wd.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
			flag = true;
		} catch (Exception e) {
			flag=false;
			throw e;
			}
		return flag;
	}
	
	private boolean invisibilityOfElementLocated(By locator) {
		boolean flag=false;
		try {
			WebDriverWait wd = new WebDriverWait(driver,5);
			wd.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(locator)));
			flag = true;
		} catch (Exception e) {
			flag=false;
			throw e;
			}
		return flag;
	}
	
	public boolean fillData(String category) throws Exception {
		boolean flag=false;
		try {
			List<TestData> paxData = reusableDataSteps.get(category);
			for (TestData testData : paxData) {
				perform(testData.getOperation(), testData.getLocatorType(), testData.getLocatorValue(),
						testData.getTextData(), testData.getTextData(), testData.getStatement());
			}
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	
	public boolean clickOnElementByUsingJSE(By locator) throws Exception {
		boolean flag=false;
		try {
			highlightElement(locator);
			WebDriverWait wd = new WebDriverWait(driver,5);
			wd.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
			Thread.sleep(7000);
			((JavascriptExecutor) driver)
		     .executeScript("arguments[0].click();", driver.findElement(locator));
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}


	private boolean executeReusableLogic(String reusableOperation) throws Exception {
		boolean status =true;
		/*Map<String, List<TestData>> reusableDataSteps1 = new HashMap<>();*/
		/*reusableDataSteps1=reusableDataSteps;*/
		List<TestData> steps=reusableDataSteps.get(reusableOperation);
		
		for (TestData testData : steps) {
			
			String featureType = testData.getFeatureType();
			String locatorType = testData.getLocatorType();
			String operation = testData.getOperation();
			String statement = testData.getStatement();
			String locatorValue = testData.getLocatorValue();
			String category = testData.getCategory();
			String textData = testData.getTextData();
			try
			{
				perform(operation, locatorType, locatorValue, textData, textData, statement);
				
			}
			catch(Exception e)
			{
				status=false;
				throw e;
			}
		}
		return status;
	}

	private boolean clickNMinus1Times(By locator, String textData) {
		boolean status=false;
		try
		{
			int count=Integer.parseInt(textData);
			count=count-1;
			WebElement e=driver.findElement(locator);
			for (int i = 0; i < count; i++) {
				e.click();
			}
			status=true;
		}
		catch(Exception e)
		{
			status=false;
			throw e;
		}
		return status;
	}

	private boolean clickNTimes(By locator, String textData) {
		boolean status=false;
		try
		{
			int count=Integer.parseInt(textData);
			WebElement e=driver.findElement(locator);
			for (int i = 0; i < count; i++) {
				e.click();
			}
			status=true;
		}
		catch(Exception e)
		{status=false;
			throw e;
		}
		return status;
	}
	
	private String getText(By locator){
		String text="";
		try
		{
			highlightElement(locator);
			text=driver.findElement(locator).getText();
		}
		catch(Exception e)
		{
			throw e;
		}
		return text;
	}

	private boolean navigateToUrl(String url) throws Exception {
		boolean flag=false;
		try {
			driver.get(url);
			flag=true;
			Thread.sleep(5000);
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	private boolean navigateTo(String url) throws Exception {
		boolean flag=false;
		try {
			driver.navigate().to(url);
			flag=true;
			Thread.sleep(2000);
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	

	/*
	 * function to verify if element is present
	 * @locator : locator value
	 */
	
	private boolean verifyElement(By locator) {
		boolean flag=false;
		try {
			highlightElement(locator);
			flag=driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	private boolean selectByValue(By locator, String textData)  {
		boolean flag=false;
		try {
			highlightElement(locator);
			Select sel = new Select(driver.findElement(locator));
			sel.selectByValue(textData);
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	private boolean sendText(By locator, String textData) {
		boolean flag=false;
		try {
			highlightElement(locator);
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(textData);
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	private boolean selectByVisibleText(By locator, String textData) {
		boolean flag = false;
		try {
			highlightElement(locator);
			Select select = new Select(driver.findElement(locator));
			select.selectByVisibleText(textData);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	private String selectMonth(By locator) {
		String selectedMonth="";
		try {
			highlightElement(locator);
			Random rm = new Random();
			int startMonth = 1;
			int endMonth = 12;
			int month = rm.nextInt(endMonth - startMonth) + startMonth;
			Select monthDD = new Select(driver.findElement(locator));
			monthDD.selectByIndex(month);
			selectedMonth=monthDD.getFirstSelectedOption().getText();
			
		} catch (Exception e) {
			throw e;
		}
		return selectedMonth;
	}

	private int selectDay(By locator) {
		int day=0;
		try {
			highlightElement(locator);
			Random rd = new Random();
			int startDay = 1;
			int endDay = 29;
			 day = rd.nextInt(endDay - startDay) + startDay;
			Select dayDD = new Select(driver.findElement(locator));
			dayDD.selectByIndex(day);
		} catch (Exception e) {
			throw e;
		}
		return day;
	}

	private String selectYear(String paxType, By locator) {
		String yearInString="";
		try {
			highlightElement(locator);
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);

			switch (paxType.toUpperCase()) {
			case "SENIOR":
				year = year - 60;
				break;
			default:
				year = year - 18;
				break;
			}

			yearInString = String.valueOf(year);
			Select yearDd = new Select(driver.findElement(locator));
			yearDd.selectByVisibleText(yearInString);
		} catch (Exception e) {
			throw e;
		}
		return yearInString;
	}

}
