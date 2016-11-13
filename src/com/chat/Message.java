
package com.chat;

import java.util.Date;

/**
 * This class represents simple message with just two attributes - id and message.
 */
public class Message {

	private long id;
	private String message;
	private String sender;
	private String roomName;
	private String date;
	


	public Message(long id, String message,String sender,String roomName, String date) {
		super();
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.roomName = roomName;
		this.date = date;
	}


	public long getId() {
		return id;
	}


	public String getMessage() {
		return message;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

}
