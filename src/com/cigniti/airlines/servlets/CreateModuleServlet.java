package com.cigniti.airlines.servlets;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cigniti.airlines.accelerators.Configuration;
import com.cigniti.airlines.exe.CreateExcel;
import com.cigniti.airlines.supportClasses.PropertiesSupport;
import com.cigniti.airlines.utils.UtilitiesClass;

/**
 * Servlet implementation class CreateModuleServlet
 */
@WebServlet("/createModuleServlet")
public class CreateModuleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> moduleProps=new LinkedHashMap<String,String>();
		String moduleName="";
		boolean result=false;
		try
		{
		Configuration config=new Configuration();
		String projectDirectory=config.getProjectDirectory();
		String newModulePath=request.getParameter("modulePath");;
		moduleName=request.getParameter("moduleName");
		if(UtilitiesClass.props.get("OS").equals("MacOS")){
		newModulePath=projectDirectory+"/"+newModulePath+"/"+moduleName;
		}else {
			newModulePath=projectDirectory+"\\"+newModulePath+"\\"+moduleName;
		}
		File theDir = new File(newModulePath);

		if (!theDir.exists()) {
				theDir.mkdir();
				CreateExcel createExcel=new CreateExcel();
				//Create Scenarios Sheet
				createExcel.createExcelSheet(newModulePath,"Scenarios","Sheet1");
				
				//Create TestData Sheet
				createExcel.createExcelSheet(newModulePath,"TestData","Sheet1");
				
				//Create Brules Sheet
				createExcel.createBrulesSheet(newModulePath, "BusinessRules", "Sheet1");
				
				//Create Testcases Sheet
				createExcel.createBrulesSheet(newModulePath, "GeneratedTestCases", "Sheet1");
				
				createExcel.createVerificationSheet(newModulePath, "StaticSteps", "Sheet1");
				
				
				PropertiesSupport ps=new PropertiesSupport();
				
				moduleProps=ps.readFile("Module");
				moduleProps.put(moduleName, newModulePath);
				result=ps.writeToFile(moduleProps,"Module");
				response.sendRedirect("ConfigProject.jsp?message=New module with name '"+moduleName+"' created Successfully....");
				
		}else
		{
			response.sendRedirect("ModuleError.jsp");
		}
		
		
		}
		
		
		catch(Exception e)
		{
			result=false;
			e.printStackTrace();
		}
		finally
		{
			
		}
	}

	
	
}
