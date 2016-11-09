package com.chat;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.AsyncListener;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.http.HttpServletRequest;

public class ChatUtils {
	public static void sendMessage(PrintWriter writer,Message message) {
		//System.out.println("sendMessage..."+sys_counter++);
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
	
	public static void addAsyncContext(HttpServletRequest request,final Room room) {
		// Generate some unique identifier used to store context in map
		final String id = UUID.randomUUID().toString();
		// Start asynchronous context and add listeners to remove it in case of errors
		final AsyncContext ac = request.startAsync();
		ac.addListener(new AsyncListener() {
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				room.getAsyncContexts().remove(id);
			}
			@Override
			public void onError(AsyncEvent event) throws IOException {
				room.getAsyncContexts().remove(id);
			}
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				// Do nothing
			}
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				room.getAsyncContexts().remove(id);
			}
		});

		// Put context in a map
		room.getAsyncContexts().put(id, ac);
	}
}
