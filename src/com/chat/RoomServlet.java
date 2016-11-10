package com.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/room")
public class RoomServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("RoomServlet doPost...");
		String userName = request.getParameter("username");
		String roomName = request.getParameter("room");
		String sessionId = (String)request.getParameter("sessionId");

		System.out.println("userName:"+userName+"  room:"+roomName+" session:"+sessionId);
		
		RoomsManager roomsManager=RoomsManager.getInstance();
		Room room=roomsManager.getRoom(roomName);
		if (room==null) {
			room=roomsManager.addRoom(roomName, userName);
		}
		roomsManager.addSessionInfo(sessionId, userName, room);
		response.getWriter().println(roomName);
	}

}
