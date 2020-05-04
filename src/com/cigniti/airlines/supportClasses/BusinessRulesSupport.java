package com.cigniti.airlines.supportClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.utils.UtilitiesClass;

public class BusinessRulesSupport extends UtilitiesClass {
	public Map<Integer, List<String>> readBusinessRules(HttpSession session) throws FileNotFoundException {
		
		Workbook readWorBook =null;
		Sheet readSheet =null;
		String filePath="";
		//key value starts from 1
		Map<Integer, List<String>> rules=new HashMap<>();
		try
		{
				// filePath="C:\\ABC\\Output\\TestDataFiles\\BusinessRules.xlsx";
				 filePath=session.getAttribute("modulePath")+"/BusinessRules.xlsx";
				File readFile=new File(filePath);
			if(readFile!=null)
			{
				InputStream inputStream = new FileInputStream(readFile);
				readWorBook = new XSSFWorkbook(inputStream);
			}
			
			if(readWorBook!=null)
			{
				readSheet=readWorBook.getSheet("Sheet1");
			}
			
			if(readSheet!=null)
			{
				List<String> rule=null;
				int rowsCount=readSheet.getLastRowNum()-readSheet.getFirstRowNum();
				
				for (int i = 0; i <= rowsCount; i++) {
					
					Row row= readSheet.getRow(i);
					
					int cellCount=row.getLastCellNum()-row.getFirstCellNum();
					 rule=new ArrayList<>();
							
					for (int j = 0; j < cellCount; j++) {
						rule.add(row.getCell(j).getStringCellValue());
					}
					
					rules.put(i+1, rule);
				}
			}
		}
		catch(FileNotFoundException e)
		{ 
		 
			String sheetName="Sheet1";
			XSSFWorkbook tcWorkbook = new XSSFWorkbook();
			File testCaseFile = new File(filePath);
			FileOutputStream tcOutputStream = new FileOutputStream(testCaseFile);
			Sheet tcOutsheet = tcWorkbook.createSheet(sheetName);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rules;
	}
	
	public boolean writeBusinessRules(Map<Integer, List<String>> bRules,HttpSession session) throws IOException
	{
		Workbook readWorBook =null;
		Sheet readSheet =null;
		FileOutputStream tcOutputStream=null;
		
		boolean result=false;
		
		try
		{
			String sheetName="Sheet1";
			readWorBook = new XSSFWorkbook();
			//File testCaseFile = new File("C:\\ABC\\Output\\TestDataFiles\\BusinessRules.xlsx");
			File testCaseFile = new File(session.getAttribute("modulePath")+"/BusinessRules.xlsx");
			tcOutputStream = new FileOutputStream(testCaseFile);
			readSheet = readWorBook.createSheet(sheetName);
			
			
				for (int i = 0; i < bRules.size(); i++) {
					
					Row row=readSheet.createRow(i);
					List<String> rule=bRules.get(i+1);
					int cellCount=rule.size();
					
					for (int j = 0; j < cellCount; j++) {
						Cell cell=row.createCell(j);
						cell.setCellValue(rule.get(j));
					}				
				}

			readWorBook.write(tcOutputStream);
			result=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			readWorBook.close();
			tcOutputStream.close();
		}
		return result;
	}

	public String getFilePath(HttpSession session) {
		String path=(String) session.getAttribute("modulePath");
		return path;
	}

	
}
