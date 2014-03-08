/**
 * 
 */
package com.tallkids.journal.populator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.tallkids.journal.populator.data.JournalEntry;

/**
 * @author Paul
 *
 */
public class TestXMLParserUtil {

	@Test
	public void testReadInXML() {

		List<JournalEntry> journalEntries = XMLParserUtil.readInXML("recordsIn.xml");
			
		assertNotNull(journalEntries);
		
	}
	
	@Test
	public void testReadInFaultyXML() {

		List<JournalEntry> journalEntries = XMLParserUtil.readInXML("wrongPath", "recordsIn.xml");
		
		assertNull(journalEntries);
	}
	
	@Test
	public void testGetRecordById() {

		List<JournalEntry> journalEntries = XMLParserUtil.readInXML("recordsIn.xml");
				
		JournalEntry jEntry = XMLParserUtil.getRecordById(journalEntries, "3293");
		
		assertNotNull(jEntry.getAuthor());
		assertEquals("Freck, MW", jEntry.getAuthor());
		
	}
	
	
}
