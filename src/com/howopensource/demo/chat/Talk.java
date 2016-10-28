package com.howopensource.demo.chat;

public class Talk {

	private String id;
	private String from;
	private String to;
	private String message;
	
	public Talk(String id, String from, String to, String message) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
