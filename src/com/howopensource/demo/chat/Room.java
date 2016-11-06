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
	boolean isRunning;
	long id;
	String name;
	String creator;
	ArrayList<String> participants; 
	private AtomicLong counter = new AtomicLong();
	// Keeps all open connections from browsers
	private Map<String, AsyncContext> asyncContexts = new ConcurrentHashMap<String, AsyncContext>();
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

	public Room(String name,String creator) {
		this.name=name;
		this.creator=creator;
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

	public void setAsyncContexts(Map<String, AsyncContext> asyncContexts) {
		this.asyncContexts = asyncContexts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void addMessage(String message) {
		Message msg = new Message(counter.incrementAndGet(), message.trim());
		this.messageQueue.add(msg);
	}
	
	
}
