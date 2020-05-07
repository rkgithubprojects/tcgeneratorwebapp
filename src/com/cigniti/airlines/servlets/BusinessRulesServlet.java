package com.cigniti.airlines.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.airlines.supportClasses.BusinessRulesSupport;
import com.cigniti.airlines.utils.UtilitiesClass;


@WebServlet("/businessRulesServlet")
public class BusinessRulesServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, List<String>> bRules=new LinkedHashMap<>();
		try
		{
		
			HttpSession session=request.getSession();
			String[] features=request.getParameterValues("feature[]");
			String[] possibleValues=request.getParameterValues("possibleValue[]");
			
			List<String> newRule=new ArrayList<>();
			
			for (int i = 0; i < features.length; i++) 
			{
				
				newRule.add(features[i]+":"+possibleValues[i]);
			}
			
			
			BusinessRulesSupport bSupport=new BusinessRulesSupport();
			bRules=bSupport.readBusinessRules(session);
			
			
			int key=bRules.size();
			if(key!=0)
			{
				bRules.remove(key);
			}
			key=bRules.size();
			bRules.put(key+1, newRule);
			int dummyRowNum=bRules.size();
			
			List<String> dummyList=new ArrayList<>();
			dummyList.add("DummyCell0");
			dummyList.add("DummyCell1");
			dummyList.add("DummyCell2");
			bRules.put(dummyRowNum+1, dummyList);
			boolean result=bSupport.writeBusinessRules(bRules,session);
			if (result) {
				
				response.sendRedirect("brules.jsp");
			} else {
				
				response.sendRedirect("Error.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean status=false;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		HttpSession session=null;
		try{
		session=req.getSession();
		String[] R0=req.getParameterValues("R0");
		String[] R1=req.getParameterValues("R1");
		String[] R2=req.getParameterValues("R2");
		String[] R3=req.getParameterValues("R3");
		String[] R4=req.getParameterValues("R4");
		String[] R5=req.getParameterValues("R5");
		String[] R6=req.getParameterValues("R6");
		String[] R7=req.getParameterValues("R7");
		String[] R8=req.getParameterValues("R8");
		String[] R9=req.getParameterValues("R9");
		String[] R10=req.getParameterValues("R10");
	
		
		int rulesCount=R1.length;
		
		String sheetName="Sheet1";
		tcWorkbook = new XSSFWorkbook();
		BusinessRulesSupport brs=new BusinessRulesSupport();
		String filePath=brs.getFilePath(session);
		File testCaseFile = null;
		if(UtilitiesClass.props.get("OS").equals("MacOS")){
		testCaseFile = new File(filePath+"/BusinessRules.xlsx");
		}else {
			testCaseFile = new File(filePath+"\\BusinessRules.xlsx");
		}
		tcOutputStream = new FileOutputStream(testCaseFile);
		Sheet tcOutsheet = tcWorkbook.createSheet(sheetName);
		Row row=null;
		
		for(int i=0;i<rulesCount;i++)
		{

			row=tcOutsheet.createRow(i);
			
			Cell cell0=row.createCell(0);
			Cell cell1=row.createCell(1);
			Cell cell2=row.createCell(2);
			Cell cell3=row.createCell(3);
			Cell cell4=row.createCell(4);
			Cell cell5=row.createCell(5);
			Cell cell6=row.createCell(6);
			Cell cell7=row.createCell(7);
			Cell cell8=row.createCell(8);
			Cell cell9=row.createCell(9);
			/*Cell cell10=row.createCell(10);*/
		
						
				cell0.setCellValue(R1[i]);
				cell1.setCellValue(R2[i]);
				cell2.setCellValue(R3[i]);
				cell3.setCellValue(R4[i]);
				cell4.setCellValue(R5[i]);
				cell5.setCellValue(R6[i]);
				cell6.setCellValue(R7[i]);
				cell7.setCellValue(R8[i]);
				cell8.setCellValue(R9[i]);
				cell9.setCellValue(R10[i]);
		
			}
			tcWorkbook.write(tcOutputStream);
			status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			tcOutputStream.close();
			tcWorkbook.close();
			
			if(!status)
				{
				resp.sendRedirect("Error.jsp");
				}
			else
			{
				resp.sendRedirect("brules.jsp");
			}
		}
		
	}
}
	
	
	
	
	
	

	


