/*
 * HowOpenSource.com
 * Copyright (C) 2015 admin@howopensource.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */


package com.chat;


import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Main servlet that does everything.
 * Receive and send messages. Send last messages to JSP, etc.
 * Better split it between two servlets.
 *
 */
@SuppressWarnings("serial")
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

	RoomsManager roomsManager;
	//private boolean running;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyy");
	StringBuffer retString=new StringBuffer();
	
	// Temporary store for messages when arrived
	//private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();

	// Keep last messages
	//private List<Message> messageStore = new CopyOnWriteArrayList<Message>();


	@Override
	public void destroy() {
		// Stops thread and clears queue and stores
		roomsManager.getRooms().forEach((key,room)->
			{
				room.getMessageQueue().clear();
				room.getMessageStore().clear();
				room.getAsyncContexts().clear();
				room.setRunning(false);
			}
		);
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Load previous messages from DB into messageStore
		// messageStore.addAll(db.loadMessages(100));
		roomsManager=RoomsManager.getInstance();
		// Start thread
		System.out.println("initialize...");
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet...");
		String roomName=roomsManager.getSessionRoomMap().get((String)request.getSession().getAttribute("SESSION_ID"));
		//String roomName=(String)request.getParameter("room");
		System.out.println("request room: "+roomName);
		Room room=roomsManager.getRoom(roomName);
		
		
		if (room==null) {
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
			return;
		}
		request.setAttribute("room", roomName);
		System.out.println("room: "+room.getName()+" roomId:"+room.getId()+" session:"+request.getSession().getAttribute("SESSION_ID"));
		// This is for loading home page when user comes for the first time
		if (request.getAttribute("success") != null) {
			request.setAttribute("messages", room.getMessageStore());
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
			return;
		}
		
		// Check that it is SSE request
		if ("text/event-stream".equals(request.getHeader("Accept"))) {

			// This a Tomcat specific - makes request asynchronous
			request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

			// Set header fields
			response.setContentType("text/event-stream");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Connection", "keep-alive");
			response.setCharacterEncoding("UTF-8");

			// Parse Last-Event-ID header field which should contain last event received
			String lastMsgId = request.getHeader("Last-Event-ID");
			if ((lastMsgId != null) && !lastMsgId.trim().isEmpty()) {
				long lastId = 0;
				try {
					lastId = Long.parseLong(lastMsgId);
				} catch (NumberFormatException e) {
					// Do nothing as we have default value
				}
				if (lastId > 0) {
					// Send all messages that are not send - e.g. with higher id
					for (Message message : room.getMessageStore()) {
						if (message.getId() > lastId) {
							ChatUtils.sendMessage(response.getWriter(), message);
						}
					}
				}
			} else {
				long lastId = 0;
				try {
					lastId = room.getMessageStore().get(room.getMessageStore().size() - 1).getId();
				} catch (Exception e) {
					// Do nothing as this just gets the last id
				}
				if (lastId > 0) {
					// Send some ping with the last id. Idea is browser to be informed
					// which is the last event id. Also tell the browser if connection
					// fails to reopen it after 1000 milliseconds
					response.getWriter().println("retry: 1000\n");
					Message message = new Message(lastId, "Welcome to chat, type message and press Enter to send it.","system");
					ChatUtils.sendMessage(response.getWriter(), message);
				}
			}
			ChatUtils.addAsyncContext(request, room);

		}
	}


	/**
	 * Receives messages from client (AJAX call).
	 * Verify message, save it to DB, etc.
	 * And finally put it into messageQueue.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost...");
		String roomName=roomsManager.getSessionRoomMap().get((String)request.getSession().getAttribute("SESSION_ID"));
		Room room=roomsManager.getRoom(roomName);
		// Sets char encoding - should not be done here, better in filter
		request.setCharacterEncoding("UTF-8");

		// Gets message from request
		String message = request.getParameter("msg");

		// Do some verification and save message into DB, etc.
		if ((message != null) && !message.trim().isEmpty()) {
		
			if (message.equals("#clear")) {
				room.getMessageQueue().clear();
				room.getMessageStore().clear();
				return;
			}
			// Save message
			// db.saveMessage(message);
				// Create new simple message
			//Message msg = new Message(counter.incrementAndGet(), message.trim());
			// Put message into messageQueue
			//room.getMessageQueue().put(msg);
			room.addMessage(message,(String)request.getSession().getAttribute("username"));
		}
	}
}
