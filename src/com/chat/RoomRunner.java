package com.chat;

import javax.servlet.AsyncContext;

public class RoomRunner implements Runnable{
	Room room;
	public RoomRunner(Room room) {
		this.room=room;
	}
	@Override
	public void run() {
		/*while (room.isRunning()) {
			try {
				// Waits until a message arrives
				Message message = room.getMessageQueue().take();
				System.out.println("Thread running...");
				// Put a message in a store
				room.getMessageStore().add(message);

				// Keep only last 100 messages
				if (room.getMessageStore().size() > 100) {
					room.getMessageStore().remove(0);
				}

				// Sends the message to all the AsyncContext's response
				System.out.println("Send message to all client...");
				for (AsyncContext asyncContext : room.getAsyncContexts().values()) {
					System.out.println(asyncContext.toString());
					try {
						ChatUtils.sendMessage(asyncContext.getResponse().getWriter(), message);
					} catch (Exception e) {
						// In case of exception remove context from map
						room.getAsyncContexts().values().remove(asyncContext);
					}
				}
				System.out.println("Sent Done!");
			} catch (InterruptedException e) {
				// Log exception, etc.
			}
		}*/
	}

}
