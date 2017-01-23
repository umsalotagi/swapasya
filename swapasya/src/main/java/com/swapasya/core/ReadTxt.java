package com.swapasya.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTxt {
	
	
	 ArrayList <String> tempText = new ArrayList <String>();
	 BufferedReader br;
	 ArrayList <ArrayList <String>> textDb = new ArrayList <ArrayList <String>>();
	 
	 public ArrayList <ArrayList <String>> readT (String filePath) throws ClassNotFoundException {
		 try {
			 textDb.clear();
			 tempText.clear();
			 
			 Class cls = Class.forName("com.core.ReadTxt");
			 ClassLoader cLoader = cls.getClassLoader();
			 
	         // input stream
	         InputStream is = cLoader.getResourceAsStream(filePath);
	         if (is == null)
	        	 System.out.println("is is null");
	         InputStreamReader isr = new InputStreamReader(is);
	         BufferedReader br = new BufferedReader(isr);
	         
			 
			// br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			
			while (line != null) {
				tempText.add(line);
		        line = br.readLine();
		    }
			
			
			int row = tempText.size();
			for (int i=0; i<row; i++) {
				textDb.add(new ArrayList <String> ());
				
				String[] cols = tempText.get(i).split(",");
				
				for (int j=0; j<cols.length ; j++) {
					textDb.get(i).add(cols[j]);
				}
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return textDb;
		
	 }
	 
	 public static void write (String content , String filePath) throws IOException {

//			File file = new File(filePath);
//
//			FileWriter fw = new FileWriter(file , true);
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(content);
//			bw.close();

	 }
	 
	

}
