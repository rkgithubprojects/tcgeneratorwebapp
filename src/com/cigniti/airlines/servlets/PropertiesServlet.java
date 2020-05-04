package com.cigniti.airlines.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cigniti.airlines.supportClasses.PropertiesSupport;

@WebServlet("/propertiesServlet")
public class PropertiesServlet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> props=new LinkedHashMap<>();
		try
		{
		
		
		String[] readFile=request.getParameterValues("readFile");
		String[] writeFile=request.getParameterValues("writeFile");
		String[] testCasesFile=request.getParameterValues("testCasesFile");
		String[] minPaxCount=request.getParameterValues("minPaxCount");
		String[] maxPaxCount=request.getParameterValues("maxPaxCount");
		String[] testCasesPath=request.getParameterValues("testCasesPath");
		String[] resultsPath=request.getParameterValues("resultsPath");
		String[] businessRulesSheetName=request.getParameterValues("businessRulesSheetName");
		String[] sheetNames=request.getParameterValues("sheetNames");
		String[] generateTestCases=request.getParameterValues("generateTestCases");
		String[] generateTestScript=request.getParameterValues("generateTestScript");
		String[] reportsDirectory=request.getParameterValues("reportsDirectory");
		String[] verificationSteps=request.getParameterValues("verificationSteps");
		String[] url=request.getParameterValues("url");
		
		
		props.put(url[0], url[1]);
		props.put(readFile[0], readFile[1]);
		
		//props.put(testCasesFile[0], testCasesFile[1]);
		props.put(minPaxCount[0], minPaxCount[1]);
		props.put(maxPaxCount[0], maxPaxCount[1]);
		props.put(testCasesPath[0], testCasesPath[1]);
		props.put(resultsPath[0], resultsPath[1]);
		props.put(businessRulesSheetName[0], businessRulesSheetName[1]);
		props.put(sheetNames[0], sheetNames[1]);
		props.put(generateTestCases[0], generateTestCases[1]);
		props.put(generateTestScript[0], generateTestScript[1]);
		props.put(reportsDirectory[0], reportsDirectory[1]);
		props.put(verificationSteps[0], verificationSteps[1]);
		
		
		PropertiesSupport ps=new PropertiesSupport();
		boolean result=ps.writeToFile(props,"TestData");
	    if(result)
	    {
	    	
	    	response.sendRedirect("config.jsp");
	    }
	    else{
	    	response.sendRedirect("Error.jsp");
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	

}
