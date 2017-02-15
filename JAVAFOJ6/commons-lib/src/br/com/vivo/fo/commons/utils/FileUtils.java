package br.com.vivo.fo.commons.utils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;

public class FileUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724092704866445018L;
	
	
	 /**
	 * @param string
	 * @return
	 */
	public static String readFile(String fileName) {

		StringBuffer fileContent = new StringBuffer();

		try {			

			FileReader fileReader = new FileReader(fileName);
			int character;
			while ((character = fileReader.read()) != -1) {
				fileContent.append((char) character);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileContent.toString();
	}
	

	public static void writeFile(String fileName, String value) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw); 
			PrintWriter outFile = new PrintWriter(bw);
			outFile.println(value);
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
