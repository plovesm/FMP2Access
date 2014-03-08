/**
 * 
 */
package com.tallkids.journal.poplulator.app;

import java.io.File;
import java.util.List;

import com.tallkids.journal.populator.data.JournalEntry;
import com.tallkids.journal.populator.util.AccessSQLUtil;
import com.tallkids.journal.populator.util.XMLParserUtil;

/**
 * @author Paul
 *
 */
public class PopulatorApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read in the file
		List<JournalEntry> entryList = XMLParserUtil.readInXML("recordsIn.xml");
		
		//Create new file
		File outputFile = AccessSQLUtil.createOutputFile("recordsOut.sql");
		
		if(!outputFile.equals(null))
		{
			int recordsIn = entryList.size();
			int count = 0;
			
			//Loop through the records
			for(JournalEntry je: entryList)
			{
				
				//Create the string record
				String strRecord = AccessSQLUtil.createRecordInsert(je, "Journal");
				
				//Write the record to the file
				if(AccessSQLUtil.writeInsertToFile(strRecord, outputFile))
				{
					count += 1;
				}
			}
			
			System.out.println("Total Records Read: " + recordsIn + 
					" Total Records Written: " + count);
			
		}
		else
		{
			System.err.println("File could not be created.");
		}
	}

}
