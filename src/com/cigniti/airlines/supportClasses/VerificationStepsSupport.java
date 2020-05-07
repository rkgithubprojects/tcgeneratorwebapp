package com.cigniti.airlines.supportClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.Request;

import com.cigniti.airlines.utils.HtmlData;
import com.cigniti.airlines.utils.UtilitiesClass;

public class VerificationStepsSupport extends UtilitiesClass{

	public Set<String> allFeatures=new HashSet<>();
	
	public List<HtmlData> readExcelData(HttpSession session) throws Exception
	{
		
		List<HtmlData> list=new ArrayList<>();
		Workbook readWorBook =null;
		Sheet readSheet =null;
		File readFile=null;
		if(props.get("OS").equals("MacOS")){
		readFile=new File(session.getAttribute("modulePath")+"/StaticSteps.xlsx");
		}else {
			readFile=new File(session.getAttribute("modulePath")+"\\StaticSteps.xlsx");
		}
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
			
			int rowsCount=readSheet.getLastRowNum()-readSheet.getFirstRowNum()+1;
			
			
			for (int i = 0; i < rowsCount; i++) {
				
				HtmlData hd=new HtmlData();
				Row row=readSheet.getRow(i);
				
				String feature=(row.getCell(0).getStringCellValue()==null)?(""):(row.getCell(0).getStringCellValue());
			
				
				String statement=(row.getCell(1).getStringCellValue()==null)?(""):(row.getCell(1).getStringCellValue());
				
				
				hd.setFeature(feature);
				hd.setStatement(statement);
				hd.setLocatorType(row.getCell(2).getStringCellValue());
				hd.setLocatorValue(row.getCell(3).getStringCellValue());
				hd.setOperation(row.getCell(4).getStringCellValue());
				hd.setTextData(row.getCell(5).getStringCellValue());
				list.add(hd);
				
	
			}
			
		}
		return list;
	}
	
	
	public boolean writeToExcelDocument(Map<String, List<HtmlData>> formatData,HttpSession session) throws Exception {
		boolean result=false;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		try {

			
			String sheetName="Sheet1";
			tcWorkbook = new XSSFWorkbook();
			File testCaseFile=null;
			if(props.get("OS").equals("MacOS")){
			testCaseFile=new File(session.getAttribute("modulePath")+"/StaticSteps.xlsx");
			}else {
				testCaseFile=new File(session.getAttribute("modulePath")+"\\StaticSteps.xlsx");
			}
			tcOutputStream = new FileOutputStream(testCaseFile);
			Sheet tcOutsheet = tcWorkbook.createSheet(sheetName);
			Row row=null;
			int rowCount=0;
			int listCount=0;
			
			
			
			for (String featureValue : formatData.keySet()) {
				
				List<HtmlData> listData=formatData.get(featureValue);
				
				for (HtmlData hd : listData) 
				{
					row=tcOutsheet.createRow(rowCount);

					if(listCount==0)
					{
						row.createCell(0).setCellValue(hd.getFeature());
					}else
					{
						row.createCell(0).setCellValue("");
					}
					
					
					row.createCell(1).setCellValue(hd.getStatement());
					row.createCell(2).setCellValue(hd.getLocatorType());
					row.createCell(3).setCellValue(hd.getLocatorValue());
					row.createCell(4).setCellValue(hd.getOperation());
					row.createCell(5).setCellValue(hd.getTextData());
					rowCount++;
					listCount++;
				}
				listCount=0;
			}
			for (int i = 0; i <=6; i++) {
				tcOutsheet.autoSizeColumn(i);
			}
			
			tcWorkbook.write(tcOutputStream);
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			tcWorkbook.close();
			tcOutputStream.close();
		}
		return result;
	}

}
