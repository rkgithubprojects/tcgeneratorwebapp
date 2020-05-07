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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.utils.HtmlData;
import com.cigniti.airlines.utils.UtilitiesClass;

public class TestDataSupport extends UtilitiesClass{
	public Set<String> allFeatures=new HashSet<>();
	public Set<String> allPossibleValues=new HashSet<>();
	public Set<String> allOperations=new HashSet<>();
	public List<HtmlData> readExcelData(HttpSession session) throws Exception
	{
		List<HtmlData> list=new ArrayList<>();
		Workbook readWorBook =null;
		Sheet readSheet =null;
		File readFile = null;
		if(props.get("OS").equals("MacOS")){
		readFile = new File(session.getAttribute("modulePath")+"/TestData.xlsx");
		}else {
			readFile = new File(session.getAttribute("modulePath")+"\\TestData.xlsx");
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
			
			
				String statement=row.getCell(2).getStringCellValue();
				hd.setFeature(feature);
				
				hd.setPossibleValue(row.getCell(1).getStringCellValue());
				hd.setStatement(statement);
				hd.setLocatorType(row.getCell(3).getStringCellValue());
				hd.setLocatorValue(row.getCell(4).getStringCellValue());
				hd.setOperation(row.getCell(5).getStringCellValue());
				allOperations.add(row.getCell(5).getStringCellValue());
				

				Cell cell=row.getCell(6);
				int cellType=cell.getCellType();
				String textData="";
				if(cellType==cell.CELL_TYPE_NUMERIC)
				{
					textData=""+(int)cell.getNumericCellValue();
				}
				else
				{
					textData=cell.getStringCellValue();
				}
				hd.setTextData(textData);
				list.add(hd);
				if(!row.getCell(0).getStringCellValue().equals(""))
				{
					allFeatures.add(row.getCell(0).getStringCellValue());
				}
				if(!row.getCell(1).getStringCellValue().equals(""))
				{
					allPossibleValues.add(row.getCell(1).getStringCellValue());
				}
			}
			
		}
		return list;
	}

	public boolean writeToExcelDocument(Map<String, List<HtmlData>> formatData ,HttpSession session) throws Exception {
		boolean result=false;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		try {

			String sheetName="Sheet1";
			tcWorkbook = new XSSFWorkbook();
			File testCaseFile = null;
			if(props.get("OS").equals("MacOS")){
			testCaseFile=new File(session.getAttribute("modulePath")+"/TestData.xlsx");
			}else {
				testCaseFile=new File(session.getAttribute("modulePath")+"\\TestData.xlsx");	
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
					
					row.createCell(1).setCellValue(hd.getPossibleValue());
					row.createCell(2).setCellValue(hd.getStatement());
					row.createCell(3).setCellValue(hd.getLocatorType());
					row.createCell(4).setCellValue(hd.getLocatorValue());
					row.createCell(5).setCellValue(hd.getOperation());
					row.createCell(6).setCellValue(hd.getTextData());				
					rowCount++;
					listCount++;
				}
				listCount=0;
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
