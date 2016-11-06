package com.howopensource.demo.chat;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RoomsManager {
	private static RoomsManager me;
	private HashMap<String,Room> rooms;
	private AtomicLong roomId;
	private HashMap<String,String> sessionRoomMap;
	private RoomsManager() {
		rooms=new HashMap<String,Room>();
		sessionRoomMap=new HashMap<String,String>();
		roomId = new AtomicLong();
	}
	
	public static RoomsManager getInstance() {
		if (me==null) {
			me=new RoomsManager();
		}
		return me;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public HashMap<String, String> getSessionRoomMap() {
		return sessionRoomMap;
	}
	
	public Room getRoom(String name) {
		return rooms.get(name);
	}
	
	public Room addRoom(String name, String creator) {
		Room room=new Room(name,creator,roomId.incrementAndGet());
		this.rooms.put(name, room);
		return room;
	}
}
