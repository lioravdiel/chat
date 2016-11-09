<%@page import="com.chat.Config"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>

	<%
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String password = request.getParameter("password");
		
		Config.insertToUsersTable(username, email, password, age);
		out.println(username);
	
	%>
