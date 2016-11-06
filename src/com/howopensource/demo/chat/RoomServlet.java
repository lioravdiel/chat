package com.howopensource.demo.chat;

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
		
		System.out.println("doPost...");
		String userName = request.getParameter("username");
		String roomName = request.getParameter("room");
		request.getSession().setAttribute("username", userName);
		//request.getSession().setAttribute("room", roomName);
		System.out.println("userName:"+userName+"  room:"+roomName+" session:"+(String)request.getSession().getAttribute("SESSION_ID"));
		
		RoomsManager roomsManager=RoomsManager.getInstance();
		if (roomsManager.getRoom(roomName)==null) {
			Room newRoom=roomsManager.addRoom(roomName, userName);
			Thread th=new Thread(new RoomRunner(newRoom));
			th.start();
		}
		roomsManager.getSessionRoomMap().put((String)request.getSession().getAttribute("SESSION_ID"), roomName);
	}

}
