
package com.chat;


/**
 * This class represents simple message with just two attributes - id and message.
 */
public class Message {

	private long id;
	private String message;
	private String sender;
	private String roomName;
	


	public Message(long id, String message,String sender,String roomName) {
		super();
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.roomName = roomName;
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
	

}
