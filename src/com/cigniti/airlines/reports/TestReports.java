package com.cigniti.airlines.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.cigniti.airlines.utils.BaseClass;

public class TestReports extends BaseClass{
	public static String projectDirectory = props.get("ProjectDirectory");
	
	public static void main(String[] args) throws IOException {
		TestReports tr=new TestReports();
	//	tr.getLinkNames();
		
		
		tr.generateReport(tr.getLinkNames());
		
		
		
		
	}
	
	
	public Set<String> getLinkNames()
	{
		Set<String> links=new LinkedHashSet<>();
		links.add("Sheet1_1");
		links.add("Sheet2_2");
		links.add("Sheet2_3");
		links.add("Sheet2_4");
		links.add("Sheet2_5");
		links.add("Sheet2_6");
		return links;
	}
	
	public void generateReport(Set<String> links) throws IOException
	{
		Writer writer = null;
		
		File file = new File(projectDirectory+"/Output/Reports/TestReports.html");

		writer = new FileWriter(file, false);
		
		writer.write("<html>");
		writer.write("<body>");
		writer.write("<table>");
		
		writer.write("<tr>");
		writer.write("<td>SNO</td>");
		writer.write("<td>TestCase Name</td>");
		writer.write("<td>Status</td>");
		writer.write("</tr>");
		int i=0;
		for (String string : links) {
			i=i+1;
			writer.write("<tr>");
			writer.write("<td>"+i+"</td>");
			writer.write("<td><a href='" + projectDirectory + "/Output/Reports/SummaryReports.html'>"+string+"</a></td>");
			writer.write("<td>PASS</td>");
			writer.write("</tr>");
		}
		
		
		
		writer.write("</table>");
		writer.write("</body>");
		writer.write("</html>");
		writer.close();
	}
}
