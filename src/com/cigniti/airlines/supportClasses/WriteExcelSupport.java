package com.cigniti.airlines.supportClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.utils.HtmlData;
import com.cigniti.airlines.utils.UtilitiesClass;


public class WriteExcelSupport extends UtilitiesClass{

	
	public boolean writeToExcelDocument(Map<String, List<HtmlData>> formatData,HttpSession session) throws Exception {
		boolean result=false;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		try {
			
			String sheetName="Sheet1";
			tcWorkbook = new XSSFWorkbook();
			File testCaseFile = null;
			if(props.get("OS").equals("MacOS")){
			testCaseFile = new File(session.getAttribute("modulePath")+"/Scenarios.xlsx");
			}else {
				testCaseFile = new File(session.getAttribute("modulePath")+"\\Scenarios.xlsx");
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
						Cell cell=row.createCell(0);
						row.createCell(0).setCellValue(hd.getFeature().toString());
					}else
					{
						Cell cell=row.createCell(0);
						row.createCell(0).setCellValue("");
					}
					
					Cell cell1=row.createCell(1);
					String pv=hd.getPossibleValue();
					cell1.setCellValue(pv);
					
					Cell cell2=row.createCell(2);
					String st=hd.getStatement();
					cell2.setCellValue(st);
					
					Cell cell3=row.createCell(3);
					String lt=hd.getLocatorType();
					cell3.setCellValue(lt);
					
					Cell cell4=row.createCell(4);
					String lv=hd.getLocatorValue();
					
					cell4.setCellValue(lv);
					
					Cell cell5=row.createCell(5);
					String op=hd.getOperation();
					cell5.setCellValue(op);
					
					Cell cell6=row.createCell(6);
					String td=hd.getTextData();
					cell6.setCellValue(td);
				
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
