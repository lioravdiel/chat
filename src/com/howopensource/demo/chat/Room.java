package com.howopensource.demo.chat;
import javax.servlet.AsyncContext;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Room {
	volatile Map<String, AsyncContext> asyncContexts;
	Boolean isRunning;
	long id;
	String name;
	String creator;
	ArrayList<String> participants; 
	private AtomicLong counter = new AtomicLong();
	// Keeps all open connections from browsers
	// Temporary store for messages when arrived
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();

	// Keep last messages
	private List<Message> messageStore = new CopyOnWriteArrayList<Message>();

	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public List<Message> getMessageStore() {
		return messageStore;
	}

	public Room(String name,String creator,long id) {
		this.name=name;
		this.creator=creator;
		this.id=id;
		participants=new ArrayList<String>();
		addParticipant(creator);
		//in the future we will change the isRunning according to room configuration
		this.setRunning(true);
		asyncContexts = new ConcurrentHashMap<String, AsyncContext>();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
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

	public Map<String, AsyncContext> getAsyncContexts() {
		return asyncContexts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void addMessage(String message,String sender) {
		Message msg = new Message(counter.incrementAndGet(), message.trim(),sender);
		this.messageQueue.add(msg);
	}
	
	public void addParticipant(String participant) {
		this.participants.add(participant);
	}
}
