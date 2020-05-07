package com.cigniti.airlines.supportClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.utils.HtmlData;
import com.cigniti.airlines.utils.UtilitiesClass;

public class ReadExcelSupport extends UtilitiesClass{
	
	public Set<String> allFeatures=new HashSet<>();
	public Set<String> allOperations=new HashSet<>();
	public Set<String> allPossibleValues=new HashSet<>();
	public List<HtmlData> readExcelData(HttpSession session) throws Exception
	{
		List<HtmlData> list=new ArrayList<>();
		Workbook readWorBook =null;
		Sheet readSheet =null;
		String modulePath=(String) session.getAttribute("modulePath");
		String moduleName=(String) session.getAttribute("moduleName");
		String generateTC=(String) session.getAttribute("generateTC");
		String generateTS=(String) session.getAttribute("generateTS");
		File readFile = null;
		if(props.get("OS").equals("MacOS")){
			readFile=new File(modulePath+"/Scenarios.xlsx");
		}
		else {
			readFile=new File(modulePath+"\\Scenarios.xlsx");
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
				
				
				
				if(row.getCell(1)!=null && !row.getCell(0).getStringCellValue().equals(""))
				{
					hd.setFeature(row.getCell(0).getStringCellValue());
					allFeatures.add(row.getCell(0).getStringCellValue());
				}
				else
				{
					hd.setFeature("");
				}
				
				if(!row.getCell(1).getStringCellValue().equals("") && row.getCell(1)!= null)
				{
					hd.setPossibleValue(row.getCell(1).getStringCellValue());
					allPossibleValues.add(row.getCell(1).getStringCellValue());
				}else
				{
					hd.setPossibleValue("");
				}
				
				if(!row.getCell(2).getStringCellValue().equals("") && row.getCell(2)!= null)
				{
					hd.setStatement(row.getCell(2).getStringCellValue());
					
				}else
				{
					hd.setStatement("");
				}
				
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
			}
			
		}
		return list;
	}

}
