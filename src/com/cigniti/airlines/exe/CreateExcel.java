package com.cigniti.airlines.exe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.supportClasses.PropertiesSupport;
import com.cigniti.airlines.utils.BaseClass;

public class CreateExcel extends BaseClass{
	
public void createVerificationSheet(String modulePath,String fileName,String sheetName)
{
	try
	{
		String filePath = null;
		if(props.get("OS").equals("MacOS")){
			filePath=modulePath+"/"+fileName+".xlsx";
		}else {
			filePath=modulePath+"\\"+fileName+".xlsx";
		}
		
		//Create blank workbook
	    XSSFWorkbook scenarioWB = new XSSFWorkbook(); 
	    
	    //Create a blank sheet
	    XSSFSheet scenarioSheet = scenarioWB.createSheet(sheetName);
	    
	    //Create row object
	    XSSFRow row=scenarioSheet.createRow(0);
	  //  XSSFRow dummyRow=scenarioSheet.createRow(1);
	    row.createCell(0).setCellValue("Feature");
	   
	    row.createCell(1).setCellValue("Statement");
	    row.createCell(2).setCellValue("Locator Type");
	    row.createCell(3).setCellValue("Locator Value");
	    row.createCell(4).setCellValue("Operation");
	    row.createCell(5).setCellValue("Text Data");
	  
	    /*dummyRow.createCell(0).setCellValue("Dummy");
	    dummyRow.createCell(1).setCellValue("Dummy");
	    dummyRow.createCell(2).setCellValue("Dummy");
	    dummyRow.createCell(3).setCellValue("Dummy");
	    dummyRow.createCell(4).setCellValue("Dummy");
	    dummyRow.createCell(5).setCellValue("Dummy");*/
	   
	    
	    //Write the workbook in file system
	    FileOutputStream out = new FileOutputStream(new File(filePath));
	    scenarioWB.write(out);
	    out.close();
	    System.out.println(fileName+" written successfully" );
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public void createExcelSheet(String modulePath,String fileName,String sheetName)
{
	try
	{
		String filePath = null;
		if(props.get("OS").equals("MacOS")){
			filePath=modulePath+"/"+fileName+".xlsx";
		}else {
			filePath=modulePath+"\\"+fileName+".xlsx";
		}
		
		//Create blank workbook
	    XSSFWorkbook scenarioWB = new XSSFWorkbook(); 
	    
	    //Create a blank sheet
	    XSSFSheet scenarioSheet = scenarioWB.createSheet(sheetName);
	    
	    //Create row object
	    XSSFRow row=scenarioSheet.createRow(0);
	    XSSFRow dummyRow=scenarioSheet.createRow(1);
	    row.createCell(0).setCellValue("Feature");
	    row.createCell(1).setCellValue("Possible Value");
	    row.createCell(2).setCellValue("Statement");
	    row.createCell(3).setCellValue("Locator Type");
	    row.createCell(4).setCellValue("Locator Value");
	    row.createCell(5).setCellValue("Operation");
	    row.createCell(6).setCellValue("Text Data");
	  
	    dummyRow.createCell(0).setCellValue("Dummy");
	    dummyRow.createCell(1).setCellValue("Dummy");
	    dummyRow.createCell(2).setCellValue("Dummy");
	    dummyRow.createCell(3).setCellValue("Dummy");
	    dummyRow.createCell(4).setCellValue("Dummy");
	    dummyRow.createCell(5).setCellValue("Dummy");
	    dummyRow.createCell(6).setCellValue("Dummy");
	    
	    //Write the workbook in file system
	    FileOutputStream out = new FileOutputStream(new File(filePath));
	    scenarioWB.write(out);
	    out.close();
	    System.out.println(fileName+" written successfully" );
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public void createBrulesSheet(String modulePath,String fileName,String sheetName)
{
	try
	{
		String filePath = null;
		if(props.get("OS").equals("MacOS")){
			filePath=modulePath+"/"+fileName+".xlsx";
		}else {
			filePath=modulePath+"\\"+fileName+".xlsx";
		}
		
		//Create blank workbook
	    XSSFWorkbook scenarioWB = new XSSFWorkbook(); 
	    
	    //Create a blank sheet
	    XSSFSheet scenarioSheet = scenarioWB.createSheet(sheetName);
	    
	    //Create row object
	 
	    XSSFRow dummyRow=scenarioSheet.createRow(0);
	   
	  
	    dummyRow.createCell(0).setCellValue("R1");
	    dummyRow.createCell(1).setCellValue("R2");
	    
	    //Write the workbook in file system
	    FileOutputStream out = new FileOutputStream(new File(filePath));
	    scenarioWB.write(out);
	    out.close();
	    System.out.println(fileName+" written successfully" );
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}



}