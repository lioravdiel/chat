<%@page import="com.chat.Config"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>

	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(Config.checkValidationLogin(username, password))
			out.print(username);
		else
			out.print("ERROR");
		
	%>
