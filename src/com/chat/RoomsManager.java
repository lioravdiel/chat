package com.chat;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RoomsManager {
	private static RoomsManager me;
	private HashMap<String,Room> rooms;
	private AtomicLong roomId;
	private HashMap<String,AppSessionContext> sessionInfoMap;
	private RoomsManager() {
		rooms=new HashMap<String,Room>();
		sessionInfoMap=new HashMap<String,AppSessionContext>();
		roomId = new AtomicLong();
	}
	
	public static RoomsManager getInstance() {
		System.out.println("RommsManager getInstance...");
		if (me==null) {
			me=new RoomsManager();
		}
		return me;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public HashMap<String, AppSessionContext> getSessionInfoMap() {
		return sessionInfoMap;
	}
	
	public Room getRoom(String name) {
		return rooms.get(name);
	}
	
	public Room addRoom(String name, String creator) {
		Room room=new Room(name,creator,roomId.incrementAndGet());
		this.rooms.put(name, room);
		return room;
	}
	
	public void addSessionInfo(String sessionId,String userName,Room room) {
		getSessionInfoMap().put(sessionId, new AppSessionContext(userName, room));
	}
	
}
