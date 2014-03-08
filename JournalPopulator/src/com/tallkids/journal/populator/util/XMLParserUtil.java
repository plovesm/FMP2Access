/**
 * 
 */
package com.tallkids.journal.populator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tallkids.journal.populator.data.JournalEntry;

/**
 * @author Paul
 *
 */
public class XMLParserUtil {

	public static List<JournalEntry> readInXML(String filename) {
		String currDir = System.getProperty("user.dir");
		
		return readInXML(currDir, filename);
	}

	public static List<JournalEntry> readInXML(String path, String filename) {
		List<JournalEntry> journalEntries = null;
		
		String filepath = path + "\\" + filename;
		
		try 
		{
		
			//Credit for following example goes to http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
			File fXmlFile = new File(filepath);
			
			if(fXmlFile.exists())
			{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();
			 
				NodeList rowList = doc.getDocumentElement().getChildNodes();
			 
				if(rowList.getLength() > 0)
				{
					journalEntries = new ArrayList<JournalEntry>();
					
					for (int temp1 = 0; temp1 < rowList.getLength(); temp1++)
					{
						
						Node iteration = rowList.item(temp1);
						
						if(iteration.getNodeName().equalsIgnoreCase("row"))
						{
							
							if (iteration.getNodeType() == Node.ELEMENT_NODE) {
							
								JournalEntry jEntry = new JournalEntry();
								
								Element rowElement = (Element) iteration;
								
								jEntry.setId(rowElement.getAttribute("RECORDID"));
																
								NodeList nList = iteration.getChildNodes();
								
								for (int temp = 0; temp < nList.getLength(); temp++) 
								{
							 
									Node nNode = nList.item(temp);
									
									if (nNode.getNodeType() == Node.ELEMENT_NODE) {
										//Element eElement = (Element) nNode;
										String nName = nNode.getNodeName();
										String nValue = nNode.getTextContent();
										
										if(nName.equalsIgnoreCase("author")){
											jEntry.setAuthor(nValue);
										}
										else if(nName.equalsIgnoreCase("date")){
											jEntry.setDate(nValue);
										}
										else if(nName.equalsIgnoreCase("location")){
											jEntry.setLocation(nValue);
										}
										else if(nName.equalsIgnoreCase("memo")){
											jEntry.setMemo(nValue);
										}
										else if(nName.equalsIgnoreCase("quote_from")){
											jEntry.setQuotefrom(nValue);
										}
										else if(nName.equalsIgnoreCase("scripture")){
											jEntry.setScripture(nValue);
										}
										else if(nName.equalsIgnoreCase("source")){
											jEntry.setSource(nValue);
										}
										else if(nName.equalsIgnoreCase("special")){
											jEntry.setSpecial(nValue);
										}
										else if(nName.equalsIgnoreCase("subject")){
											jEntry.setSubject(nValue);
										}
										else if(nName.equalsIgnoreCase("text")){
											jEntry.setText(nValue);
										}
										
									}
								}
								
								journalEntries.add(jEntry);
							}
						}
					}
				}
				
			}
			
	    } 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }	

		return journalEntries;
	}

	public static JournalEntry getRecordById(List<JournalEntry> journalEntries, String recordId) {
		
		JournalEntry jEntry = new JournalEntry();
		
		if(journalEntries.size() > 0 && !recordId.equals(null)){
			
			for(JournalEntry j : journalEntries){
				if(j.getId().equals(recordId)){
					jEntry = j;
				}
			}
		}
		
		return jEntry;
	}
	
	
	
}
