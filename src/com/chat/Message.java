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


/**
 * This class represents simple message with just two attributes - id and message.
 */
public class Message {

	private long id;
	private String message;
	private String sender;
	


	public Message(long id, String message,String sender) {
		super();
		this.id = id;
		this.message = message;
		this.sender = sender;
	}


	public long getId() {
		return id;
	}


	public String getMessage() {
		return message;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}

}
