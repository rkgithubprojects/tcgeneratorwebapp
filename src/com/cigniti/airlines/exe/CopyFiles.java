package com.cigniti.airlines.exe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class CopyFiles {

	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(new File("\\\\172.16.28.230\\Airlines\\TCgenerator\\SW_TC_TS_Generator\\Flight\\GeneratedTestCases.xlsx"));
		OutputStream out = new FileOutputStream(new File("C:\\ABC\\GeneratedTestCases.xlsx"));

		
		//
		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
		out.write(buf, 0, len);
		}
		in.close();
		out.close();

	}
}

