package com.cigniti.airlines.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cigniti.airlines.accelerators.GenerateTestCase;
import com.cigniti.airlines.accelerators.GenerateTestScript;
import com.cigniti.airlines.accelerators.ReadExcel;
import com.cigniti.airlines.accelerators.ReadStaticSteps;
import com.cigniti.airlines.accelerators.StoreExcelData;
import com.cigniti.airlines.reports.GenerateReports;
import com.cigniti.airlines.utils.TestData;


@WebServlet("/executeServlet")
public class ExecuteServlet extends HttpServlet implements SingleThreadModel {
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			HttpSession session=request.getSession();
			String[] sheetNames={"Sheet1"};
			
			ReadExcel readExcel=new ReadExcel();
			Set<List<String>> excelData=readExcel.readExcelData(sheetNames[0],session);
			
			StoreExcelData storeExcelData=new StoreExcelData();
			Map<String, TestData> storeData=storeExcelData.getSheetData(sheetNames[0],session);
			
			ReadStaticSteps rd=new ReadStaticSteps();
			Map<String, List<TestData>> staticData=rd.readStaticData(session);
			
			GenerateTestCase tc=new GenerateTestCase();
			tc.generateTestCase(excelData, storeData, staticData, sheetNames[0],session);
			
			GenerateTestScript generateScript=new GenerateTestScript();
			generateScript.runTestScript(excelData, storeData,staticData,sheetNames[0],session);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				GenerateReports.getSummaryReports();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

	

}
