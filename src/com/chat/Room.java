package com.chat;

import java.util.ArrayList;

public class Room {
	long id;
	String name;
	String creator;
	ArrayList<String> participants; 

	public Room(String name,String creator,long id) {
		this.name=name;
		this.creator=creator;
		this.id=id;
		participants=new ArrayList<String>();
		addParticipant(creator);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public ArrayList<String> getParticipants() {
		return participants;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public void addParticipant(String participant) {
		this.participants.add(participant);
	}
}
