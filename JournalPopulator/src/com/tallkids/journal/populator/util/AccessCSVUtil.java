/**
 * 
 */
package com.tallkids.journal.populator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tallkids.journal.populator.data.JournalEntry;

/**
 * @author Paul
 *
 */
public class AccessCSVUtil {

	public static File createOutputFile(String filename) {
		String currDir = System.getProperty("user.dir");
		
		return createOutputFile(currDir, filename, true);
	}
	
	public static File createOutputFile(String filename, boolean overwriteExisting) {
		String currDir = System.getProperty("user.dir");
		
		return createOutputFile(currDir, filename, overwriteExisting);
	}
	
	public static File createOutputFile(String path, String filename, boolean overwriteExisting) {
		
		File newFile = new File(path + "\\" + filename);
		
		if(newFile.exists())
		{
			//File exists
			if(overwriteExisting)
			{
				newFile.delete();
				return createNewFile(newFile);
			}
			else
			{
				return newFile;
			}
		}
		else
		{
			return createNewFile(newFile);
		}
		
	}
	
	private static File createNewFile(File newFile){
		
		try 
		{
			if(newFile.createNewFile())
			{
				//Directory created successfully
				return newFile;
			}
			else
			{
				//file creation failed
				return null;
			}
		} 
		catch (IOException e) {
			return null;
		}		
	}
	
	public static String createFirstRow()
	{
		StringBuffer fieldList = new StringBuffer();
		
		fieldList.append("[ID],");
		fieldList.append("[Subject], ");
		fieldList.append("[Author], ");
		fieldList.append("[Quote From], ");
		fieldList.append("[Source], ");
		fieldList.append("[Scripture], ");
		fieldList.append("[Memo], ");
		fieldList.append("[Special], ");
		fieldList.append("[Date], ");
		fieldList.append("[MessageText], ");
		fieldList.append("[Location]");
		fieldList.append("\n");
		
		return fieldList.toString();
	}
	public static String createRecordInsert(JournalEntry jEntry, String tableName) {
		StringBuffer insertStatement = new StringBuffer();
		
		if(jEntry.getId().length() > 0)
		{
			insertStatement.append("'" + stringEscape(jEntry.getId()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getSubject()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getAuthor()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getQuotefrom()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getSource()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getScripture()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getMemo()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getSpecial()) + "', ");
			insertStatement.append("#" + stringEscape(jEntry.getDate()) + "#, ");
			insertStatement.append("'" + stringEscape(jEntry.getText()) + "', ");
			insertStatement.append("'" + stringEscape(jEntry.getLocation()) + "'");
			insertStatement.append("\n");
			

		}
		
		return insertStatement.toString();
	}
	
	
	public static String stringEscape(String strIn) {
		
		return strIn.replace("'", "''");
		
	}

	public static boolean writeInsertToFile(String insertSQL, File file) {
		boolean success = false;
		
		try 
		{
			 
			String content = insertSQL;

			// if file doesnt exists, then create it
			if (!file.exists()) {
				createNewFile(file);
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(content);
			bw.append("\n");
			bw.close();
			
			success = true;

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return success;
	}

}
