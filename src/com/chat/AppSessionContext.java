package com.chat;

public class AppSessionContext {
	String userName;
	Room room;
	public AppSessionContext(String userName, Room room) {
		super();
		this.userName = userName;
		this.room = room;
	}
	public String getUserName() {
		return userName;
	}
	public Room getRoom() {
		return room;
	}
}
