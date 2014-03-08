/**
 * 
 */
package com.tallkids.journal.populator.data;

import java.io.Serializable;

/**
 * @author Paul
 *
 */
public class JournalEntry implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	String id;
	String author;
	String date;
	String location;
	String memo;
	String quotefrom;
	String scripture;
	String special;
	String source;
	String subject;
	String text;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getQuotefrom() {
		return quotefrom;
	}
	public void setQuotefrom(String quotefrom) {
		this.quotefrom = quotefrom;
	}
	public String getScripture() {
		return scripture;
	}
	public void setScripture(String scripture) {
		this.scripture = scripture;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
