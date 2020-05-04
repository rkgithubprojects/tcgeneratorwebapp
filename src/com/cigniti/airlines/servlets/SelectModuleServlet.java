package com.cigniti.airlines.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cigniti.airlines.accelerators.Configuration;

/**
 * Servlet implementation class SelectModuleServlet
 */
@WebServlet("/selectModuleServlet")
public class SelectModuleServlet extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result=false;
		String moduleName="";
		try {
			System.out.println("Select Module Servlet Executed");
			
			String moduleData=request.getParameter("moduleData");
			moduleName=moduleData.split("##")[1];
			String modulePath=moduleData.split("##")[0].replace("'\'", "'\\'");
			String generateTC=request.getParameter("generateTC");
			String generateTS=request.getParameter("generateTS");
			String automation=request.getParameter("automation");
			String includeAuto=request.getParameter("includeAuto");
			/*System.out.println("moduleName @@@@@@@ : "+moduleName);
			System.out.println("modulePath @@@@@@@ : "+modulePath);
			System.out.println("generateTC @@@@@@@ : "+generateTC);
			System.out.println("generateTC @@@@@@@ : "+generateTC);
			System.out.println("generateTS @@@@@@@ : "+generateTS);*/
			
			
			/*Configuration configure=new Configuration();
			configure.setModuleData(moduleName, modulePath, generateTC, generateTS);*/
			 
			HttpSession session=request.getSession();
			System.out.println("Session Object $$$$$$$$$$$$ "+session);
		  
		     session.setAttribute("moduleName",moduleName); 
		     session.setAttribute("modulePath",modulePath); 
		     session.setAttribute("generateTC",generateTC); 
		     session.setAttribute("generateTS",generateTS); 
		     session.setAttribute("includeAuto",includeAuto);
		     
		     System.out.println("Include Automation ^^^^^^^^^  "+session.getAttribute("includeAuto"));
		     
			result=true;
		} catch (Exception e) {
			result=false;
			e.printStackTrace();
		}
		finally{
			if(result)
			{
				response.sendRedirect("index.jsp?moduleName="+moduleName);
			}
			else
			{
				response.sendRedirect("Error.jsp");
			}
		}
		
	}

	

}
