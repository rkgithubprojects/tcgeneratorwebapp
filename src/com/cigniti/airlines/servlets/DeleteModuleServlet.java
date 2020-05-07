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

import com.cigniti.airlines.exe.CreateExcel;
import com.cigniti.airlines.supportClasses.PropertiesSupport;

/**
 * Servlet implementation class CreateModuleServlet
 */
@WebServlet("/deleteModuleServlet")
public class DeleteModuleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> moduleProps = new LinkedHashMap<String, String>();
		boolean result = false;
		try {
			String moduleData = request.getParameter("moduleData").replace("'\'", "'\\'");
			System.out.println("moduleData>>>>>>" + moduleData);
			String newModulePath = moduleData.split("##")[0];
			System.out.println("newModulePath>>>>>>" + newModulePath);
			String moduleName = moduleData.split("##")[1];
			System.out.println("moduleName>>>>>>" + moduleName);
			File directory = new File(newModulePath);

			if (!directory.exists()) {

				System.out.println("Directory does not exist.");
				System.exit(0);

			} else {
				try {
					delete(directory);

				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}

			
			//Delete from properties file
			PropertiesSupport ps=new PropertiesSupport();
			
			moduleProps=ps.readFile("Module");
			moduleProps.remove(moduleName);
			result=ps.writeToFile(moduleProps,"Module");

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			if (result) {
				response.sendRedirect("ConfigProject.jsp");
			} else {
				response.sendRedirect("Error.jsp");
			}
		}
	}

	
	
	
	//Method to delete directory
	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				// System.out.println("Directory is deleted : " +
				// file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					// System.out.println("Directory is deleted : " +
					// file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			// System.out.println("File is deleted : " +
			// file.getAbsolutePath());
		}
	}

}
