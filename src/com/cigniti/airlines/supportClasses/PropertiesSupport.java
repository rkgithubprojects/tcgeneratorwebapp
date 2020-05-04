package com.cigniti.airlines.supportClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cigniti.airlines.utils.UtilitiesClass;

public class PropertiesSupport extends UtilitiesClass {
	
	

	public boolean writeToFile(Map<String, String> props,String fileName) {
		boolean result=false;
		PrintWriter writer=null;
		try {
			String projectDirectory=propData.get("ProjectDirectory");
					//"C:\\ABC\\TCgenerator";
					//System.getProperty("user.dir").replace("'\'", "'\\'");
			String filePath=projectDirectory+"/src/"+fileName+".properties";
			writer = new PrintWriter(filePath, "UTF-8");
			for (String key : props.keySet()) {
				writer.println(key + "=" + props.get(key));
			}
			result=true;
		} catch (Exception e) {
			result=false;
			e.printStackTrace();
		}
		finally {
			writer.close();
		}
		return result;
	}

	public Map<String,String> readFile(String fileName)
	{
		Map<String,String> lprops=new LinkedHashMap<>();
		try
		{
			String projectDirectory=propData.get("ProjectDirectory");//"C:\\ABC\\TCgenerator";
					//System.getProperty("user.dir").replace("'\'", "'\\'");
			String filePath=projectDirectory+"/src/"+fileName+".properties";
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str;

			List<String> list = new ArrayList<String>();
			while((str = in.readLine()) != null){
			    list.add(str);
			}
			
			for (String dataPair : list) {
				
				lprops.put(dataPair.split("=")[0], dataPair.split("=")[1]);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lprops;
	}
	
	
}
