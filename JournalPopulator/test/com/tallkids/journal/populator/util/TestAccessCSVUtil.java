/**
 * 
 */
package com.tallkids.journal.populator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.tallkids.journal.populator.data.JournalEntry;

/**
 * @author Paul
 *
 */
public class TestAccessCSVUtil {

	@Test
	public void testCreateOutputFile() {
		
		File newFile = AccessSQLUtil.createOutputFile(getTestFileName());
		
		assertNotNull(newFile);
	}
	
	@Test
	public void testCreateRecordInsert() {
		
		JournalEntry jEntry = getTestEntry();
		
		String recordInsertStatement = AccessSQLUtil.createRecordInsert(jEntry, "Journal");
		
		assertNotNull(recordInsertStatement);
	}
	
	@Test
	public void testStringEscape() {
		String sanitizedString = AccessSQLUtil.stringEscape("This is a string that shouldn't have apostrophe");
		
		assertNotNull(sanitizedString);
		assertEquals("This is a string that shouldn''t have apostrophe", sanitizedString);
	}
	
	@Test
	public void testWriteInsertToFile() {
		String currDir = System.getProperty("user.dir");
		
		File fOutput = new File(currDir + "\\" + getTestFileName());
		JournalEntry jEntry = getTestEntry();
		String recordInsertStatement = AccessSQLUtil.createRecordInsert(jEntry, "Journal");
		
		boolean success = AccessSQLUtil.writeInsertToFile(recordInsertStatement, fOutput);
		
		assertTrue(success);
	}
	
	private JournalEntry getTestEntry(){
		
		List<JournalEntry> journalEntries = XMLParserUtil.readInXML("recordsIn.xml");
		
		JournalEntry jEntry = XMLParserUtil.getRecordById(journalEntries, "3293");
		
		return jEntry;
	}
	
	private String getTestFileName(){
		
		String filename = "accessSQL.txt";
		
		return filename;
	}
}
