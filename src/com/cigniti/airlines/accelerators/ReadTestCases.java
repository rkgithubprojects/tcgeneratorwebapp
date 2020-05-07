package com.cigniti.airlines.accelerators;

import java.io.File;
import java.io.FileInputStream;
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

import com.cigniti.airlines.utils.TestData;
import com.cigniti.airlines.utils.UtilitiesClass;

public class ReadTestCases extends UtilitiesClass{
	
	public Map<Integer,String > readData(HttpSession session) throws Exception {
		InputStream inputStream = null;
		Workbook readWorBook;
		File inputFile;
		Map<Integer,String> readdata = new HashMap<>();

		try {
			if(props.get("OS").equals("MacOS")){
				inputFile = new File(session.getAttribute("modulePath")+"/GeneratedTestCases.xlsx");
			}
			else {
				inputFile = new File(session.getAttribute("modulePath")+"\\GeneratedTestCases.xlsx");
			}
			inputStream = new FileInputStream(inputFile);
			readWorBook = new XSSFWorkbook(inputStream);
			Sheet readSheet = readWorBook.getSheet("Sheet1");
			
			
			Row row=null;
			int rowsCount = readSheet.getLastRowNum() - readSheet.getFirstRowNum();
			

			for (int i = 1; i < rowsCount + 1; i++) {
				row=readSheet.getRow(i);
				int cell0=(int) row.getCell(0).getNumericCellValue();
				String cell1=row.getCell(1).getStringCellValue().trim();
				readdata.put(cell0, cell1);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return readdata;
	}

}
