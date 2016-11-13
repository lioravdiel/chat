<%@page import="com.chat.Config"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>

	<%
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String password = request.getParameter("password");
		
		Config.insertToUsersTable(firstname, lastname, username, email, password, age);
		out.println(username);
	
	%>
