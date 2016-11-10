
package com.chat;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
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
	private boolean running;
	private AtomicLong counter = new AtomicLong();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyy");
	StringBuffer retString=new StringBuffer();
	
	volatile Map<String, RoomContext> asyncContexts=new ConcurrentHashMap<String, RoomContext>();
	// Temporary store for messages when arrived
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();

	// Keep last messages
	private List<Message> messageStore = new CopyOnWriteArrayList<Message>();


	// Thread that waits for new message and then redistribute it
		private Thread notifier = new Thread(new Runnable() {
		
			@Override
			public void run() {
				System.out.println("notifier thread running..");
				while (running) {
					try {
						System.out.println("wait for new message...");
						// Waits until a message arrives
						Message message = messageQueue.take();
						System.out.println("ChatServlet Thread running...");
						// Put a message in a store
						messageStore.add(message);
						System.out.println("messageStore: " + messageStore.size());
						// Keep only last 100 messages
						if (messageStore.size() > 100) {
							messageStore.remove(0);
						}

						// Sends the message to all the AsyncContext's response
						for (RoomContext roomContext : asyncContexts.values()) {
							try {
								if (roomContext.getRoom().getName().equals(message.getRoomName())) {
									
									sendMessage(roomContext.getAc().getResponse().getWriter(), message);
								}
							} catch (Exception e) {
								// In case of exception remove context from map
								asyncContexts.values().remove(roomContext);
							}
						}
					} catch (InterruptedException e) {
						// Log exception, etc.
					}
				}
			}
		});


	
	@Override
	public void destroy() {
		// Stops thread and clears queue and stores
		System.out.println("ChatServlet destroy...");
		running = false;
		messageQueue.clear();
		messageStore.clear();
		asyncContexts.clear();
		
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Load previous messages from DB into messageStore
		// messageStore.addAll(db.loadMessages(100));
		roomsManager=RoomsManager.getInstance();
		// Start thread
		running = true;
		notifier.start();
		System.out.println("ChatServlet initialize...");
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChatServlet doGet... session: " + (String)request.getParameter("sessionId"));
		AppSessionContext appContext=roomsManager.getSessionInfoMap().get((String)request.getParameter("sessionId"));
		System.out.println("ChatServlet doGet... appcontext: " + appContext);
		if (null==appContext) {
			System.out.println("ChatServlet doGet - dispaching to chat.jsp");
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
			return;
		}
		Room room=appContext.getRoom();
		String roomName=room.getName();
		System.out.println("request room: "+roomName);
		request.setAttribute("room", roomName);
		System.out.println("room: "+room.getName()+" roomId:"+room.getId()+" session:"+request.getSession().getAttribute("SESSION_ID"));
		// This is for loading home page when user comes for the first time
		String success = (String)request.getAttribute("success"); 
		if (success != null) {
			request.setAttribute("messages", messageStore);
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
			return;
		}
		
		// Check that it is SSE request
		if ("text/event-stream".equals(request.getHeader("Accept"))) {
			System.out.println("ChatServlet event-stream");
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
					for (Message message : messageStore) {
						if (message.getId() > lastId && message.getRoomName().equals(roomName)) {
							sendMessage(response.getWriter(), message);
						}
					}
				}
			} else {
				long lastId = 0;
				try {
					lastId = messageStore.get(messageStore.size() - 1).getId();
				} catch (Exception e) {
					// Do nothing as this just gets the last id
				}
				if (lastId > 0) {
					// Send some ping with the last id. Idea is browser to be informed
					// which is the last event id. Also tell the browser if connection
					// fails to reopen it after 1000 milliseconds
					response.getWriter().println("retry: 1000\n");
					Message message = new Message(lastId, "Welcome to chat, type message and press Enter to send it.","system",roomName);
					sendMessage(response.getWriter(), message);
				}
			}
			//ChatUtils.addAsyncContext(request, room);
			// Generate some unique identifier used to store context in map
			final String id = UUID.randomUUID().toString();
			// Start asynchronous context and add listeners to remove it in case of errors
			final AsyncContext ac = request.startAsync();
			ac.setTimeout(0);
			ac.addListener(new AsyncListener() {
				@Override
				public void onComplete(AsyncEvent event) throws IOException {
					asyncContexts.remove(id);
				}
				@Override
				public void onError(AsyncEvent event) throws IOException {
					asyncContexts.remove(id);
				}
				@Override
				public void onStartAsync(AsyncEvent event) throws IOException {
					// Do nothing
				}
				@Override
				public void onTimeout(AsyncEvent event) throws IOException {
					asyncContexts.remove(id);
				}
			});

			// Put context in a map
			RoomContext roomContext=new RoomContext(ac, room);
			asyncContexts.put(id, roomContext);

		}
	}


	/**
	 * Receives messages from client (AJAX call).
	 * Verify message, save it to DB, etc.
	 * And finally put it into messageQueue.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ChatServlet doPost...");
		AppSessionContext appContext=roomsManager.getSessionInfoMap().get((String)request.getParameter("sessionId"));
		String roomName=appContext.getRoom().getName();
		String userName=appContext.getUserName();		
		// Sets char encoding - should not be done here, better in filter
		request.setCharacterEncoding("UTF-8");

		// Gets message from request
		String message = request.getParameter("msg");

		// Do some verification and save message into DB, etc.
		if ((message != null) && !message.trim().isEmpty()) {
		
			if (message.equalsIgnoreCase("#clear")) {
				messageQueue.clear();
				messageStore.clear();
				return;
			}
			// Save message
			// db.saveMessage(message);
				// Create new simple message
			System.out.println("Send message from roomName:"+roomName);
			Message msg = new Message(counter.incrementAndGet(), message.trim(),userName,roomName);
			this.messageQueue.add(msg);
			// Put message into messageQueue
			//room.getMessageQueue().put(msg);
			//room.addMessage(message,(String)request.getSession().getAttribute("username"));
		}
	}
	
	public static void sendMessage(PrintWriter writer,Message message) {
		System.out.println("ChatServlet sendMessage...");
		//writer.print("<!doctype html>");
		writer.print("id: ");
		writer.println(message.getId());
		writer.print("data: ");
		String msg = message.getMessage();
		char firstChar = Character.toUpperCase(msg.charAt(0));
	 	msg = firstChar + msg.substring(1);
		writer.println("<img src='images/user_profile.png' width='20px' height='20px'><span style='font-size: 9px; color:red;'>[" + message.getId() + "]"+message.getSender()+":</span><br>"+ msg + "<br><hr>");
		writer.println();
		writer.flush();
	}
	
	class RoomContext {
		AsyncContext ac;
		Room room;
		
		public RoomContext(AsyncContext ac, Room room) {
			super();
			this.ac = ac;
			this.room = room;
			System.out.println("ChatServlet RoomContext...");
		}
		public AsyncContext getAc() {
			return ac;
		}
		public Room getRoom() {
			return room;
		}
		
	}
}
